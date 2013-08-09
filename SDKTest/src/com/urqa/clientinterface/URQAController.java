package com.urqa.clientinterface;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.urqa.common.Network;
import com.urqa.common.StateData;
import com.urqa.eventpath.EventPathManager;
import com.urqa.rank.ErrorRank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

public final class URQAController {
	
	private static final Gson gson = new Gson();
	
	@SuppressLint("NewApi")
	public static void InitializeAndStartSession(Context context, String APIKEY)
	{
		//test code 
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		if(StateData.FirstConnect)
		{
			StateData.AppContext 	= context;
			StateData.FirstConnect 	= false;
			StateData.APIKEY 		= APIKEY;

			class SessionID extends Network
			{
				@Override
				public void CallbackFunction(HttpResponse responseGet ,HttpEntity resEntity)
				{
					String jsondata = "";
					try {
						jsondata = EntityUtils.toString(resEntity);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String sessionID = "";
					StateData.SessionID =gson.fromJson(jsondata, String.class);
				}
			}
			SessionID getID = new SessionID();
			getID.SetNetwork("http://172.16.100.67:9000/urqa/client/connect", "", Network.Networkformula.GET);
			getID.start();

		}
		
		StartActivity(context);
	}
	public static void StartActivity(Context context)
	{
		//이벤트 패스 클리어
		EventPathManager.ClearEvent();
	}
	
	public static void EndActivity(Context context)
	{
		//SendEvent
	}

	class SendData
	{
		public String 	    sdkversion; 
		public String 	    appversion;
		public String 		osversion ;
		public String 	    appmemmax ;
		public String 	    appmemavail;
		public String 	    appmemtotal;
		public String 	    country;
		public String 	    date;
		public String 	    locale;
		public String 	    mobileon;
		public String 	    gpson;
		public String 	    wifion ;
		public String 	    device ;
		public String 	    rooted ;
		public String 	    scrheight;
		public String 	    scrwidth ;
		public String 	    srcorientation; 
		public String 	    sysmemlow ;

	}

	public static void SendException(Exception e, String Tag, ErrorRank rank)
	{
		//SendData senddata = new SendData();
/*
		senddata.sdkversion = 
		senddata.appversion = 
		senddata.osversion = osversion;
		senddata.appmemmax = appmemmax;
		senddata.appmemavail = appmemavail;
		senddata.appmemtotal = appmemtotal;
		senddata.country = country;
		senddata.date = date;
		senddata.locale = locale;
		senddata.mobileon = mobileon;
		senddata.gpson = gpson;
		senddata.wifion = wifion;
		senddata.device = device;
		senddata.rooted = rooted;
		senddata.scrheight = scrheight;
		senddata.scrwidth = scrwidth;
		senddata.srcorientation = srcorientation;
		senddata.sysmemlow = sysmemlow;
		*/
		
	}
	public static void SendException(Exception e)
	{
		SendException(e, null, ErrorRank.Nothing);
	}
	public static void SendException(Exception e, String Tag)
	{
		SendException(e, Tag, ErrorRank.Nothing);
	}

	
	public static void SetLogCat(boolean toggleLog)
	{
		StateData.ToggleLogCat = toggleLog;
	}

	public static void SetLogging(int Line, String Filter)
	{
		StateData.TransferLog = true;
		StateData.LogLine = Line;
		StateData.LogFilter = Filter;
	}
	public static void SetLogging(int Line)
	{
		StateData.TransferLog = true;
		StateData.LogLine = Line;
	}
	
	
	public static  int v(String tag, String Msg, Throwable tr)
	{
		return log(LogLevel.Verbose,tag,Msg,tr);
	}
	public static  int v(String tag, String Msg)
	{
		return log(LogLevel.Verbose,tag,Msg,null);
	}
	public static  int d(String tag, String Msg, Throwable tr)
	{
		return log(LogLevel.Debug,tag,Msg,tr);
	}
	public static  int d(String tag, String Msg)
	{
		return log(LogLevel.Debug,tag,Msg,null);
	}
	public static int i(String tag, String Msg, Throwable tr)
	{
		return log(LogLevel.Info,tag,Msg,tr);
	}
	public static  int i(String tag, String Msg)
	{
		return log(LogLevel.Info,tag,Msg,null);
	}
	public static  int w(String tag, String Msg, Throwable tr)
	{
		return log(LogLevel.Warning,tag,Msg,tr);
	}
	public static  int w(String tag, String Msg)
	{
		return log(LogLevel.Warning,tag,Msg,null);
	}
	public static  int e(String tag, String Msg, Throwable tr)
	{
		return log(LogLevel.Error,tag,Msg,tr);
	}
	public static  int e(String tag, String Msg)
	{
		return log(LogLevel.Error,tag,Msg,null);
	}
	
	enum LogLevel
	{
		Verbose,Debug,Info,Warning,Error
	}
	
	private static int loglevel(LogLevel level, String tag,String Msg, Throwable tr)
	{
		if (tr != null) 
		{
			switch (level) {
			case Verbose:
				return Log.v(tag, Msg, tr);
			case Debug:
				return Log.d(tag, Msg, tr);
			case Info:
				return Log.i(tag, Msg, tr);
			case Warning:
				return Log.w(tag, Msg, tr);
			case Error:
				return Log.e(tag, Msg, tr);
			default:
				return 0;
			}
		}
		else
		{
			switch (level) 
			{
			case Verbose:
				return Log.v(tag, Msg);
			case Debug:
				return Log.d(tag, Msg);
			case Info:
				return Log.i(tag, Msg);
			case Warning:
				return Log.w(tag, Msg);
			case Error:
				return Log.e(tag, Msg);
			default:
				return 0;
			}
		}
		
	}
	
	private static int log(LogLevel level, String tag,String Msg, Throwable tr)
	{
		EventPathManager.CreateEventPath(2);
		
		if(StateData.ToggleLogCat)
			return loglevel(level,tag,Msg,tr);
		else
			return 0;
	}
	
}
