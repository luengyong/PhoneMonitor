package com.feiyu.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.feiyu.phonemonitor.R;

public class GuardPage extends Activity
{

	static{
		System.loadLibrary("PhoneMonitor_Client");
	}
	private native String stringTestNdk();
	private ImageView stu_view,parent_view;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.guardpage);
		
		
		
		stu_view = (ImageView)findViewById(R.id.stu_mode);
		
		parent_view = (ImageView)findViewById(R.id.parent_mode);
		
		stu_view.setOnClickListener(new OnClickListener(){

			public void onClick(View v)
			{
				stu_view.setImageDrawable(getResources().getDrawable(R.drawable.stu_clicked));
				Intent intent = new Intent();
				intent.setClass(GuardPage.this, MainPage.class);
				finish();
				startActivity(intent);
				/*List<JSONObject> items = new ArrayList<JSONObject>();
				JSONArray array = new JSONArray();
				CallInfoCollector callCollector = new CallInfoCollector(GuardPage.this);
				List<CallEntity> list = callCollector.getData();
				for(int i=0;i<list.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("name", list.get(i).getName());
						obj.put("phoneNum", list.get(i).getPhone_num());
						obj.put("time", list.get(i).getTime());
						obj.put("type", list.get(i).getType());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					items.add(obj);
				}
				array.put(items);
				System.out.println("array "+array.toString());*/
			}
			
		});
		
		parent_view.setOnClickListener(new OnClickListener(){

			public void onClick(View v)
			{
				parent_view.setImageDrawable(getResources().getDrawable(R.drawable.parent_clicked));
			}
			
		});
	}
	
	
}
