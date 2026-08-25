package com.error404.reelix;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class TransferNetworkEngine {

    private static final int PORT = 8988;
    private static final int BUFFER_SIZE = 65536;
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    // ─── RECEIVER: Bind ServerSocket and wait ────────────────────────────────
    public static void startReceiving(final String saveDirectory, final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                Socket socket = null;
                DataInputStream dis = null;
                DataOutputStream dos = null;

                try {
                    serverSocket = new ServerSocket(PORT);
                    serverSocket.setReuseAddress(true);
                    serverSocket.setSoTimeout(120000); // 2 minutes timeout

                    postToast(activity, "Waiting for sender to connect...");

                    socket = serverSocket.accept();
                    socket.setSoTimeout(30000);

                    dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

                    // 1. Read metadata JSON
                    String jsonPayload = dis.readUTF();
                    JSONArray jsonArray = new JSONArray(jsonPayload);

                    // 2. Populate receive list (on main thread)
                    MAIN_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            TransferStateManager.receive_trans_map.clear();
                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("title", obj.optString("movieTitle", "Unknown"));
                                    map.put("video_path", "");
                                    map.put("size", obj.optString("size", "0MB"));
                                    map.put("cover_url", obj.optString("imageUrl", ""));
                                    map.put("progress", "0");
                                    map.put("status", "Waiting to receive...");
                                    TransferStateManager.receive_trans_map.add(map);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            TransferStateManager.notifyReceiveUpdated();
                        }
                    });

                    // Small delay to let UI populate
                    Thread.sleep(300);

                    // 3. Signal READY to sender
                    dos.writeUTF("READY");
                    dos.flush();

                    // 4. Receive each file
                    File saveDir = new File(saveDirectory);
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String fileName = obj.optString("movieTitle", "file_" + i) + ".mp4";
                        long fileSize = dis.readLong();

                        final int index = i;
                        updateReceiveItem(index, "Receiving...", "0");

                        File outFile = new File(saveDir, fileName);
                        int counter = 1;
                        while (outFile.exists()) {
                            String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
                            String ext = fileName.substring(fileName.lastIndexOf('.'));
                            outFile = new File(saveDir, baseName + "(" + counter + ")" + ext);
                            counter++;
                        }

                        FileOutputStream fos = new FileOutputStream(outFile);
                        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        long received = 0;
                        int bytesRead;

                        while (received < fileSize) {
                            int toRead = (int) Math.min(BUFFER_SIZE, fileSize - received);
                            bytesRead = dis.read(buffer, 0, toRead);
                            if (bytesRead == -1) break;
                            bos.write(buffer, 0, bytesRead);
                            received += bytesRead;

                            int progress = (int) ((received * 100) / fileSize);
                            if (progress % 10 == 0) {
                                updateReceiveItem(index, "Receiving...", String.valueOf(progress));
                            }
                        }
                        bos.flush();
                        bos.close();
                        fos.close();

                        final String savedPath = outFile.getAbsolutePath();
                        MAIN_HANDLER.post(new Runnable() {
                            @Override
                            public void run() {
                                if (index < TransferStateManager.receive_trans_map.size()) {
                                    TransferStateManager.receive_trans_map.get(index).put("video_path", savedPath);
                                    TransferStateManager.receive_trans_map.get(index).put("status", "Received ✓");
                                    TransferStateManager.receive_trans_map.get(index).put("progress", "100");
                                    TransferStateManager.notifyReceiveUpdated();
                                }
                            }
                        });
                    }

                    // 5. Confirm completion
                    dos.writeUTF("ALL_DONE");
                    dos.flush();
                    postToast(activity, "All files received!");

                } catch (SocketTimeoutException e) {
                    postToast(activity, "No sender connected within 2 minutes");
                } catch (Exception e) {
                    e.printStackTrace();
                    postToast(activity, "Receive error: " + e.getMessage());
                } finally {
                    closeQuietly(dis);
                    closeQuietly(dos);
                    closeQuietly(socket);
                    closeQuietly(serverSocket);
                }
            }
        }).start();
    }

    // ─── SENDER: Connect with retry then stream ──────────────────────────────
    public static void startSending(final String receiverIp, final String jsonPayload, final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                DataOutputStream dos = null;
                DataInputStream dis = null;

                try {
                    // Retry connection up to 15 times (receiver might still be starting)
                    socket = connectWithRetry(receiverIp, PORT, 15, 1000);
                    if (socket == null) {
                        postToast(activity, "Could not connect to " + receiverIp + " after retries");
                        updateAllSendStatus("Connection failed");
                        return;
                    }

                    socket.setSoTimeout(30000);
                    dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                    // 1. Send metadata JSON
                    dos.writeUTF(jsonPayload);
                    dos.flush();

                    // 2. Wait for READY signal
                    String response = dis.readUTF();
                    if (!"READY".equals(response)) {
                        postToast(activity, "Receiver not ready");
                        updateAllSendStatus("Receiver rejected");
                        return;
                    }

                    JSONArray jsonArray = new JSONArray(jsonPayload);

                    // 3. Send each file
                    for (int i = 0; i < TransferStateManager.send_trans_list.size(); i++) {
                        HashMap<String, Object> item = TransferStateManager.send_trans_list.get(i);
                        String filePath = (String) item.get("video_path");
                        File file = new File(filePath);

                        if (!file.exists()) {
                            updateSendItem(i, "File not found", "0");
                            dos.writeLong(0); // Signal: no data
                            dos.flush();
                            continue;
                        }

                        long fileSize = file.length();
                        dos.writeLong(fileSize);
                        dos.flush();

                        FileInputStream fis = new FileInputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        long sent = 0;
                        int bytesRead;

                        updateSendItem(i, "Sending...", "0");

                        while ((bytesRead = bis.read(buffer)) != -1) {
                            dos.write(buffer, 0, bytesRead);
                            sent += bytesRead;

                            int progress = (int) ((sent * 100) / fileSize);
                            if (progress % 10 == 0) {
                                updateSendItem(i, "Sending...", String.valueOf(progress));
                            }
                        }
                        dos.flush();
                        bis.close();
                        fis.close();

                        updateSendItem(i, "Sent ✓", "100");
                    }

                    // 4. Wait for confirmation
                    String confirmation = dis.readUTF();
                    if ("ALL_DONE".equals(confirmation)) {
                        postToast(activity, "Transfer complete!");
                    }

                } catch (ConnectException e) {
                    postToast(activity, "Connection refused. Is receiver listening?");
                    updateAllSendStatus("Connection refused");
                } catch (Exception e) {
                    e.printStackTrace();
                    postToast(activity, "Send error: " + e.getMessage());
                    updateAllSendStatus("Error: " + e.getMessage());
                } finally {
                    closeQuietly(dis);
                    closeQuietly(dos);
                    closeQuietly(socket);
                }
            }
        }).start();
    }

    // ─── CONNECTION RETRY HELPER ─────────────────────────────────────────────
    private static Socket connectWithRetry(String ip, int port, int maxRetries, int delayMs) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(ip, port), 3000);
                return s;
            } catch (IOException e) {
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException ignored) {
                }
            }
        }
        return null;
    }

    // ─── UI UPDATE HELPERS (run on main thread) ──────────────────────────────
    private static void updateSendItem(final int index, final String status, final String progress) {
        MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                if (index >= 0 && index < TransferStateManager.send_trans_list.size()) {
                    TransferStateManager.send_trans_list.get(index).put("status", status);
                    TransferStateManager.send_trans_list.get(index).put("progress", progress);
                    TransferStateManager.notifySendUpdated();
                }
            }
        });
    }

    private static void updateReceiveItem(final int index, final String status, final String progress) {
        MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                if (index >= 0 && index < TransferStateManager.receive_trans_map.size()) {
                    TransferStateManager.receive_trans_map.get(index).put("status", status);
                    TransferStateManager.receive_trans_map.get(index).put("progress", progress);
                    TransferStateManager.notifyReceiveUpdated();
                }
            }
        });
    }

    private static void updateAllSendStatus(final String status) {
        MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                for (HashMap<String, Object> map : TransferStateManager.send_trans_list) {
                    map.put("status", status);
                }
                TransferStateManager.notifySendUpdated();
            }
        });
    }

    private static void postToast(final Activity activity, final String message) {
        MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                android.widget.Toast.makeText(activity, message, android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {
            }
        }
    }
}