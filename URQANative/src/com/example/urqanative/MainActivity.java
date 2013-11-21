package com.example.urqanative;

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
         URQAController.InitializeAndStartSession(this, "DDC2C89B");
         
         URQAController.i("error!!Test", "1");
         URQAController.i("error!!Test", "2");
         URQAController.i("error!!Test", "3");
         URQAController.i("error!!Test", "4");
         URQAController.i("error!!Test", "5");
         URQAController.i("error!!Test", "6");
         URQAController.i("error!!Test", "7");
         URQAController.i("error!!Test", "8");
         URQAController.i("error!!Test", "9");
         
         invokeNativeFunction();
         URQAController.leaveBreadcrumb();
         try{
        	 String a = null;
        	 a.charAt(1);
         }catch(Exception e)
         {
             URQAController.SendException(e,"aetdjdfghdrthdrth",ErrorRank.Critical);
         }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
