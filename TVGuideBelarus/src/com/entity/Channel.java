package com.entity;

public class Channel {
	
	private int id;
	private String name;

	public Channel()
	{
	}
	
	public Channel(int Id, String Name)
	{
		this.id = Id;
		this.name = Name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int Id) {
		this.id = Id;
	}

	@Override
	public String toString() {
		return name;
	}
	
}

