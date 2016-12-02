package com.allen.common.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/**
 * udp 服务端
 * @author Allen
 *
 */
public class UDPServer extends Thread{
	 //消息缓存区
    private byte[] buffer;
    private DatagramSocket socket;
    public UDPServer(){
    	try {
		socket = new DatagramSocket(null);
    	socket.setReuseAddress(true); // 绑定之前先设置Reuse
		socket.bind(new InetSocketAddress(1053)); // 然后再绑定
    	} catch (SocketException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		System.out.println("@@@@@@构造方法错误~"+e.getMessage());
    		  socket = null;
    	}
    }
    public void run(){
        try {
        	
            while (true) {
            	buffer = new byte[65535];
            	DatagramPacket pack = new DatagramPacket(buffer, buffer.length);
            	socket.receive(pack);
            	System.out.println("ip:"+pack.getAddress().getHostAddress());
            	System.out.println(pack.getPort()+":::"+socket.getPort()+":;"+socket.getLocalPort()+"::");
            	int length = pack.getLength();
	           	 byte[] data = new byte[length];
	           	 System.arraycopy(buffer, 0, data, 0, length); 
	           	 if(length < buffer.length){
	           		System.out.println("接收局域网聊天数据");   
	           		System.out.println("RECEIVE: " + new String(pack.getData()).trim());
	           		/*DatagramSocket ds = new DatagramSocket();
	           		 byte []test = new byte[1];
	           		 test[0] = 1;
	           		DatagramPacket dp = new DatagramPacket(test, test.length, new InetSocketAddress(pack.getAddress().getHostAddress(), pack.getPort()));
	           		ds.send(dp);
	           		System.out.println("SEND: " + new String(dp.getData()));*/

	           	}else{
	           		 //TODO:数据超过65535
	           		 System.out.println("数据超过65535");
	           	 }
            }
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("@@@@@@run()错误~"+e.getMessage());
           socket = null;
        }
    }
}
