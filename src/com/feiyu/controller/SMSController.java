package com.feiyu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

import com.feiyu.entity.SMSEntity;
import com.feiyu.phonemonitor.R;

public class SMSController 
{
	//private Activity mActivity;
	
	
	public static final String SMS_URI_ALL = "content://sms/";
	
	private Context mContext;
	
	public SMSController(Context context){
		this.mContext = context;
	}
	

	/**********
	 * 获取短信相关内容
	 * @return*/
	/*public List<SMSEntity> getSMSInfo(){
		Uri uri = Uri.parse(SMS_URI_ALL);
		Cursor cursor = mContext.managedQuery(uri, new String[] { "_id", "address", "person",  
                "body", "date", "type" },  
                null, null, null );  
         
		List<SMSEntity> list = new ArrayList<SMSEntity>(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(cursor.moveToFirst()) {  
        	
            int addrIdx = cursor.getColumnIndex("address");  
            int personIdx = cursor.getColumnIndex("person");  
            int dateIdx = cursor.getColumnIndex("date");
            int typeIdx = cursor.getColumnIndex("type");  
            do {  
            	SMSEntity sms = new SMSEntity();
                String addr = cursor.getString(addrIdx);  
                String person = cursor.getString(personIdx);  
                String type = cursor.getString(typeIdx);  
                String date = sdf.format(Long.parseLong(cursor.getString(dateIdx))); 
                
                sms.setSms_number(addr);
                sms.setSms_time(date);
                
                HashMap<String, Object> item = new HashMap<String, Object>();  
                item.put("addr", addr);  
                item.put("person", date);  
                if("1".equals(type)){
                	sms.setSms_type(mContext.getResources().getDrawable(R.drawable.sms_in));
                }
                if("2".equals(type)){
                	sms.setSms_type(mContext.getResources().getDrawable(R.drawable.sms_out));
                }
                list.add(sms);  
            } while(cursor.moveToNext());  
        }  
		return list;
	}
	*/
	
	public List<SMSEntity> getSmsInPhone()   
	{   
	    final String SMS_URI_ALL   = "content://sms/";     
	     
	    List<SMSEntity> list = new ArrayList<SMSEntity>();  
	    
	    try{   
	        ContentResolver cr = mContext.getContentResolver();   
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
	            	SMSEntity sms = new SMSEntity();
	                name = cur.getString(nameColumn);                
	                phoneNumber = cur.getString(phoneNumberColumn);   
	                smsbody = cur.getString(smsbodyColumn);   
	                   
	                SimpleDateFormat dateFormat = new SimpleDateFormat(   
	                        "yyyy-MM-dd hh:mm:ss");   
	                Date d = new Date(Long.parseLong(cur.getString(dateColumn)));   
	                date = dateFormat.format(d);   
	                   
	                int typeId = cur.getInt(typeColumn);   
	                if(typeId == 1){   
	                	sms.setSms_type(typeId);   
	                } else if(typeId == 2){   
	                	sms.setSms_type(typeId);
	                }   
	                
	                sms.setSms_number(phoneNumber);
	                sms.setSms_time(date);
	                
	                list.add(sms);
	                if(smsbody == null) smsbody = "";     
	            }while(cur.moveToNext());   
	        } else {   
	            //smsBuilder.append("no result!");   
	        }   
	        cur.close();
	        
	       // smsBuilder.append("getSmsInPhone has executed!");   
	    } catch(SQLiteException ex) {   
	        Log.d("SQLiteException in getSmsInPhone", ex.getMessage());   
	    }   
	    return list;   
	} 
	
	/******
	 * 获取查询结果
	 * @return**********/
	public JSONObject getJsonFromAllData(){
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
	}
	
}
