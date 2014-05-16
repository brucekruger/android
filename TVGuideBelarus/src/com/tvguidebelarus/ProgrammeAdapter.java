package com.tvguidebelarus;

import java.util.ArrayList;

import com.entity.Programme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProgrammeAdapter extends BaseAdapter {

	private ArrayList<Programme> data;
	private Context context;

	public ProgrammeAdapter(ArrayList<Programme> data, Context context) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public ArrayList<Programme> getChannels() {
		return data;
	}

	@Override
	public Programme getItem(int position) {
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
					R.layout.programme_list_item, parent, false);

			TextView Time = (TextView) convertView.findViewById(R.id.tv_Time);
			
			TextView Name = (TextView) convertView.findViewById(R.id.tv_Name);

			ViewHolder vh = (ViewHolder) convertView.getTag();
			
			if (vh == null) {
				vh = new ViewHolder(Time, Name);
				convertView.setTag(vh);
			}

			Programme pg = data.get(position);
			
			StringBuilder time = new StringBuilder(); 
			
			int hours = pg.getTime().getHours();
			
			if (hours > 9)
				time.append(String.valueOf(hours));
			else
				time.append("0" + String.valueOf(hours)); 
			
			time.append(':');
			
			int minutes = pg.getTime().getMinutes();
			
			if (minutes > 9)
				time.append(String.valueOf(minutes));
			else
				time.append("0" + String.valueOf(minutes)); 
			
			String name = pg.getName();
			
			vh.Time.setText(time);
			vh.Name.setText(name);

			convertView.setTag(vh);

		}
		return convertView;
	}

	private class ViewHolder {

		public final TextView Time;
		public final TextView Name;

		public ViewHolder(TextView Time, TextView Name) {
			this.Time = Time;
			this.Name = Name;
		}
	}

}
