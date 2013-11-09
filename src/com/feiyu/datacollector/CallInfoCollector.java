package com.feiyu.datacollector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.feiyu.entity.CallEntity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

public class CallInfoCollector {
	private Context mContext;
	
	public CallInfoCollector(Context context){
		this.mContext = context;
	}
	
	public List<CallEntity> getData(){
		String str = "";
		String name = "";
        int type;
        List<CallEntity> list = new ArrayList<CallEntity>();
        Date date;
        ContentResolver cr = mContext.getContentResolver();
        Cursor cursor = cr.query(
        		CallLog.Calls.CONTENT_URI, 
        		new String[]{CallLog.Calls.NUMBER,CallLog.Calls.CACHED_NAME,CallLog.Calls.TYPE, CallLog.Calls.DATE}, 
        		null, 
        		null,
        		CallLog.Calls.DEFAULT_SORT_ORDER);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < cursor.getCount(); i++) {  
        	CallEntity call = new CallEntity();
            cursor.moveToPosition(i);
            name = cursor.getString(1);
            str = cursor.getString(0);
            type = cursor.getInt(2);
            
            date = new Date(Long.parseLong(cursor.getString(3)));
            HashMap<String, Object> item = new HashMap<String, Object>(); 
            if(name == null){
            	call.setName("未知号码");
            }
            else{
            	call.setName(name);
            }
              
            call.setTime(str+" "+sdf.format(date)); 
           
            if(type==1){
            	call.setType(type);
            }
            if(type==2){
            	call.setType(type);
            }
           // 	System.out.println(str+" : "+type+" : "+date);
            list.add(call);
           }
        
        return list;  
	}
}
