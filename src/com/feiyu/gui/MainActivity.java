package com.feiyu.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.feiyu.controller.Controller;
import com.feiyu.notification.NotificationExtend;
import com.feiyu.phonemonitor.R;

public class MainActivity extends Activity
{
	private Button start;
	private NotificationExtend notification;
	
	private Controller smsController;

	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 1:
				String s = msg.getData().getString("msg");
				System.out.println(s);
				break;
			}
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		start = (Button)findViewById(R.id.Start);
		notification = new NotificationExtend(this);
		final Intent intent = new Intent();
		intent.setAction("com.feiyu.service.FIRST_SERVICE");
		smsController = new Controller(this);
		
		start.setOnClickListener(new OnClickListener(){

			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				/*startService(intent);
				notification.showNotification();*/
				new Thread(){
					public void run(){
						while(!Thread.interrupted()){
							try
							{
								Thread.sleep(5000);
							} catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String str = smsController.getJsonFromAllData().toString();
							System.out.println(str);
							Message msg = new Message();
							Bundle bundle = new Bundle();
							msg.what = 1;
							bundle.putString("msg", str);
							msg.setData(bundle);
							handler.sendMessage(msg);
						}
						
					}
				}.start();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_HOME){
            // 显示Notification
            notification = new NotificationExtend(this);
            notification.showNotification();
            moveTaskToBack(true);                
 
            return true;
        }
		return super.onKeyDown(keyCode, event);
	}

	
}
