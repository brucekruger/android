package com.example.vacancies;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.entity.Area;
import com.example.entity.Employer;
import com.example.entity.Logo_urls;
import com.example.entity.Salary;
import com.example.entity.Type;
import com.example.entity.Vacancy;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {

	private static final String DEBUG_TAG = "DEBUG";
	private static final String INFO_TAG = "INFO";
	public static final String EXTRA_ID = "id";

	private static String NetErrorMsg = "Не удалось подключиться к сети";

	private static final String[] items = { "Опыт работы", "Тип занятости",
			"График работы", "Регион", "Ветка или станция метро",
			"Профобласть или специализация", "Идентификатор [компании]",
			"Код валюты", "Размер заработной платы", "За период" };

	private static final String[] keys = { "experience", "employment",
			"schedule", "area", "metro", "specialization", "employer_id",
			"currency", "salary", "period" };

	private static String key = "";

	private static ArrayList<Vacancy> vacancies;

	private static ArrayList<Vacancy> buffer;

	TextView tv_Title;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ArrayList<Vacancy> data = (ArrayList<Vacancy>) getLastCustomNonConfigurationInstance();

		if (data != null) {
			// Loading JSON data
			VacancyAdapter adapter = new VacancyAdapter(R.layout.list_item,
					data, getApplicationContext());

			if (getSupportFragmentManager().findFragmentById(
					R.id.fragment_container) != null) {

				VacancyFragment vacancyFraq = new VacancyFragment();
				vacancyFraq.setListAdapter(adapter);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.fragment_container, vacancyFraq).commit();
			}
		}

		tv_Title = (TextView) findViewById(R.id.tv_Title);

		Spinner spin = (Spinner) findViewById(R.id.sp_Category);
		final ArrayAdapter<String> categories = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		categories
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(categories);

		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				key = keys[position];

				Toast.makeText(getApplicationContext(),
						"Поиск по категории '" + items[position] + "'",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				key = keys[0];
			}
		});

		final EditText et_Search = (EditText) findViewById(R.id.et_SearchText);

		Button btn_Search = (Button) findViewById(R.id.btn_Search);

		btn_Search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String input = et_Search.getEditableText().toString();

				if (input == "") {
					Toast.makeText(getApplicationContext(),
							"Введите что-нибудь в строку поиска!",
							Toast.LENGTH_LONG).show();
					return;
				}

				Toast.makeText(getApplicationContext(),
						"Попытка доступа к сети", Toast.LENGTH_LONG).show();

				ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					// fetch data

					// Попытка получить json с вакансией оказывается неудачной

					// String stringUrl = "https://api.hh.ru/vacancies/3983946";

					/*
					 * String stringUrl = "https://api.hh.ru/vacancies?period="
					 * + input;
					 */

					String stringUrl = "https://api.hh.ru/vacancies?per_page=5&?"
							+ key + "=" + input;

					DownloadWebpageTask downloader = new DownloadWebpageTask();
					downloader.execute(stringUrl);

					try {
						String json_source = downloader.get();

						JSONObject json = new JSONObject(json_source);
						JSONArray lineItems = json.getJSONArray("items");
						JSONObject subjson = new JSONObject();

						vacancies = new ArrayList<Vacancy>();

						for (int i = 0; i < lineItems.length(); i++) {
							json = lineItems.getJSONObject(i);

							Vacancy vacancy = new Vacancy();

							vacancy.setId(json.getString("id"));

							vacancy.setPremium(Boolean.valueOf(json
									.getString("premium")));

							vacancy.setAlternate_url(json
									.getString("alternate_url"));

							Salary salary = new Salary();
							if (json.isNull("salary") == false) {
								subjson = json.getJSONObject("salary");
								salary.setTo(subjson.get("to"));
								salary.setFrom(Integer.getInteger(
										subjson.getString("from"), 0));
								salary.setCurrency(subjson
										.getString("currency"));
							}
							vacancy.setSalary(salary);

							vacancy.setId(json.getString("name"));

							Area area = new Area();
							if (json.isNull("area") == false) {
								subjson = json.getJSONObject("area");
								area.setId(json.getString("id"));
								area.setName(json.getString("name"));
								area.setUrl(json.getString("url"));
							}
							vacancy.setArea(area);

							vacancy.setUrl(json.getString("url"));

							vacancy.setPublished_at(json
									.getString("published_at"));

							Employer employer = new Employer();
							if (json.isNull("employer") == false) {
								subjson = json.getJSONObject("employer");

								employer.setId(subjson.getString("id"));
								employer.setName(subjson.getString("name"));
								employer.setUrl(subjson.getString("url"));
								employer.setAlternate_url(subjson
										.getString("alternate_url"));

								Logo_urls logo_urls = new Logo_urls();

								if (json.getJSONObject("employer").isNull("logo_urls") == false) {
									subjson = json.getJSONObject("employer").getJSONObject("logo_urls");
									logo_urls.set90(subjson.getString("90"));
									logo_urls.set240(subjson.getString("240"));
									logo_urls.setOriginal(subjson
											.getString("original"));
								}
								employer.setLogo_urls(logo_urls);
							}
							vacancy.setEmployer(employer);

							vacancy.setResponse_letter_required(Boolean.valueOf(json
									.getString("response_letter_required")));

							Type type = new Type();
							if (json.getJSONObject("type") != null) {
								subjson = json.getJSONObject("type");
								type.setId(subjson.getString("id"));
								type.setName(subjson.getString("name"));
							}
							vacancy.setType(type);

							vacancies.add(vacancy);
						}

						buffer = new ArrayList<Vacancy>(vacancies);

						VacancyAdapter adapter = new VacancyAdapter(
								R.layout.list_item, vacancies,
								getApplicationContext());

						if (getSupportFragmentManager().findFragmentById(
								R.id.fragment_container) == null) {

							VacancyFragment vacancyFraq = new VacancyFragment();
							vacancyFraq.setListAdapter(adapter);
							getSupportFragmentManager().beginTransaction()
									.add(R.id.fragment_container, vacancyFraq)
									.commit();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i(DEBUG_TAG, e.getMessage());
						Toast.makeText(getApplicationContext(), NetErrorMsg,
								Toast.LENGTH_LONG).show();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i(DEBUG_TAG, e.getMessage());
						Toast.makeText(getApplicationContext(), NetErrorMsg,
								Toast.LENGTH_LONG).show();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i(DEBUG_TAG, e.getMessage());
						Toast.makeText(getApplicationContext(), NetErrorMsg,
								Toast.LENGTH_LONG).show();
					}

				} else {
					// display error

					/*
					 * Toast.makeText(getApplicationContext(),
					 * "Не удалось подключиться к сети", Toast.LENGTH_LONG)
					 * .show();
					 */

					return;
				}
			}
		});

		int vacancyId = getIntent().getIntExtra(EXTRA_ID, -1);

		if (vacancyId != -1) {

			if (getSupportFragmentManager().findFragmentById(
					R.id.fragment_container) == null) {

				Vacancy selected = buffer.get(vacancyId);
				
				DetailsFragment detailsFraq = new DetailsFragment(selected);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.fragment_container, detailsFraq)
						.commit();
			}

			return;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				return downloadUrl(urls[0]);
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {

			// tv_Title.setText(result);
		}
	}

	private String downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		// Only display the first 512000 characters of the retrieved
		// web page content.
		int len = 512000;

		try {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			// Starts the query
			conn.connect();
			int response = conn.getResponseCode();
			Log.d(DEBUG_TAG, "The response is: " + response);
			is = conn.getInputStream();

			// Convert the InputStream into a string
			String contentAsString = readIt(is, len);
			Log.i(INFO_TAG, contentAsString);
			return contentAsString;

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		// TODO Auto-generated method stub
		Log.i(INFO_TAG, "Saving data!");

		if (buffer == null)
			return null;

		for (Vacancy v : buffer)
			Log.i(INFO_TAG, v.getId());

		final ArrayList<Vacancy> data = new ArrayList<Vacancy>(buffer);

		return data;
	}
}
