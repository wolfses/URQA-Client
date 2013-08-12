package com.urqa.clientinterface;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.urqa.Collector.CallStackCollector;
import com.urqa.Collector.DateCollector;
import com.urqa.Collector.DeviceCollector;
import com.urqa.Collector.LogCollector;
import com.urqa.common.CallStackData;
import com.urqa.common.Network;
import com.urqa.common.SendErrorProcess;
import com.urqa.common.StateData;
import com.urqa.common.JsonObj.ErrorSendData;
import com.urqa.common.JsonObj.IDInstance;
import com.urqa.common.JsonObj.IDSession;
import com.urqa.common.JsonObj.SendAPIApp;
import com.urqa.eventpath.EventPath;
import com.urqa.eventpath.EventPathManager;
import com.urqa.rank.ErrorRank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.StrictMode;
import android.util.Log;

public final class URQAController {
	
	private static final Gson gson = new Gson();
	
	@SuppressLint("NewApi")
	public static void InitializeAndStartSession(Context context, String APIKEY)
	{
		if(StateData.FirstConnect)
		{
			StateData.AppContext 	= context;
			StateData.FirstConnect 	= false;
			StateData.APIKEY 		= APIKEY;

			//Session 아이디 설정
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
					IDSession idsession =gson.fromJson(jsondata, IDSession.class);
					StateData.SessionID = idsession.idsession;
				}
			}
			
			SendAPIApp sendAPIKEY = new SendAPIApp();
			sendAPIKEY.apikey = StateData.APIKEY;
			sendAPIKEY.appversion = DeviceCollector.GetAppVersion(context);
			
			SessionID getID = new SessionID();
			getID.SetNetwork(StateData.ServerAddress + "client/connect",
							 sendAPIKEY, 
							 Network.Networkformula.POST);
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
		List<EventPath> SendEventdata =  EventPathManager.getEventPath();
		
		//없으면 보낼 필요 없지요...
		if(SendEventdata.size() == 0 || StateData.SessionID == "")
			return;
		
		
		Network SendEventPath = new Network();
		SendEventPath.SetNetwork(StateData.ServerAddress + "client/connect", 
								 gson.toJson(SendEventdata), 
								 Network.Networkformula.POST);
		
	}

	private static ErrorSendData CreateSendData(Exception e,String tag,ErrorRank rank ,Context context)
	{
		ErrorSendData senddata = new ErrorSendData();
		
		String CallStack = CallStackCollector.GetCallStack(e);
		CallStackData data = CallStackCollector.ParseStackTrace(e, CallStack);
		
		PackageManager packagemanager = context.getPackageManager();
		PackageInfo packageinfo = null;
		try {
			packageinfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		senddata.apikey 		= StateData.APIKEY;
		senddata.datetime 		= DateCollector.GetDateYYMMDDHHMMSS(context);
		senddata.device			= android.os.Build.MODEL;
		senddata.country		= DeviceCollector.GetNational(context);
		senddata.errorname		= data.ErrorName; 
		senddata.errorclassname	= data.ClassName;
		senddata.linenum		= data.Line;
		senddata.callstack		= CallStack;
		senddata.appversion		= packageinfo.versionName;
		senddata.osversion		= android.os.Build.VERSION.RELEASE;
		senddata.gpson			= (DeviceCollector.GetGps(context)) ? 1 : 0;
		senddata.wifion			= (DeviceCollector.GetWiFiNetwork(context)) ? 1 : 0;
		senddata.mobileon		= (DeviceCollector.GetMobileNetwork(context)) ? 1 : 0;
		senddata.scrwidth		= DeviceCollector.GetWidthScreenSize(context);
		senddata.scrheight		= DeviceCollector.GetHeightScreenSize(context);
		senddata.batterylevel	= DeviceCollector.GetBatteryLevel(context);
		senddata.availsdcard	= DeviceCollector.BytetoMegaByte(DeviceCollector.getAvailableExternalMemorySize());
		senddata.rooted			= (DeviceCollector.CheckRoot()) ? 1 : 0;
		senddata.appmemtotal	= DeviceCollector.BytetoMegaByte(DeviceCollector.GetTotalMemory());
		senddata.appmemfree		= DeviceCollector.BytetoMegaByte(DeviceCollector.GetFreeMemory());
		senddata.appmemmax		= DeviceCollector.BytetoMegaByte(DeviceCollector.GetMaxMemory());
		senddata.kernelversion	= DeviceCollector.GetLinuxKernelVersion();
		senddata.xdpi			= DeviceCollector.GetXDPI(context);
		senddata.ydpi			= DeviceCollector.GetYDPI(context);
		senddata.scrorientation = DeviceCollector.GetOrientation(context);
		senddata.sysmemlow		= (DeviceCollector.GetSystemLowMemory()) ? 1 : 0;
		senddata.tag			= tag;
		senddata.rank			= rank.value();
		senddata.eventpaths		= EventPathManager.GetErrorEventPath();
		senddata.locale			= DeviceCollector.GetLocale(context);
		
		return senddata;
	}
	public static void SendException(Exception e, String Tag, ErrorRank rank)
	{
		ErrorSendData sendData = CreateSendData(e,Tag,rank,StateData.AppContext);
		String Log		= LogCollector.getLog(StateData.AppContext);
		
		SendErrorProcess errprocess = new SendErrorProcess(sendData,Log);
		errprocess.start();
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
		EventPathManager.CreateEventPath(3);
		
		if(StateData.ToggleLogCat)
			return loglevel(level,tag,Msg,tr);
		else
			return 0;
	}
	
}
