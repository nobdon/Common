package com.allen.common.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import android.content.Context;
import android.content.Intent;
/**
 * socket接收信息
 * @author Allen
 */
public class Receive extends Thread {
     
    private Socket socket;
    private Context ctx;
    public static String PULL_RECIVE = "com.angico.wts.pullrecive";
    public Receive(Socket s, Context ctx) {
		this.socket = s;
		this.ctx = ctx;
	}

     
    public void run(){
         //前面4位byte 是长度 4位以后才是数据 所以 开头2位是长度（不包含id的长度）
        try {
            while (true) {
            	InputStream is = socket.getInputStream();
            	byte[] protocol = new byte[2];
            	// 把数据写入到缓冲区
            	is.read(protocol, 0, 2);
            	//如果当前协议是9001
            	if("9001".equals(bytes2HexString(protocol))){
            		//数据的长度(包括userid)
            		byte[] len = new byte[2];
            		// 把数据写入到缓冲区
                	is.read(len, 0, 2);
                	System.out.println(Client.toInt(len));
                	//userid
                	byte[] id = new byte[4];
                	is.read(id,0,4);
                	System.out.println(Client.toInt(id));
                	if(Client.toInt(len) > 4) {
                		byte[] data = new byte[Client.toInt(len)];
                    	is.read(data, 0, Client.toInt(len));
                    	String str = new String(data,"GBK");
//                    	String test = bytes2HexString(data);
 //                   	System.out.println(test);
                    	System.out.println(str);
                    /*	Intent it = new Intent();
    					it.setAction(PULL_RECIVE);
    					it.putExtra("pullrecive",str);
    					ctx.sendBroadcast(it);
    					*/
                	}

            	}else{
            		is = null;
            		continue;
            	}
            	
            //	System.out.println(Client.toInt(buff));
            	
                 }
        } catch (Exception e) {
            System.out.println("e--" + e);
        }
    }
    private final byte[] hex = "0123456789ABCDEF".getBytes(); 
 // 从字节数组到十六进制字符串转换  
    public String bytes2HexString(byte[] b) {  
        byte[] buff = new byte[2 * b.length];  
        for (int i = 0; i < b.length; i++) {  
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];  
            buff[2 * i + 1] = hex[b[i] & 0x0f];  
        }  
        return new String(buff);  
    }  
}