package com.allen.common.udp;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * udp socket管理
 * @author Allen
 *
 */
public class DatagramSocketManager {
	//private static final String IP = "192.168.1.20";
	//private static InetAddress serverAddress = null;
	private static int port = 5333;
	private static DatagramSocket socket = null;
	private static UDPReceive receive;
	private static UDPServer server;
	public static DatagramSocket getSocketInstance() throws UnknownHostException, SocketException {
			if (null == socket) {
				socket = new DatagramSocket(null);
		    	socket.setReuseAddress(true); // 绑定之前先设置Reuse
				socket.bind(new InetSocketAddress(port)); // 然后再绑定
			}
	return socket;
}

/*public static UDPServer getServerSocketInstance(){
	if(null==server){
		server = new UDPServer();
		server.start();
	}
	return server;
}*/
	
public static UDPReceive getReceiveInstance(DatagramSocket socket,CommunicationListener listener) {
	if(null == receive) {
		receive = new UDPReceive(socket,listener);
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
