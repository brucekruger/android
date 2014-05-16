package com.utils;

import java.util.ArrayList;
import com.entity.Channel;
import com.entity.Programme;

public interface XMLParser {
	ArrayList<Channel> parsedchannels();
	ArrayList<Programme> parsedprogrammes();
	ArrayList<Programme> programmesbychanneldate(int Day, int ChannelId);
}



