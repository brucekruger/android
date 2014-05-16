package com.tvguidebelarus;

import java.util.ArrayList;

import com.entity.Channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChannelAdapter extends BaseAdapter {

	private ArrayList<Channel> data;
	//public NoteFilter filter;
	private Context context;

	public ChannelAdapter(ArrayList<Channel> data, Context context) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public ArrayList<Channel> getChannels() {
		return data;
	}

	@Override
	public Channel getItem(int position) {
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
					R.layout.channel_list_item, parent, false);

			TextView Name = (TextView) convertView.findViewById(R.id.tv_Name);

			ViewHolder vh = (ViewHolder) convertView.getTag();
			
			if (vh == null) {
				vh = new ViewHolder(Name);
				convertView.setTag(vh);
			}

			Channel ch = data.get(position);
			
			String name = ch.getName();
			
			vh.Name.setText(name);

			convertView.setTag(vh);

		}
		return convertView;
	}

	private class ViewHolder {

		public final TextView Name;

		public ViewHolder(TextView Name) {

			this.Name = Name;
		}
	}
}
