package com.allen.common.comm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * android SharedPreferences 管理类
 * 
 * @author Allen
 */
public class PreferenceManage {
	private static SharedPreferences preferences;
	
	/** 自动登陆 */
	public static final String IS_AUTO_LOGIN = "is_auto_login";
	/** 记住密码 */
	public static final String IS_REMBER_PASSWORD = "is_rember_password";
	/** 加密后的用户名 */
	public static final String USRENAME_PWD = "username_pwd";
	/** 加密后的密码 */
	public static final String PASSWORD_PWD = "password_pwd";
	/** 用户id*/
	public static final String USERID = "userid";
	/** 用户性别*/
	public static final String USER_GENDER="usergender";
	/** 用户昵称*/
	public static final String USER_NICKNAME = "usernickname";
	/** 用户备注*/
	public static final String USER_ABOUT = "userabout";
	/** 用户签名*/
	public static final String USER_SIGNATURE = "usersignature";
	/**手机*/
	public static final String USER_PHONENUM="phonenum";
	/**邮箱*/
	public static final String USER_EMAIL="email";
	/**真实姓名*/
	public static final String USER_REALNAME="realname";
	/**头像地址*/
	public static final String USER_FACEPATH = "facepath";
	/** 用户名*/
	public static final String USER_NAME = "username";
	/** 密码*/
	public static final String PASSWORD = "password";
	
	public static void init(Context ctx) {
		preferences = ctx.getSharedPreferences("wts_im", Context.MODE_PRIVATE);
	}
	
	/**
	 * 在SharedPreferences里面存储
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		SharedPreferences.Editor editor = preferences.edit();
		
		if (value.getClass() == String.class) {
			editor.putString(key, (String) value);
		} else if (value.getClass() == Integer.class) {
			editor.putInt(key, ((Integer) value).intValue());
		} else if (value.getClass() == Float.class) {
			editor.putFloat(key, ((Float) value).floatValue());
		} else if (value.getClass() == Long.class) {
			editor.putLong(key, ((Long) value).longValue());
		} else if (value.getClass() == Boolean.class) {
			editor.putBoolean(key, (Boolean) value);
		}
		
		editor.commit();
	}
	
	
	/** 判断字符串是否存在于既有的key */
	public static boolean contains(String from)
	{
		return preferences.contains(from);
	}
	
	/**
	 * 在SharedPreferences里面取
	 * @param key
	 * @param value
	 */
	public static String getString(String key, String value) {
		return preferences.getString(key, value);
	}

	public static int getInt(String key, int value) {
		return preferences.getInt(key, value);
	}

	public static float getFloat(String key, float value) {
		return preferences.getFloat(key, value);
	}

	public static long getLong(String key, long value) {
		return preferences.getLong(key, value);
	}

	public static boolean getBoolean(String key, boolean value) {
		return preferences.getBoolean(key, value);
	}
	
}
