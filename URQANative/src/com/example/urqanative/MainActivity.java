package com.example.urqanative;

import com.urqa.clientinterface.URQAController;

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
         URQAController.InitializeAndStartSession(this, "15081163");
         
         URQAController.i("error!!Test", "1");
         URQAController.i("error!!Test", "2");
         URQAController.i("error!!Test", "3");
         URQAController.i("error!!Test", "4");
         URQAController.i("error!!Test", "5");
         try{
        	 String a = null;
        	 a.charAt(1);
         }catch(Exception e)
         {
             URQAController.SendException(e);
         }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
