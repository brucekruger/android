package com.example.vacancies;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.example.entity.Vacancy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class VacancyAdapter extends ArrayAdapter {

	private ArrayList<Vacancy> data;
	private Context context;

	private static final String DEBUG_TAG = "DEBUG";

	@SuppressWarnings("unchecked")
	public VacancyAdapter(int textViewResourceId, ArrayList<Vacancy> objects,
			Context context) {
		// TODO Auto-generated constructor stub
		super(context, textViewResourceId, objects);

		this.data = objects;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Vacancy getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item, parent, false);

			TextView tv_PublishDate = (TextView) convertView
					.findViewById(R.id.tv_PubishDate);

			TextView tv_VacancyName = (TextView) convertView
					.findViewById(R.id.tv_VacancyName);

			ImageView img_Logo = (ImageView) convertView
					.findViewById(R.id.img_Logo);

			/*
			 * Ion.with(icon) .placeholder(R.drawable.owner_placeholder)
			 * .resize(size, size) .centerCrop() .error(R.drawable.owner_error)
			 * .load(item.getAsJsonObject("owner").get("profile_image")
			 * .getAsString());
			 */

			TextView tv_Employer = (TextView) convertView
					.findViewById(R.id.tv_Employer);

			ViewHolder vh = new ViewHolder(tv_PublishDate, tv_VacancyName,
					img_Logo, tv_Employer);

			convertView.setTag(vh);
		}

		ViewHolder vh = (ViewHolder) convertView.getTag();

		vh.tv_PublishDate.setText(data.get(position).getPublished_at());
		vh.tv_VacancyName.setText(data.get(position).getName());

		/*
		 * vh.img_Logo.setImageDrawable(context.getResources().getDrawable(
		 * R.drawable.ic_launcher));
		 */

		String logo_url = data.get(position).getEmployer().getLogo_urls().get90();

		InputStream is = null;
		// Only display the first 512000 characters of the retrieved
		// web page content.
		int len = 512000;
		int response;

		try {
			URL url = new URL(logo_url);
			HttpURLConnection conn;

			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			// Starts the query
			conn.connect();

			response = conn.getResponseCode();
			Log.d(DEBUG_TAG, "The response is: " + response);
			is = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		vh.img_Logo.setImageDrawable(Drawable.createFromStream(is, "logo"));

		vh.tv_Employer.setText(data.get(position).getEmployer().getName());

		return convertView;
	}

	private class ViewHolder {

		public final TextView tv_PublishDate;

		public final TextView tv_VacancyName;

		public final ImageView img_Logo;

		public final TextView tv_Employer;

		public ViewHolder(TextView tv_PublishDate, TextView tv_VacancyName,
				ImageView img_Logo, TextView tv_Employer) {
			this.tv_PublishDate = tv_PublishDate;
			this.tv_VacancyName = tv_VacancyName;
			this.img_Logo = img_Logo;
			this.tv_Employer = tv_Employer;
		}
	}

}
