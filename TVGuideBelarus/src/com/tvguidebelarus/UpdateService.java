package com.tvguidebelarus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

@SuppressLint("NewApi")
public class UpdateService extends IntentService {

	public static final String ACTION_COMPLETE = "com.example.service.updateservice.action.COMPLETE";

	public UpdateService() {
		super("UpdateService");
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("NewApi")
	@Override
	protected void onHandleIntent(Intent i) {
		// TODO Auto-generated method stub
		// Downloading a file
		Log.i("DEBUG", "Starting service.");

		try {
			
			File root = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			
			//File root = new File(getApplicationInfo().dataDir + "/assets/");
					
			root.mkdirs();
			File output = new File(root, i.getData().getLastPathSegment());

			if (output.exists()) {
				output.delete();
			}

			URL url = new URL(i.getData().toString());

			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setReadTimeout(15000);
			c.connect();
			FileOutputStream fos = new FileOutputStream(output.getPath());
			BufferedOutputStream out = new BufferedOutputStream(fos);

			try {
				InputStream in = c.getInputStream();
				byte[] buffer = new byte[8192];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.flush();
				
			} finally {
				fos.getFD().sync();
				out.close();
			}
			
			sendBroadcast(new Intent(ACTION_COMPLETE));
			Log.i("DEBUG", "Downloading completed.");
		} catch (IOException e2) {
			Log.e(getClass().getName(), "Exception in download", e2);
			Log.i("DEBUG", e2.getMessage());
		}

	}

}
