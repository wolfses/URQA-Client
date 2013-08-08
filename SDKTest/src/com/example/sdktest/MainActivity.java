package com.example.sdktest;

import com.urqa.Collector.DeviceCollector;
import com.urqa.Collector.LogCollector;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeviceCollector co = new DeviceCollector(this);
        
        boolean a = co.externalMemoryAvailable();
        
        long availex = co.getAvailableExternalMemorySize();
        long totalex = co.getTotalExternalMemorySize();
        
        long availin =  co.getAvailableInternalMemorySize();
        long totalin = co.getTotalInternalMemorySize();
        int level = co.GetBatteryLevel(this);
        
        long totalm = co.GetTotalMemory();
        long freem = co.GetFreeMemory();
        long maxm = co.GetMaxMemory();
        
        float xdpi = co.GetXDPI(this);
        float ydpi = co.GetXDPI(this);
        String Kernel = co.GetLinuxKernelVersion();
        
        
        
        Log.i("asd",level +"%");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
