package com.example.urqanative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.urqa.clientinterface.URQAController;
import com.urqa.common.StateData;
import com.urqa.rank.ErrorRank;

public class BaseActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //StateData.ServerAddress = "http://ur-qa.com:9000/urqa/"; 
        URQAController.InitializeAndStartSession(this, "61278AC7");
        
        URQAController.leaveBreadcrumb("1");
        URQAController.leaveBreadcrumb("2");
        URQAController.leaveBreadcrumb("3");
        URQAController.leaveBreadcrumb("4");
        URQAController.leaveBreadcrumb("5");
        URQAController.leaveBreadcrumb("6");
        URQAController.leaveBreadcrumb("7");
        URQAController.leaveBreadcrumb("8");
        URQAController.leaveBreadcrumb("9");
        URQAController.leaveBreadcrumb("10");
        
        
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
