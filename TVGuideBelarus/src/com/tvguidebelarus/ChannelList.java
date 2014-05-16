package com.tvguidebelarus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListFragment;

@SuppressLint("ValidFragment")
public class ChannelList extends SherlockListFragment {
	
	String data[] = new String[] { "one", "two", "three", "four", "five", "six", "seven" };
	
	public ChannelList()
	{
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated constructor stub
		super.onActivityCreated(savedInstanceState);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_list_item_1, data);
	    setListAdapter(adapter);
	}
	
}
