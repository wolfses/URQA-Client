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

    static {
 	   System.loadLibrary("csourcep");
 	 }
    
    private native String invokeNativeFunction();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        try{
        	String a = invokeNativeFunction();
        }
        catch(Throwable e)
        {
        	Log.d("zzz","이야~~~~~~");
        	e.printStackTrace();
        }


        /*
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
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
