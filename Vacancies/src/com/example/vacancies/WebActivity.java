package com.example.vacancies;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.WebView;

public class WebActivity extends FragmentActivity {

	private static final String INFO_TAG = "INFO";
	private static String buffer = "";
	public static final String EXTRA_URL = "url";
	String url = "";
	private WebView Browser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		url = getIntent().getStringExtra(EXTRA_URL);

		String data = (String) getLastCustomNonConfigurationInstance();

		if (data != null) {
			Browser = (WebView) findViewById(R.id.wbv_browser);
			Browser.loadData(data, "text/html", null);
			return;
		}

		LoadData loader = new LoadData();
		loader.execute(url);
	}

	public class LoadData extends AsyncTask<String, Void, String> {

		String responseString;
		DefaultHttpClient client;
		HttpGet request;
		HttpResponse response;

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub

			client = new DefaultHttpClient();
			request = new HttpGet(urls[0]);
			responseString = null;

			try {

				response = client.execute(request);
				HttpEntity entity = response.getEntity();
				responseString = EntityUtils.toString(entity);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return responseString;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Browser = (WebView) findViewById(R.id.wbv_browser);
			Browser.loadData(result, "text/html", null);

			buffer = result;

			Log.i("DEBUG", response.getStatusLine().toString());
			Log.i("DEBUG", responseString);
		}
	}

	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		// TODO Auto-generated method stub
		Log.i(INFO_TAG, "Saving data!");

		if (buffer == null)
			return null;

		Log.i(INFO_TAG, buffer);

		final String data = buffer;

		return data;
	}
}
