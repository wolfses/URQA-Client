package com.urqa.Collector;

import java.text.SimpleDateFormat;

import android.content.Context;

public class DateCollector {
	
	public static String GetDateYYMMDDHHMMSS(Context context){
		java.util.Date currenttime = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss",  context.getResources( ).getConfiguration( ).locale);
		String time = formatter.format(currenttime);
		return time;
	}
}
