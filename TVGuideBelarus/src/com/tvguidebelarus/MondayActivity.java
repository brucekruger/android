package com.tvguidebelarus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MondayActivity extends Activity {
	
	public static final String EXTRA_DAY = "day";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/*Intent i = new Intent(this, MainActivity.class);
		
		i.putExtra(MainActivity.EXTRA_DAY, 1);
		
		startActivity(i);*/
		
		Log.i("DEBUG", "First tab is created!");
	}
}
