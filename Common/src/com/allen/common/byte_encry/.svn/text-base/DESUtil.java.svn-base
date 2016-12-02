package com.allen.common.byte_encry;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * DES加密 解密算法类
 * 
 * @author ysj
 * 
 */
public class DESUtil {
	
	public static final String ECB_DECOD = "DES/ECB/PKCS5Padding"; // ecb加密模式
	public static final String CBC_DECOD = "DES/CBC/PKCS5Padding"; // cbc加密模式
	
	/**
	 * DES 加密算法
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 *             EncryptAsDoNet("ysj","Ango.WTS") 调用Ango.WTS 密文 必须8位
	 */
	public static String EncryptAsDoNet(String message, String key)
			throws Exception {
		// 产生与.net 对应的加密des + base64 加密串
		Cipher cipher = Cipher.getInstance(ECB_DECOD);
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GB2312"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		/*IvParameterSpec iv = new IvParameterSpec(key.getBytes("GB2312"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);*/
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // ECB不需要IV CBC 需要
		byte[] encryptbyte = cipher.doFinal(message.getBytes());
		BASE64Encoder base64Encoder = new BASE64Encoder();
		System.gc();
		// base64Encoder.encode(encryptbyte);
		return base64Encoder.encode(encryptbyte);
		// toHexString(encryptbyte).toUpperCase();
	}

	/**
	 * DES 解密算法
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String DecryptDoNet(String message, String key)
			throws Exception {
		// base64 + des 解密.net 加密传来串
		// byte[] bytesrc = convertHexString(message); //不用base64 的方式
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] bytesrc = base64Decoder.decodeBuffer(message);
		Cipher cipher = Cipher.getInstance(ECB_DECOD);
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GB2312"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		/*IvParameterSpec iv = new IvParameterSpec(key.getBytes("GB2312"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);*/
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] retByte = cipher.doFinal(bytesrc);

		return new String(retByte);
	}

	/**
	 * 为了适应des加密后放到浏览器的加密处理
	 * 
	 * @return
	 */
	public static String encryptAsDoNetForUrl(String msg) {
		String value = "";
		try {
			value = msg.replace("/", "_").replace("+", "-").replace("=", "");
		} catch (Exception e) {
			System.out.println("DES字符串加密处理异常!");
		}

		return value;
	}

	/**
	 * 为了解密从浏览器传过来的des加密数据
	 * 
	 * @return
	 */
	public static String decryptDoNetForUrl(String msg) {
		String value = "";
		try {
			value = msg.replace("_", "/").replace("-", "+");
			int i = value.length(); // 当前字符串的长度
			int j = value.length() + (4 - value.length() % 4) % 4; // 最终需要补齐到的长度
			StringBuffer sb = null;
			while (i < j) {
				sb = new StringBuffer();
				sb.append(value).append("=");
				value = sb.toString();
				i = value.length();
			}
		} catch (Exception e) {
			System.out.println("DES字符串解密处理异常!");
		}

		return value;
	}
	
	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密字符串
	 * @param key
	 *            加密私钥，长度不能够小于8位
	 * @return 加密后的字节数组，一般结合Base64编码使用
	 * @throws CryptException
	 *             异常
	 *//*
	public String encode(String data,String key) throws Exception {
		return encode(data.getBytes(),key);
	}

	*//**
	 * 加密
	 * 
	 * @param data
	 *            待加密字符串
	 * @param key
	 *            加密私钥，长度不能够小于8位
	 * @return 加密后的字节数组，一般结合Base64编码使用
	 * @throws CryptException
	 *             异常
	 *//*
	public static String encode(byte[] data,String key) throws Exception {
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key的长度不能够小于8位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ECB_DECOD);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] bytes = cipher.doFinal(data);

			return Base64.encodeToString(bytes, 3);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	*//**
	 * 解密
	 * 
	 * @param data
	 *            待解密字符串
	 * @param key
	 *            解密私钥，长度不能够小于8位
	 * @return 解密后的字节数组
	 * @throws Exception
	 *             异常
	 *//*
	public static byte[] decode(byte[] data,String key) throws Exception {
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key的长度不能够小于8位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ECB_DECOD);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	*//**
	 * 获取编码后的值
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 *//*
	public static String decodeValue(String data,String key) {
		byte[] datas;
		String value = null;
		try {
			if (System.getProperty("os.name") != null
					&& (System.getProperty("os.name").equalsIgnoreCase("sunos") || System
							.getProperty("os.name").equalsIgnoreCase("linux"))) {
				datas = decode( Base64.decode(data, 3),key);
			} else {
				datas = decode(Base64.decode(data, 3),key);
			//}

			value = new String(datas);
		} catch (Exception e) {
			value = "";
		}
		return value;
	}*/
}
