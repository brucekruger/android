package com.example.vacancies;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebActivity extends Activity {

	public static final String EXTRA_URL = "url";
	String url = "";
	private WebView Browser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		
		url = getIntent().getStringExtra(EXTRA_URL);
		
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
			
			Browser = (WebView)findViewById(R.id.wbv_browser);
			Browser.loadData(result, "text/html", null);
			
			Log.i("DEBUG", response.getStatusLine().toString());
			Log.i("DEBUG", responseString);
		}
	}
}
