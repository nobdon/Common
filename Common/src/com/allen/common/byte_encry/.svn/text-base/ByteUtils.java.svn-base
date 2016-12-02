package com.allen.common.byte_encry;

public class ByteUtils {
    private final static byte[] hex = "0123456789ABCDEF".getBytes(); 
    
	/**
	 * int 转成 byte
	 * @param i
	 * @return
	 */
	public static byte[] intToByte4(int i) {
		
		byte[] abyte0 = new byte[4];
		abyte0[0] = (byte) (0xff & i);
		abyte0[1] = (byte) ((0xff00 & i) >> 8);
		abyte0[2] = (byte) ((0xff0000 & i) >> 16);
		abyte0[3] = (byte) ((0xff000000 & i) >> 24);
		return abyte0;
	}
	
	public static byte[] intToByte(int value,int len){
		byte[] abyte = new byte[len];
		for (int i = 0; i < abyte.length; i++) {
			int shift = i*8;
			abyte[i] = (byte)((0xff&i) >> shift);
		}
		return abyte;
	}
	
	public static byte[] longToByte(long x) { 
		byte[] bb = new byte[8];
        bb[ 0] = (byte) (x >> 0); 
        bb[ 1] = (byte) (x >> 8); 
        bb[ 2] = (byte) (x >> 16); 
        bb[ 3] = (byte) (x >> 24); 
        bb[ 4] = (byte) (x >> 32); 
        bb[ 5] = (byte) (x >> 40); 
        bb[ 6] = (byte) (x >> 48); 
        bb[ 7] = (byte) (x >> 56); 
        return bb;
    } 

	 public static long byteToLong(byte[] bb) { 
		 byte[] byteDate = new byte[8];
		 System.arraycopy(bb, 0, byteDate,0 ,bb.length);
	       return ((((long) byteDate[ 0] & 0xff) << 0) 
	               | (((long) byteDate[ 1] & 0xff) << 8) 
	               | (((long) byteDate[ 2] & 0xff) << 16) 
	               | (((long) byteDate[ 3] & 0xff) << 24) 
	               | (((long) byteDate[ 4] & 0xff) << 32) 
	               | (((long) byteDate[ 5] & 0xff) << 40) 
	               | (((long) byteDate[ 6] & 0xff) << 48) | (((long) byteDate[ 7] & 0xff) << 56)); 
	 } 
	 
	public static byte[] intToByte2(int i) {
		
		byte[] abyte0 = new byte[2];
		
		abyte0[0] = (byte) (0xff & i);
		abyte0[1] = (byte) ((0xff00 & i) >> 8);
		return abyte0;
	}
	
	public static byte[] intToByte1(int i) {	
		byte[] abyte0 = new byte[1];
		abyte0[0] = (byte) (0xff & i);
		return abyte0;
	}
	
	public static int byteToInt(byte[] b){
		int value = (b[0] & 0xff) | ((b[1] << 8) & 0xff00) // | 表示安位或   
				| ((b[2] << 24) >>> 8) | (b[3] << 24);
	       return value;
	}
	public static int byte2ToInt(byte[] b){
		int value = (b[0] & 0xff) | ((b[1] << 8) & 0xff00);
	    return value;
	}
	
	public static int byte1ToInt(byte b){
		int value = (b & 0xff);
	    return value;
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
	
    // 从字节数组到十六进制字符串转�? 
    public static String bytes2HexString(byte[] b) {  
        byte[] buff = new byte[2 * b.length];  
        for (int i = 0; i < b.length; i++) {  
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];  
            buff[2 * i + 1] = hex[b[i] & 0x0f];  
        }  
        return new String(buff);  
    }  
    
    public static byte[] setParams(byte[]... b) {
		
		//System.out.println("参数的个数" + b.length);
		
		int len = 0;
		
		for(int i = 0; i < b.length; i++) {
			len+=b[i].length;
			//System.out.println("每个参数的长度"+b[i].length);
		}
		
		//System.out.println("整个数组参数加起来的总长度"+len);
		byte[] data = new byte[len];
		
		byte[] temp = null;
		for(int i = 0; i < b.length - 1; i++) {
			temp = byteMerger(b[i],b[i+1]);
			b[i+1] = temp;
		}
		
		//System.out.println("合并后的整个byte的总长度"+temp.length);
		//for(int i = 0;i<temp.length;i++) {
			//System.out.print(temp[i]+",");
		//}
		
		return temp;
	} 
    
    /*public static byte[] VariableBytes(byte[] date ,int length){
    	byte []idByte = null;
		if(sendIDlen==1){
			idByte = ByteUtils.intToByte1(sendID);
		}else if(sendIDlen ==2){
			idByte = ByteUtils.intToByte2(sendID);
		}else{
			idByte = ByteUtils.intToByte4(sendID);
		}
    }*/
}
