package com.example.urqanative;

import com.urqa.clientinterface.URQAController;
import com.urqa.rank.ErrorRank;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends BaseActivity {
    static {
  	   System.loadLibrary("csourcep");
  	 }
     
     private native String invokeNativeFunction();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
