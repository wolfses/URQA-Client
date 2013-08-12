package com.urqa.common;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.urqa.common.JsonObj.ErrorSendData;
import com.urqa.common.JsonObj.IDInstance;

public class SendErrorProcess extends Thread{
	private ErrorSendData errordata;
	String 		  log;
	
	public SendErrorProcess(ErrorSendData data, String log) 
	{
		// TODO Auto-generated constructor stub
		errordata = data;
		this.log = log;
	}
	
	@Override
	public void run()
	{
		try {
			Gson gson = new Gson();
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(StateData.ServerAddress + "client/send/exception");
			
			post.setHeader("Content-Type", "application/json; charset=utf-8");
			client.getParams().setParameter("http.protocol.expect-continue", false);
			client.getParams().setParameter("http.connection.timeout", 5000);
			client.getParams().setParameter("http.socket.timeout", 5000);
			
			String test = gson.toJson(errordata);
			StringEntity input = new StringEntity(test,"UTF-8");

			post.setEntity(input);
			HttpResponse responsePOST = client.execute(post);
			HttpEntity resEntity = responsePOST.getEntity();
			
			if(StateData.TransferLog == false)
				return;

			String jsondata = "";
			try {
				jsondata = EntityUtils.toString(resEntity);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			IDInstance idinstance =gson.fromJson(jsondata, IDInstance.class);

			try {
			   HttpClient logclient = new DefaultHttpClient();
			   
			   HttpPost logpost = new HttpPost( StateData.ServerAddress + 
					   							"client/send/exception/log/"+ 
					   							idinstance.idinstance);

			   logclient.getParams().setParameter("http.protocol.expect-continue", false);
			   logclient.getParams().setParameter("http.connection.timeout", 5000);
			   logclient.getParams().setParameter("http.socket.timeout", 5000);
			   
			   // 1. 파일의 내용을 body 로 설정함 
			   logpost.setHeader("Content-Type", "text/plain; charset=utf-8");
			   StringEntity entity = new StringEntity(log, "UTF-8");
			   logpost.setEntity(entity);
			   
			   
			   HttpResponse response = logclient.execute(logpost);
			  } catch (Exception e) {
					e.printStackTrace();
				}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}