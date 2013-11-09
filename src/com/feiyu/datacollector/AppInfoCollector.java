package com.feiyu.datacollector;

import java.io.DataOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AppInfoCollector
{
	private Context mContext;
	public AppInfoCollector(Context context){
		
		this.mContext = context;
	//	String apkRoot="chmod 777 "+context.getPackageCodePath();
     //   RootCommand(apkRoot);
	}
	
	public void getUsageTime(ApplicationInfo app){
		PackageManager pm = mContext.getPackageManager(); 
		int aLaunchCount;
        long aUseTime;
		try
		{
			/*PackageInfo info = pm.getPackageInfo(mContext.getPackageName(),  
			        PackageManager.GET_CONFIGURATIONS | PackageManager.GET_META_DATA);
			ApplicationInfo app = info.applicationInfo;*/
			ComponentName aName = new ComponentName(app.packageName,app.className);
			
			//获得ServiceManager类
            Class<?> ServiceManager = Class
               .forName("android.os.ServiceManager");
            
            //获得ServiceManager的getService方法
            Method getService = ServiceManager.getMethod("getService", java.lang.String.class);
            
            //调用getService获取RemoteService
            Object oRemoteService = getService.invoke(null, "usagestats");
            
            //获得IUsageStats.Stub类
            Class<?> cStub = Class
               .forName("com.android.internal.app.IUsageStats$Stub");
            //获得asInterface方法
            Method asInterface = cStub.getMethod("asInterface", android.os.IBinder.class);
            //调用asInterface方法获取IUsageStats对象
            Object oIUsageStats = asInterface.invoke(null, oRemoteService);
            //获得getPkgUsageStats(ComponentName)方法
            Method getPkgUsageStats = oIUsageStats.getClass().getMethod("getPkgUsageStats", ComponentName.class);
            //调用getPkgUsageStats 获取PkgUsageStats对象
            Object aStats = getPkgUsageStats.invoke(oIUsageStats, aName);
            //Object bStats = getPkgUsageStats.invoke(oIUsageStats, bName);
            
            //获得PkgUsageStats类
            Class<?> PkgUsageStats = Class.forName("com.android.internal.os.PkgUsageStats");
            
            aLaunchCount = PkgUsageStats.getDeclaredField("launchCount").getInt(aStats);
           // bLaunchCount = PkgUsageStats.getDeclaredField("launchCount").getInt(bStats);
            aUseTime = PkgUsageStats.getDeclaredField("usageTime").getLong(aStats);
            //bUseTime = PkgUsageStats.getDeclaredField("usageTime").getLong(bStats);
            
            System.out.println("aLaunchCount = "+aLaunchCount+" : aUseTime = "+aUseTime);
            
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     * @param command 命令：String apkRoot="chmod 777 "+getPackageCodePath(); RootCommand(apkRoot);
     * @return 应用程序是/否获取Root权限
     */
    public static boolean RootCommand(String command)
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e)
        {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;
    }
	
}
