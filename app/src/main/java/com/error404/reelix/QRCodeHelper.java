package com.error404.reelix;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeHelper {

    /**
     * Converts a text string into a native Android Bitmap using ZXing.
     * * @param data The string to encode (e.g., SSID and connection info).
     * @param width The width of the output Bitmap.
     * @param height The height of the output Bitmap.
     * @return The generated QR Code Bitmap, or null if an error occurs.
     */
    public static Bitmap generateQRCodeBitmap(String data, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1); // Compact padding for high readability
            
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                data, BarcodeFormat.QR_CODE, width, height, hints
            );
            
            int matrixWidth = bitMatrix.getWidth();
            int matrixHeight = bitMatrix.getHeight();
            int[] pixels = new int[matrixWidth * matrixHeight];
            
            for (int y = 0; y < matrixHeight; y++) {
                int offset = y * matrixWidth;
                for (int x = 0; x < matrixWidth; x++) {
                    // 0xFF000000 is Black (QR dots), 0xFFFFFFFF is White (Background)
                    pixels[offset + x] = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                }
            }
            
            Bitmap bitmap = Bitmap.createBitmap(matrixWidth, matrixHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, matrixWidth, 0, 0, matrixWidth, matrixHeight);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}