package com.feiyu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.feiyu.entity.SMSEntity;

public class CheckService extends Service
{
	private int REFRESH_TIME = 1000*5;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 1:
				String str = msg.getData().getString("message");
				System.out.println(str);
				Toast.makeText(CheckService.this, str, 4000).show();
				
			//	System.out.println(sms.getJsonFromAllData().toString());
				break;
			}
		}
	};
	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		
		System.out.println("Service is created!");
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		System.out.println("Service is destoryed!");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		new Thread(){
			public void run(){
			
				while(!Thread.interrupted()){
					try
					{
						Thread.sleep(REFRESH_TIME);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Message msg = new Message();
					Bundle bundle = new Bundle();
					msg.what = 1;
					bundle.putString("message", getSmsInPhone());
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				
			}
		}.start();
		
		//System.out.println(getSmsInPhone());
		return START_STICKY;
	}

	/***********获取信息实体集合***************/
	public String getSmsInPhone()   
	{   
		//List<SMSEntity> list = new ArrayList<SMSEntity>(); 
	    final String SMS_URI_ALL   = "content://sms/";     
	    JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
	    StringBuilder smsBuilder = new StringBuilder();   
	    ContentResolver cr = getContentResolver();   
        
	    try{   
	    	String[] projection = new String[]{"_id", "address", "person",    
	                "body", "date", "type"};   
	        Uri uri = Uri.parse(SMS_URI_ALL);   
	        Cursor cur = cr.query(uri, projection, null, null, "date desc");      
	  
	        if (cur.moveToFirst()) {   
	            String name;    
	            String phoneNumber;          
	            String smsbody;   
	            String date;   
	            String type;   
	            
	            int nameColumn = cur.getColumnIndex("person");   
	            int phoneNumberColumn = cur.getColumnIndex("address");   
	            int smsbodyColumn = cur.getColumnIndex("body");   
	            int dateColumn = cur.getColumnIndex("date");   
	            int typeColumn = cur.getColumnIndex("type");   
	            
	            do{   
	            	JSONObject obj = new JSONObject();
	                name = cur.getString(nameColumn);                
	                phoneNumber = cur.getString(phoneNumberColumn);   
	                smsbody = cur.getString(smsbodyColumn);   
	                   
	                SimpleDateFormat dateFormat = new SimpleDateFormat(   
	                        "yyyy-MM-dd hh:mm:ss");   
	                Date d = new Date(Long.parseLong(cur.getString(dateColumn)));   
	                date = dateFormat.format(d);   
	                   
	                int typeId = cur.getInt(typeColumn);  
	                try{
	                	if(typeId == 1){   
		                    type = "接收";   
		                    obj.put("sms_type", typeId);
		                } else if(typeId == 2){   
		                    type = "发送";   
		                    obj.put("sms_type", typeId);
		                } else {   
		                    type = "";   
		                }   
		                
		                obj.put("sms_number", phoneNumber);
						obj.put("sms_time", date);
						array.put(obj);
	                }catch(JSONException e){
	                	e.printStackTrace();
	                }
	                
	                if(smsbody == null) smsbody = "";     
	            }while(cur.moveToNext());   
	            
	        } else {   
	            smsBuilder.append("no result!");   
	        }   
	        cur.close();
	        
	        smsBuilder.append("getSmsInPhone has executed!");   
	    } catch(SQLiteException ex) {   
	        Log.d("SQLiteException in getSmsInPhone", ex.getMessage());   
	       
	    }
	    
	    try
		{
			json.put("type", "sms");
			json.put("item", array);
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return json.toString();   
	} 

	/******
	 * 获取查询结果
	 * @return**********/
	/*public JSONObject getJsonFromAllData(){
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<SMSEntity> sms = getSmsInPhone();
		for(int index=0;index<sms.size();index++){
			JSONObject obj = new JSONObject();
			try
			{
				obj.put("sms_number", sms.get(index).getSms_number());
				obj.put("sms_time", sms.get(index).getSms_time());
				obj.put("sms_type", sms.get(index).getSms_type());
				array.put(obj);
			} catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			json.put("type", "sms");
			json.put("item", array);
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}*/
}
