package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.annotation.SuppressLint;
import android.util.Log;

import com.entity.Channel;
import com.entity.Programme;

@SuppressLint("NewApi")
public class Dom implements XMLParser {

	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;

	private Channel channel;
	private ArrayList<Channel> ChannelList; 

	private Programme programme;
	private ArrayList<Programme> ProgrammeList = new ArrayList<Programme>();

	public Dom(InputStream stream) {
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			try {
				document = builder.parse(stream);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Channel> parsedchannels() {
		// TODO Auto-generated method stub

		NodeList nodeList = document.getElementsByTagName("channel");

		ChannelList = new ArrayList<Channel>();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;

				channel = new Channel();

				int id = Integer.parseInt(eElement.getAttribute("id"));
				channel.setId(id);

				String name = eElement.getElementsByTagName("display-name")
						.item(0).getTextContent();
				channel.setName(name);

				ChannelList.add(channel);
			}
		}

		Collections.sort(ChannelList, new ChannelComparator());
		
		return ChannelList;
	}

	@Override
	public ArrayList<Programme> parsedprogrammes() {
		// TODO Auto-generated method stub

		NodeList nodeList = document.getElementsByTagName("programme");

		// for(int i = 0; i<nodeList.getLength(); i++)
		for (int i = 0; i < 7; i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;

				programme = new Programme();

				String name = eElement.getElementsByTagName("title").item(0)
						.getTextContent();
				programme.setName(name);

				try {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					String datestr = eElement.getAttribute("start");
					Date startTime = formatter.parse(datestr);
					programme.setTime(startTime);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					Log.i("DEBUG", "Parsing date:" + e.getMessage());
					e.printStackTrace();
				}

				int channelId = Integer.parseInt(eElement
						.getAttribute("channel"));
				programme.setChannelId(channelId);

				ProgrammeList.add(programme);
			}
		}

		return ProgrammeList;
	}

	@Override
	public ArrayList<Programme> programmesbychanneldate(int Day, int ChannelId) {
		// TODO Auto-generated method stub
		NodeList nodeList = document.getElementsByTagName("programme");

		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) node;
				
				Date startTime = new Date();
				
				try {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					String datestr = eElement.getAttribute("start");
					startTime = formatter.parse(datestr);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					Log.i("DEBUG", "Parsing date:" + e.getMessage());
					e.printStackTrace();
				}
				
				int channelId = Integer.parseInt(eElement.getAttribute("channel"));
				
				if (startTime.getDay() == Day && channelId == ChannelId) {
					
					programme = new Programme();

					String name = eElement.getElementsByTagName("title")
							.item(0).getTextContent();
					
					programme.setName(name);
					programme.setTime(startTime);
					programme.setChannelId(channelId);

					ProgrammeList.add(programme);
				}
			}
		}
		
		return ProgrammeList;
	}
	
	public class ChannelComparator implements Comparator<Channel> {

		@Override
		public int compare(Channel ch1, Channel ch2) {
			// TODO Auto-generated method stub
			return ch1.getId() - ch2.getId();
		}
	}
	
	public ArrayList<Channel> GetChannels() {
		return ChannelList;
	}

	public ArrayList<Programme> GetProgrammes() {
		return ProgrammeList;
	}
}
