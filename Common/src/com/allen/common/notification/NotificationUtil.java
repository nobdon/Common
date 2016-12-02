package com.allen.common.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.allen.common.Login;
import com.allen.common.R;

/**
 * 通知栏--消息通知
 * @author pl
 *
 */
public class NotificationUtil {
	private final static int NOTIFICATION_MSG_ID = 100;
	
	public static void showNotification(Context context,String nickName,String msgContent){
		NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setContentTitle(nickName);
		builder.setContentText(msgContent);
		builder.setContentIntent(getDefalutIntent(context, Notification.FLAG_NO_CLEAR));
		builder.setTicker("消息内容"+":"+msgContent);
		builder.setWhen(System.currentTimeMillis());//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
		//builder.setPriority(Notification.); //设置该通知优先级
		builder.setVibrate(new long[] {0,300,500,700}); //震动
		builder.setDefaults(Notification.DEFAULT_SOUND); //默认铃声
		builder.setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher));
		Intent intent = new Intent(context,Login.class);  
		PendingIntent pendingIntent = PendingIntent.getActivity(context,NOTIFICATION_MSG_ID, intent, 0);  
		builder.setContentIntent(pendingIntent); 
		nManager.notify(NOTIFICATION_MSG_ID,builder.build());
	}
	
	private static PendingIntent getDefalutIntent(Context context,int flags){  
	    PendingIntent pendingIntent= PendingIntent.getActivity(context, 100, new Intent(), flags);  
	    return pendingIntent;  
	}  
}
