package com.example.vacancies;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.entity.Vacancy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DetailsFragment extends Fragment {

	private static final String DEBUG_TAG = "DEBUG";
	
	private Vacancy current;

	private TextView tv_Name_Vacancy;
	private TextView tv_Id_Vacancy;
	private TextView tv_Published_At_Vacancy;
	private TextView tv_Region_Vacancy;
	private TextView tv_Salary_Vacancy;
	private TextView tv_Employer_Vacancy;
	private ImageView img_Logo_Vacancy;
	private TextView tv_Type_Vacancy;
	private Button btn_browse;

	public DetailsFragment(Vacancy item) {
		// TODO Auto-generated constructor stub
		this.current = item;
	}
	
	public void SetItem(Vacancy item) {
		this.current = item;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.details_item, container, false);

		tv_Name_Vacancy = (TextView) view.findViewById(R.id.tv_name_vacancy);
		tv_Name_Vacancy.setText(current.getName());

		tv_Id_Vacancy = (TextView) view.findViewById(R.id.tv_id_vacancy);
		;
		tv_Id_Vacancy.setText(current.getId());

		tv_Published_At_Vacancy = (TextView) view
				.findViewById(R.id.tv_publicshed_at_vacancy);
		tv_Published_At_Vacancy.setText(current.getPublished_at());

		tv_Region_Vacancy = (TextView) view
				.findViewById(R.id.tv_region_vacancy);
		tv_Region_Vacancy.setText(current.getArea().getName());

		tv_Salary_Vacancy = (TextView) view
				.findViewById(R.id.tv_salary_vacancy);
		tv_Salary_Vacancy.setText(current.getSalary().getFrom() + "-"
				+ current.getSalary().getTo() + " "
				+ current.getSalary().getCurrency());

		tv_Employer_Vacancy = (TextView) view
				.findViewById(R.id.tv_employer_vacancy);
		tv_Employer_Vacancy.setText("Наниматель: "
				+ current.getEmployer().getName());

		img_Logo_Vacancy = (ImageView) view.findViewById(R.id.img_logo_vacancy);
		String logo_url = current.getEmployer().getLogo_urls().get240();

		InputStream is = null;
		// Only display the first 512000 characters of the retrieved
		// web page content.
		int len = 512000;
		URL url;
		int response;
		
		try {
			url = new URL(logo_url);
			HttpURLConnection conn;
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(10000 /* milliseconds */);
				conn.setConnectTimeout(15000 /* milliseconds */);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				// Starts the query
				conn.connect();
				
				try {
					response = conn.getResponseCode();
					Log.d(DEBUG_TAG, "The response is: " + response);
					is = conn.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		img_Logo_Vacancy.setImageDrawable(Drawable.createFromStream(is, "logo"));

		tv_Type_Vacancy = (TextView) view.findViewById(R.id.tv_type_vacancy);
		tv_Type_Vacancy.setText(current.getType().getName());

		btn_browse = (Button) view.findViewById(R.id.btn_browse);
		btn_browse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Starting WebView activity
				Intent i = new Intent(getActivity().getApplicationContext(), WebActivity.class);
				i.putExtra(WebActivity.EXTRA_URL, current.getAlternate_url());
				startActivity(i);
			}
		});

		return view;
	}
}
