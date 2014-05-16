package com.tvguidebelarus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.entity.Channel;

public class ChannelFragment extends SherlockListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		ChannelAdapter channels = (ChannelAdapter) getListAdapter();
		
		Channel selected = channels.getItem(position);
		
		int channelId = selected.getId();
		
		//MainActivity activity = (MainActivity) getActivity();
		
		//int day = activity.GetDay();
		
		Intent i = new Intent(getActivity(), MainActivity.class);
		
		//i.putExtra(MainActivity.EXTRA_DAY, day);
		i.putExtra(MainActivity.EXTRA_ID, channelId);
		
		startActivity(i);
	}
}
