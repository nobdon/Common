package com.allen.common.tcp;

import java.io.OutputStream;
import java.net.Socket;

import android.content.Context;
/**
 * socket 通信客户端
 * @author Allen
 *
 */
public class Client {

	/*public static void main(String args[]) {
		int m = 4;
		byte[] byte1 = intToByte(m);
		
		int n = 3;
		byte[] byte2 = intToByte(n);
		byte[] byte_temp1 = byteMerger(byte1,byte2);
		
		String str = "[{\"content\":\"大家一起加油\",\"publishTime\":\"2014-07-28  16:39:40\",\"title\":\"大家一起加油\"}]";
		byte[] byte3 = str.getBytes();
		byte[] byte_temp2 = byteMerger(byte_temp1,byte3);
		
		send(byte_temp1);
	}*/
	public static void send(byte[] data,Context context) {
		Socket socket = null;
		try {
			// 创建客户端socket连接对象
			socket = SocketConnManager.getSocketInstance();
			// 创建byte数组数据对象
			//ByteArrayInputStream stringInputStream = new ByteArrayInputStream(data); 
			// 接收byte数组的值
			//BufferedReader buff = new BufferedReader(new InputStreamReader(stringInputStream));
			// 写入到socket中
			//PrintWriter os = new PrintWriter(socket.getOutputStream());
			if(socket.isConnected()){
				OutputStream os = socket.getOutputStream();
				// 启动接收数据的线程
				SocketConnManager.getReceiveInstance(socket,context);
	            // 用户输入信息
				if(null != data && data.length > 0) {
					os.write(data);
					os.flush();
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			SocketConnManager.closeReceive();
			SocketConnManager.closeSocket();
			System.out.println("c-Exception:" + e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * int 转成 byte
	 * @param i
	 * @return
	 */
	public static byte[] intToByte(int i) {
		
		byte[] abyte0 = new byte[4];
		
		abyte0[0] = (byte) (0xff & i);
		abyte0[1] = (byte) ((0xff00 & i) >> 8);
		abyte0[2] = (byte) ((0xff0000 & i) >> 16);
		abyte0[3] = (byte) ((0xff000000 & i) >> 24);
		
		return abyte0;
	}
	
	public static byte[] intToByte1(int i) {
		
		byte[] abyte0 = new byte[2];
		
		abyte0[0] = (byte) (0xff & i);
		abyte0[1] = (byte) ((0xff00 & i) >> 8);
		return abyte0;
	}
	
	// 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位
	public static int toInt(byte[] bRefArr) {
	    int iOutcome = 0;
	    byte bLoop;

	    for (int i = 0; i < bRefArr.length; i++) {
	        bLoop = bRefArr[i];
	        iOutcome += (bLoop & 0xFF) << (8 * i);
	    }
	    return iOutcome;
	}

	/**
	 * 合并多个byte数组
	 * @param byte1
	 * @param byte2
	 * @return
	 */
	public static byte[] byteMerger(byte[] byte1, byte[] byte2) {
		// 首先新建一个等于所有byte数组长度的byte数组
		byte[] byte_temp = new byte[byte1.length + byte2.length];
		// 复制每一个数组到新建的数组即可
		System.arraycopy(byte1, 0, byte_temp, 0, byte1.length);
		System.arraycopy(byte2, 0, byte_temp, byte1.length, byte2.length);
		//System.arraycopy(byte3, 0, byte_temp, byte2.length, byte3.length);
		return byte_temp;
	}

}
