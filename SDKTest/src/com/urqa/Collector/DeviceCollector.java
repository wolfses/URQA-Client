package com.urqa.Collector;

import java.io.File;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;





public class DeviceCollector {

	Context m_Context;
	
	public DeviceCollector(Context context) {
		// TODO Auto-generated constructor stub
		m_Context = context;
	}

	/**
	 * 시스템 정보를 모은다.
	 * @since 2012. 11. 12.오전 12:14:53
	 * TODO
	 * @author JeongSeungsu
	 * @param OutPutData
	 */
	public void DoCollectSystemInfo(Context context) {
/*		// TODO Auto-generated method stub
 
        PackageManager packagemanager = m_Context.getPackageManager();
        try
        {
            PackageInfo packageinfo = packagemanager.getPackageInfo(m_Context.getPackageName(), 0);
            
            OutPutData.AppVersion 			= packageinfo.versionName;
            //String APP_VERSIONCODE 		= String.valueOf(packageinfo.versionCode);
            //String APP_PACKAGE 			= packageinfo.packageName;
            OutPutData.MobileNetwork 		= Network.Get3GNetwork(m_Context);
            OutPutData.WiFi			 		= Network.GetWiFiNetwork(m_Context);
            OutPutData.National 			= GetNational(m_Context);
            OutPutData.GPS					= GetGps(m_Context);
            OutPutData.OSVersion			= android.os.Build.VERSION.RELEASE;
            OutPutData.Model				= android.os.Build.MODEL;
            OutPutData.ScreenHeight			= GetHeightScreenSize(m_Context);
            OutPutData.ScreenWidth			= GetWidthScreenSize(m_Context);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        */
		
	}
	

	public int GetBatteryLevel(Context context)
	{
		Intent bat = new Intent(); // 배터리 값을 받는 인텐트 변수 
		int batLevel = 0; // 인텐트에서 배터리 값을 받는다. 
		bat = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		batLevel = bat.getIntExtra("level", -1); 
		return batLevel;
	}
	
	/**
	 * 나라 코드를 가져온다.
	 * @since 2012. 11. 15.오전 6:00:49
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return 나라코드가 제대로 안될시 Unknown반환 아니면 kr 같은 나라코드 반환
	 */
	private String GetNational(Context context){
		Locale nowlocale = context.getResources( ).getConfiguration( ).locale;
		String isNull = "";
		if(isNull.equals(nowlocale.getCountry()))
			return "unknown";
		else
			return nowlocale.getCountry(); 
	}
	/**
	 * Gps 상태를 가져온다.
	 * @since 2012. 11. 15.오전 6:01:19
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return true 사용중 false 면 비사용
	 */
	private boolean GetGps(Context context) {
		PackageManager packagemanager = context.getPackageManager();
		if (packagemanager.checkPermission("android.permission.ACCESS_FINE_LOCATION",context.getPackageName()) == 0) {
			LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				return false;
			else
				return true;
		}
		return false;
	}
	/**
	 * 가로 스크린 사이즈를 가져온다.
	 * @since 2012. 11. 15.오전 6:01:42
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return 가로 스크린 크기
	 */
	private int GetWidthScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}
	/**
	 * 세로 스크린 사이즈를 가져온다.
	 * @since 2012. 11. 15.오전 6:01:58
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return 세로 스크린 크기
	 */
	private int GetHeightScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}
	
	
	
    static final int ERROR = -1;   
    
    static public boolean externalMemoryAvailable() {   
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);   
    }   
       
    static public long getAvailableInternalMemorySize() {   
        File path = Environment.getDataDirectory();   
        StatFs stat = new StatFs(path.getPath());   
        long blockSize = stat.getBlockSize();   
        long availableBlocks = stat.getAvailableBlocks();   
        return availableBlocks * blockSize;   
    }   
       
    static public long getTotalInternalMemorySize() {   
        File path = Environment.getDataDirectory();   
        StatFs stat = new StatFs(path.getPath());   
        long blockSize = stat.getBlockSize();   
        long totalBlocks = stat.getBlockCount();   
        return totalBlocks * blockSize;   
    }   
       
    static public long getAvailableExternalMemorySize() {   
        if(externalMemoryAvailable()) {   
            File path = Environment.getExternalStorageDirectory();   
            StatFs stat = new StatFs(path.getPath());   
            long blockSize = stat.getBlockSize();   
            long availableBlocks = stat.getAvailableBlocks();   
            return availableBlocks * blockSize;   
        } else {   
            return ERROR;   
        }   
    }   
       
    static public long getTotalExternalMemorySize() {   
        if(externalMemoryAvailable()) {   
            File path = Environment.getExternalStorageDirectory();   
            StatFs stat = new StatFs(path.getPath());   
            long blockSize = stat.getBlockSize();   
            long totalBlocks = stat.getBlockCount();   
            return totalBlocks * blockSize;   
        } else {   
            return ERROR;   
        }   
    }   

    static public boolean CheckRoot()
    {
        boolean flag = false;
        String as[] = {
            "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"
        };
        String as1[] = as;
        int i = as1.length;
        int j = 0;
        do
        {
            if(j >= i)
                break;
            String s = as1[j];
            File file = new File((new StringBuilder()).append(s).append("su").toString());
            if(file.exists())
            {
                flag = true;
                break;
            }
            j++;
        } while(true);
        return flag;
    }
	
    
    static public long GetTotalMemory()
    {
        long total = Runtime.getRuntime().totalMemory();
        return total;
    }

    static public long GetFreeMemory()
    {
    	long free = Runtime.getRuntime().freeMemory();
    	return free;  
    }
  
    static public long GetMaxMemory()
    {
    	long max = Runtime.getRuntime().maxMemory();
    	return max;
    }

    static public String GetLinuxKernelVersion()
    {
    	 return System.getProperty("os.version");
    }
    
    static public float GetXDPI(Context context)
    {
    	DisplayMetrics metrics = new DisplayMetrics();
    	((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics.xdpi;               // 해상도 
    }
    static public float GetYDPI(Context context)
    {
    	DisplayMetrics metrics = new DisplayMetrics();
    	((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics.ydpi;               // 해상도 
    }
    
    static public int GetOrientation(Context context)
    {
    	return ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
    }
    
    static public boolean GetSystemLowMemory()
    {
    	android.app.ActivityManager.MemoryInfo memoryinfo = new android.app.ActivityManager.MemoryInfo();
    	return memoryinfo.lowMemory;
    }
}
