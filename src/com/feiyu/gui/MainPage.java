package com.feiyu.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.feiyu.phonemonitor.R;

public class MainPage extends Activity{
	private ListView list1,list2,list3;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.mainpage);
		list1 = (ListView)findViewById(R.id.models);
		SimpleAdapter adapter1 = new SimpleAdapter(this,getData(),R.layout.list_models,
				new String[]{"icon","title","info","next"},
				new int[]{R.id.mainIcon,R.id.mainTitle,R.id.subTitle,R.id.subIcon});
		list1.setAdapter(adapter1);
		list1.setOnItemClickListener(new ItemListener());
		
		list2 = (ListView)findViewById(R.id.record);
		SimpleAdapter adapter2 = new SimpleAdapter(this,getListData(),R.layout.list_models,
				new String[]{"icon","title","info","next"},
				new int[]{R.id.mainIcon,R.id.mainTitle,R.id.subTitle,R.id.subIcon});
		list2.setAdapter(adapter2);
		
		list2.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				if(arg3==1){
					/*Intent intent = new Intent();
					intent.setClass(MainPage.this, SMSActivity.class);
					startActivity(intent);*/
				}
				if(arg3==0){
					/*Intent intent = new Intent();
					intent.setClass(MainPage.this, CallActivity.class);
					startActivity(intent);*/
				}
			}
			
		});
		
		list3 = (ListView)findViewById(R.id.advice);
		SimpleAdapter adapter3 = new SimpleAdapter(this,getAdviceData(),R.layout.list_models,
				new String[]{"icon","title","info","next"},
				new int[]{R.id.mainIcon,R.id.mainTitle,R.id.subTitle,R.id.subIcon});
		list3.setAdapter(adapter3);
        
	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("icon", R.drawable.app);
		map.put("title", "模式设定");
		map.put("info", "设置上课、睡眠以及安全模式");
		map.put("next", R.drawable.iconup);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.suo);
		map.put("title", "应用锁");
		map.put("info", "开启模式后，锁住指定应用");
		map.put("next", R.drawable.iconup);
		list.add(map);

		
		return list;
	}
	
	private List<Map<String,Object>> getListData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("icon", R.drawable.phone);
		map.put("title", "电话记录");
		map.put("info", "记录孩子的电话通话频率");
		map.put("next", R.drawable.iconup);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.msg);
		map.put("title", "短信记录");
		map.put("info", "记录孩子的短信发送频率");
		map.put("next", R.drawable.iconup);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ie);
		map.put("title", "网页浏览记录");
		map.put("info", "记录孩子的上网浏览信息");
		map.put("next", R.drawable.iconup);
		list.add(map);

		return list;
	}
	
	private List<Map<String, Object>> getAdviceData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("icon", R.drawable.star);
		map.put("title", "应用推荐");
		map.put("info", "了解更多我们的应用");
		map.put("next", R.drawable.iconup);
		list.add(map);
		
		return list;
	}
	
	class ItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			// TODO Auto-generated method stub
			if(id==0){
				//Toast.makeText(MainPage.this, "你选择了第"+id+"个列表项", Toast.LENGTH_LONG).show();
				/*Intent intent = new Intent();
				intent.setClass(MainPage.this, ModeActivity.class);
				startActivity(intent);*/
			}
			if(id==1){
				/*Intent intent = new Intent();
				intent.setClass(MainPage.this, AppLock.class);
				startActivity(intent);*/
			}
		}

		
		
	}
}
