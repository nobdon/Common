
package com.allen.common.byte_encry;


/**
 * byte字节流定长截取工具类
 * 
 * @author Allen
 */

public class SubByteUtil {
	/**
	 * 
	 * @param data byte数据源
	 * @param start 开始截取的起点
	 * @param length byte的截取的长度
	 * @return
	 */
	public static byte[] byteSub(byte[] data, int start, int length) {
		byte[] bt = new byte[length];
		
		if(start + length > data.length) {
			bt = new byte[data.length-start];
		}
		
		for(int i = 0; i < length &&(i + start) < data.length; i++) {
			bt[i] = data[i + start];
		}
		return bt;
	}
}
