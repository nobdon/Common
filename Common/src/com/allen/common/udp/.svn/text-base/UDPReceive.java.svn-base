package com.allen.common.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * socket接收信息
 * @author Allen
 */
public class UDPReceive extends Thread {
    private DatagramSocket socket;
    private CommunicationListener listener;
    public static int port  = 0;
 //   public static String PULL_RECIVE = "com.angico.wts.pullrecive";
    public UDPReceive(DatagramSocket s,CommunicationListener listener) {
		this.socket = s;
		this.listener = listener;
		
	}
    //消息缓存区
    private byte[] buffer;
    public void run(){
        try {
        	
            while (true) {
            	buffer = new byte[65535];
            	DatagramPacket pack = new DatagramPacket(buffer, buffer.length);
            	socket.receive(pack);
            /*	port = pack.getPort();
            	System.out.println("ip:"+pack.getAddress().getHostAddress());
            	System.out.println(pack.getPort()+":::"+socket.getPort()+":;"+socket.getLocalPort()+"::");*/
            	int length = pack.getLength();
	           	 byte[] data = new byte[length];
	           	 System.arraycopy(buffer, 0, data, 0, length); 
	           	 if(length < buffer.length){
	           		 listener.communication(data,pack.getAddress().getHostAddress(),pack.getPort());            		 
	           	 }else{
	           		 System.out.println("数据超过65535");
	           	 }
            }
        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("@@@run错误"+e.getMessage());
           socket = null;
        }
    }
}