package com.feiyu.notification;

import com.feiyu.phonemonitor.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;



/**
 * Notification扩展类
 * @Description: Notification扩展类
 * @File: NotificationExtend.java
 * @Package com.test.background
 * @Author Hanyonglu
 * @Date 2012-4-13 下午02:00:44
 * @Version V1.0
 */

public class NotificationExtend {

    private Activity context;

    public NotificationExtend(Activity context) {

        // TODO Auto-generated constructor stub

        this.context = context;

    }

    // 显示Notification

    public void showNotification() {

        // 创建一个NotificationManager的引用

        NotificationManager notificationManager = (

                NotificationManager)context.getSystemService(

                        android.content.Context.NOTIFICATION_SERVICE);

        // 定义Notification的各种属性

        Notification notification = new Notification(

                R.drawable.safe,"阅读器", 

                System.currentTimeMillis());

        // 将此通知放到通知栏的"Ongoing"即"正在运行"组中

        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        // 表明在点击了通知栏中的"清除通知"后，此通知自动清除。

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;

        notification.defaults = Notification.DEFAULT_LIGHTS;

        notification.ledARGB = Color.BLUE;

        notification.ledOnMS = 5000;

        // 设置通知的事件消息

        CharSequence contentTitle = "阅读器显示信息"; // 通知栏标题

        CharSequence contentText = "推送信息显示，请查看……"; // 通知栏内容

        

        Intent notificationIntent = new Intent(context,context.getClass());

        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent contentIntent = PendingIntent.getActivity(
        context, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(
        context, contentTitle, contentText, contentIntent);
        // 把Notification传递给NotificationManager
        notificationManager.notify(0, notification);
    }

    // 取消通知

    public void cancelNotification(){

        NotificationManager notificationManager = (

                NotificationManager) context.getSystemService(

                        android.content.Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(0);

    }

}
