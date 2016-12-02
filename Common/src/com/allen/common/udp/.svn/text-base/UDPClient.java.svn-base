package com.allen.common.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
/**
 * UDP客户端类
 * @author Allen
 *
 */
public class UDPClient {
	private  InetAddress serverAddress = null;
	
	
	public static boolean isFlush = false;
	public static boolean isDel = false;
	//懒汉式单例
	private static UDPClient client;
	private Context context;
	private UDPClient(Context context){
		this.context = context;
	}
	public static synchronized UDPClient getInstance(Context context){
		if (client==null){
			client = new UDPClient(context);
		}
		return client;
	}
	
	
	/**
	 * 
	 * 发出Notification的method.
	 * 
	 * @param iconId
	 *            图标
	 * @param contentTitle
	 *            标题
	 * @param contentText
	 *            你内容
	 * @param activity
	 */
	private void setNotiType(int iconId,String id,String uname,String headimg, String contentTitle,String contentText, Class activity) {
		
		NotificationManager noticeManager  = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		
		/*
		 * 创建新的Intent，作为点击Notification留言条时， 会运行的Activity
		 */
		Intent notifyIntent = new Intent(context, activity);
		//SystemNoticeDetailActivity.id = id;
		//SystemNoticeDetailActivity.name = uname;
		//SystemNoticeDetailActivity.img = headimg;
		notifyIntent.putExtra("id", id);
		notifyIntent.putExtra("name", uname);
		notifyIntent.putExtra("headimg", headimg);
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		/* 创建PendingIntent作为设置递延运行的Activity */
		PendingIntent appIntent = PendingIntent.getActivity(context, 0,
				notifyIntent, 0);

		/* 创建Notication，并设置相关参数 */
		Notification myNoti = new Notification();
		/* 设置statusbar显示的icon */
		myNoti.icon = iconId;
		/* 设置statusbar显示的文字信息 */
		myNoti.tickerText = contentTitle;
		/* 设置notification发生时同时发出默认声音 */
		myNoti.defaults = Notification.DEFAULT_SOUND;
		/* 设置Notification留言条的参数 */
		myNoti.setLatestEventInfo(context, contentTitle, contentText, appIntent);
		/* 在通知栏上点击此通知后自动清除此通知 */
		myNoti.flags |= Notification.FLAG_AUTO_CANCEL; 
		/* 送出Notification */
		noticeManager.notify(0, myNoti);
	}
	
	/**
	 * 向服务端发送消息
	 * @param data btye数组
	 * @param context 应用的上下文
	 */
	public void send(final byte[] data,String ip,int port) {
				DatagramSocket socket = null;
				DatagramPacket pack = null;
				try {
					// 创建客户端socket连接对象
					socket = DatagramSocketManager.getSocketInstance();
					serverAddress = InetAddress.getByName(ip);
					if(null != data && data.length > 0){
						pack = new DatagramPacket(data, data.length,serverAddress,port);
						// 创建客户端socket连接对象
						socket = DatagramSocketManager.getSocketInstance();
						//socket.setSoTimeout(30000);
						socket.send(pack);
						// 启动接收数据的线程
						DatagramSocketManager.getReceiveInstance(socket,null);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("send()@@@@@@@@@@@@@@@@@@"+e.getMessage()+"出错");
//					throw new RuntimeException(e);
				}finally{
					
					DatagramSocketManager.closeReceive();
					DatagramSocketManager.closeSocket();
				}
	}
	private void receiveMessage(byte[] data,String firendIp,int firendPort){
	}
}
