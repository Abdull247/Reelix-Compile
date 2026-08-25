package com.error404.reelix;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.io.InputStream;
import java.net.URL;

public class FCMService extends FirebaseMessagingService {

	private static final String CHANNEL_ID = "default_channel";

	@Override
	public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);

		String title = "";
		String body = "";
		String imageUrl = null;

		// Reading from getData() now instead of getNotification(), since the
		// backend sends data-only messages so this method always fires.
		if (remoteMessage.getData() != null) {
			title = remoteMessage.getData().get("title");
			body = remoteMessage.getData().get("body");
			imageUrl = remoteMessage.getData().get("imageUrl");
		}

		showNotification(title, body, imageUrl);
	}

	@Override
	public void onNewToken(@NonNull String token) {
		super.onNewToken(token);
		// Optional: send updated token to your backend here if you track tokens per-user
	}

	private void showNotification(String title, String body, String imageUrl) {
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(
				CHANNEL_ID,
				"General Notifications",
				NotificationManager.IMPORTANCE_HIGH
			);
			notificationManager.createNotificationChannel(channel);
		}

		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		int pendingIntentFlags = PendingIntent.FLAG_UPDATE_CURRENT;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			pendingIntentFlags |= PendingIntent.FLAG_IMMUTABLE;
		}

		PendingIntent pendingIntent = PendingIntent.getActivity(
			this,
			0,
			intent,
			pendingIntentFlags
		);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
			.setSmallIcon(R.drawable.icon)
			.setContentTitle(title)
			.setContentText(body)
			.setAutoCancel(true)
			.setPriority(NotificationCompat.PRIORITY_HIGH)
			.setContentIntent(pendingIntent);

		// If there's an image, load it and show as a big picture notification
		if (imageUrl != null && !imageUrl.isEmpty()) {
			try {
				Bitmap bitmap = getBitmapFromUrl(imageUrl);
				if (bitmap != null) {
					builder.setLargeIcon(bitmap);
					builder.setStyle(new NotificationCompat.BigPictureStyle()
						.bigPicture(bitmap)
						.bigLargeIcon((Bitmap) null));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		notificationManager.notify((int) System.currentTimeMillis(), builder.build());
	}

	private Bitmap getBitmapFromUrl(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			InputStream input = url.openStream();
			return BitmapFactory.decodeStream(input);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
