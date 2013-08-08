package com.urqa.Collector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class LogCollector {
	
	public final static int 		LogLine 		= 100;
	public final static boolean 	TransferLog 	= true;
	public final static String 		LogFilter 		= "";
	
	public final static String getLog(Context context) {
	    StringBuilder LOGCAT_CMD = new StringBuilder();
	    LOGCAT_CMD.append("logcat").append("-d").append(LogFilter);
	    
	    Process logcatProc = null;
	    
	    try {
	        logcatProc = Runtime.getRuntime().exec(LOGCAT_CMD.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "";
	    }
	    BufferedReader reader = null;
	    StringBuilder strOutput = new StringBuilder();
	    ArrayList<String> LogList = new ArrayList<String>();
	    try {
	        reader = new BufferedReader(new InputStreamReader(logcatProc.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	LogList.add(line);
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    int LogLineStart = LogList.size() - LogLine;
	    if(LogLineStart < 0)
	    	LogLineStart = 0;
	     
	    int LogLineEnd = LogList.size();
	    
	    for(int i = LogLineStart ; i < LogLineEnd; i++)
	    {
	    	strOutput.append(LogList.get(i)).append("\n");
	    }
	    return strOutput.toString();
	}
	
}
