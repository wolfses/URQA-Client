package com.example.sdktest;

import com.google.gson.Gson;
import com.urqa.Collector.DeviceCollector;
import com.urqa.Collector.LogCollector;
import com.urqa.clientinterface.URQAController;
import com.urqa.rank.ErrorRank;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        URQAController.InitializeAndStartSession(this, "12312312");
        
        
        
        URQAController.e("test", "Test");
        
        URQAController.i("test2", "Test2");
        
        try{
        	String a = null;
            a.charAt(10);
        }
        catch(Exception e)
        {
        	URQAController.SendException(e,"testtag",ErrorRank.Critical);
        }
        
        /*
        DeviceCollector co = new DeviceCollector(senddata);
        
        boolean a = co.externalMemoryAvailable();
        
        long availex = co.getAvailableExternalMemorySize();
        long totalex = co.getTotalExternalMemorySize();
        
        long availin =  co.getAvailableInternalMemorySize();
        long totalin = co.getTotalInternalMemorySize();
        int level = co.GetBatteryLevel(senddata);
        
        long totalm = co.GetTotalMemory();
        long freem = co.GetFreeMemory();
        long maxm = co.GetMaxMemory();
        
        float xdpi = co.GetXDPI(senddata);
        float ydpi = co.GetXDPI(senddata);
        String Kernel = co.GetLinuxKernelVersion();
        
        
        
        Log.i("asd",level +"%");
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
