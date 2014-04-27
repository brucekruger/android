package com.tvguidebelarus;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
		
		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("tag1");
		
		spec.setContent(R.id.tab1);
		spec.setIndicator("Mo");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Tu");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("We");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Th");
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag5");
		spec.setContent(R.id.tab5);
		spec.setIndicator("Fr");
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag6");
		spec.setContent(R.id.tab6);
		spec.setIndicator("Sa");
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag7");
		spec.setContent(R.id.tab7);
		spec.setIndicator("Su");
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
}
