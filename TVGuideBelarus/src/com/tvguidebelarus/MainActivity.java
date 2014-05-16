package com.tvguidebelarus;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.entity.Channel;
import com.entity.Programme;
import com.utils.Dom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends SherlockFragmentActivity {

	public static final Date currentDate = new Date(); 
	
	public static int DayToFragment = -1;
	
	ArrayList<Channel> channels;
	ArrayList<Programme> programmes;

	public static final String EXTRA_DAY = "day";
	public static final String EXTRA_ID = "id";
	
	TextView tv_Title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);

		tabs.setup();

		Intent intentMonday = new Intent().setClass(this, MondayActivity.class);
		TabHost.TabSpec spec = tabs.newTabSpec("tag1");

		spec.setContent(R.id.tab1);
		spec.setIndicator("Пн");
		//spec.setContent(intentMonday);
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Вт");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Ср");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Чт");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag5");
		spec.setContent(R.id.tab5);
		spec.setIndicator("Пт");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag6");
		spec.setContent(R.id.tab6);
		spec.setIndicator("Сб");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag7");
		spec.setContent(R.id.tab7);
		spec.setIndicator("Вс");
		tabs.addTab(spec);
	
		int currentday = currentDate.getDay() - 1;
		
		tabs.setCurrentTab(currentday);

		tv_Title = (TextView) findViewById(R.id.tv_Title);

		DateFormat dateFormat = new SimpleDateFormat("E - dd.MM.yyyy");
		
		tv_Title.setText(dateFormat.format(currentDate));
		
		int channelId = getIntent().getIntExtra(EXTRA_ID, -1);
		
		//int day = getIntent().getIntExtra(EXTRA_DAY, -1); 

		if (channelId != -1) {
			
			/*tabs = (TabHost) findViewById(android.R.id.tabhost);
			
			tabs.clearAllTabs();
			
			tabs.setup();
			
			//intentMonday = new Intent().setClass(this, MondayActivity.class);
			spec = tabs.newTabSpec("tag1");

			spec.setContent(R.id.tab1);
			spec.setIndicator("Пн");
			spec.setContent(intentMonday);
			tabs.addTab(spec);*/
			
			/*DayToFragment = tabs.getCurrentTab();
			
			if(day != -1)
			{
				ShowProgrammes(day, channelId);
				return;
			}*/
			
			ShowProgrammes(tabs.getCurrentTab(), channelId);
			return;
		}

		try {

			ParseChannelsXmlTask parser = new ParseChannelsXmlTask();
			parser.execute("program_xml.xml");
			ChannelAdapter channels = new ChannelAdapter(parser.get(), this);

			if (getSupportFragmentManager().findFragmentById(
					R.id.fragment_container) == null) {

				ChannelFragment channelFraq = new ChannelFragment();
				channelFraq.setListAdapter(channels);
				getSupportFragmentManager().beginTransaction()
						.add(R.id.fragment_container, channelFraq).commit();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("DEBUG", e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("DEBUG", e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	private class ParseChannelsXmlTask extends
			AsyncTask<String, Void, ArrayList<Channel>> {

		@Override
		protected ArrayList<Channel> doInBackground(String... paths) {
			// TODO Auto-generated method stub
			try {

				InputStream stream;
				stream = getAssets().open(paths[0]);
				Dom xdoc = new Dom(stream);

				return xdoc.parsedchannels();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("DEBUG", e.getMessage());
				return null;
			}
		}
	}

	private class ParseProgrammesXmlTask extends
			AsyncTask<String, Void, ArrayList<Programme>> {

		@Override
		protected ArrayList<Programme> doInBackground(String... paths) {
			// TODO Auto-generated method stub
			try {

				InputStream stream;
				stream = getAssets().open(paths[0]);
				Dom xdoc = new Dom(stream);

				/*
				 * Date date = new Date();
				 * 
				 * try { SimpleDateFormat formatter = new SimpleDateFormat(
				 * "yyyyMMddHHmmss"); String datestr = paths[1]; date =
				 * formatter.parse(datestr); } catch (ParseException e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); } catch
				 * (java.text.ParseException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); }
				 */

				int Day = Integer.parseInt(paths[1]);

				int channelId = Integer.parseInt(paths[2]);

				return xdoc.programmesbychanneldate(Day, channelId);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("DEBUG", e.getMessage());
				return null;
			}
		}
	}

	public void ShowProgrammes(int Day, int channelId) {
		/*tv_Title = (TextView) findViewById(R.id.tv_Title);

		tv_Title.setText("Программы");*/

		String id = String.valueOf(channelId);

		String day = String.valueOf(Day);

		ParseProgrammesXmlTask parser = new ParseProgrammesXmlTask();
		parser.execute("program_xml.xml", day, id);
		ProgrammeAdapter programmes = null;

		try {
			programmes = new ProgrammeAdapter(parser.get(), this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (getSupportFragmentManager().findFragmentById(
				R.id.fragment_container) == null) {

			ProgrammeFragment programmeFraq = new ProgrammeFragment();
			programmeFraq.setListAdapter(programmes);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, programmeFraq).commit();
			
			/*TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
			tabs.setup();
			tabs.setCurrentTab(Day);*/
		}
	}
	
	public int GetDay()
	{
		return DayToFragment;
	}
}
