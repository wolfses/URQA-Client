package com.urqa.common;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;


// /urqa/client/connect

public class Network extends Thread {
	
	private static final Gson gson = new Gson();
	
	public enum Networkformula{
		GET,POST
	}
	String URL;
	Object Jsondata;
	Networkformula formula;
	
	public Network()
	{}
	
	public void SetNetwork(String uRL, Object jsondata, Networkformula formula)
	{
		URL = uRL;
		Jsondata = jsondata;
		this.formula = formula;
	}
	
	public void CallbackFunction(HttpResponse responseGet ,HttpEntity resEntity)
	{
		
	}
	
	@Override
	public void run()
	{
		switch (formula) {
		case GET:
			GetSend();
			break;
		case POST:
			PostSend();
			break;
		}
	}
	
	private void GetSend()
	{
		try {
			HttpClient client = new DefaultHttpClient();

			client.getParams().setParameter("http.protocol.expect-continue", false);
			client.getParams().setParameter("http.connection.timeout", 5000);
			client.getParams().setParameter("http.socket.timeout", 5000);
			
			
			HttpGet get = new HttpGet(URL);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();

			
			if (resEntityGet != null) {
				CallbackFunction(responseGet,resEntityGet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("URQA", "Server Problem", e);
		}
	}
	
	private void PostSend()
	{
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(URL);

			post.setHeader("Content-Type", "application/json; charset=utf-8");
			
			client.getParams().setParameter("http.protocol.expect-continue", false);
			client.getParams().setParameter("http.connection.timeout", 5000);
			client.getParams().setParameter("http.socket.timeout", 5000);
			
			StringEntity input = new StringEntity(gson.toJson(Jsondata),"UTF-8");

			post.setEntity(input);
			HttpResponse responsePOST = client.execute(post);
			HttpEntity resEntity = responsePOST.getEntity();

			int getcode = responsePOST.getStatusLine().getStatusCode();

			CallbackFunction(responsePOST,resEntity);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("URQA", "Server Problem", e);
		}
	}

}
