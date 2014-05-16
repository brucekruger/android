package com.entity;

import java.util.Date;

public class Programme {
	
	private String name;
	private Date StartTime;
	private int ChannelId;

	public Programme()
	{
	}
	
	public Programme(String Name, Date Time, int channelId)
	{
		this.name = Name;
		this.StartTime = Time;
		this.ChannelId = channelId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTime() {
		return StartTime;
	}
	
	public void setTime(Date startTime) {
		this.StartTime = startTime;
	}
	
	public int getChannelId() {
		return ChannelId;
	}
	
	public void setChannelId(int channelId) {
		this.ChannelId = channelId;
	}

	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		result.append(name);
		result.append(" ");
		result.append(StartTime.getTime());
		
		return result.toString();
	}
	
}

