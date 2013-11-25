package com.example.urqanative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.urqa.clientinterface.URQAController;
import com.urqa.rank.ErrorRank;

public class BaseActivity extends Activity {
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
        
        Class<?> enclosingClass = getClass().getEnclosingClass();
		if (enclosingClass != null) {
			Log.i("Fanpple", enclosingClass.getName());
		} else {
			Log.i("Fanpple", getClass().getName());
		}
		
        //invokeNativeFunction();
        URQAController.leaveBreadcrumb();
        try{
       	 String a = null;
       	 a.charAt(1);
        }catch(Exception e)
        {
            URQAController.SendException(e,"aetdjdfghdrthdrth",ErrorRank.Critical);
        }
	}
}
