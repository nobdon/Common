package com.allen.common.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;


/**
 * socket 连接工具类
 * @author Allen
 *
 */
public class SocketConnManager {
	private static String HOST = "192.168.1.1";
	private static int PORT = 5111;
	private static Socket socket = null;
	private static Receive receive = null;
	
	public static Socket getSocketInstance() throws UnknownHostException, IOException {
			if (null == socket) {
				socket = new Socket(HOST, PORT);
				socket.setSoTimeout(300000);
			}
		
		return socket;
	}
	
	public static Receive getReceiveInstance(Socket socket,Context contenxt) {
		if(null == receive) {
			receive = new Receive(socket,contenxt);
			receive.start();
		}
		
		return receive;
	}
	public static void closeReceive(){
		receive = null;
	}
	public  static void closeSocket(){
		socket = null;
	}
}
