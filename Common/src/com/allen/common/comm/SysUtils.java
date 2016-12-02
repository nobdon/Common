package com.allen.common.comm;

import java.util.UUID;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 
 * 创建人：wuhaowen
 * 创建时间：2014年4月16日 下午4:33:14
 * 
 * @version 1.0
 * 
 */
public class SysUtils {

	/**
	 * 
	 * 取得应用元数据（AndroidManifest.xml中配置的meta-data数据）    
	 * @param  context    
	 * @param  key
	 * @return String      
	 */
	public static String getMetaDataByKey(Context context, String key) {
		if (TextUtils.isEmpty(key)) {
			try {
				ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
						PackageManager.GET_META_DATA);
				key = appInfo.metaData.getString(key);
			} catch (NameNotFoundException e) {
				return "";
			}
		}
		return key;

	}

	public static String getIMSI(Context context) {
		TelephonyManager telephoneMngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephoneMngr.getSubscriberId();

	}

	public static String getIMEI(Context context) {
		TelephonyManager telephoneMngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephoneMngr.getDeviceId();

	}

	/**
	 * 获取App安装包信息
	 * @return
	 */
	public static PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try {
			info = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			return new PackageInfo();
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	/**
	 * 获取app名称
	 * @return
	 */
	public static String getApplicationName(Context context) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getApplicationContext().getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			applicationInfo = null;
		}
		String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
		return applicationName;
	}

	/**
	 * 
	 * getAppVersion获取App版本名称
	 * @param   name    
	 * @param  @return        
	 * @return String      
	 * @Exception 异常对象
	 */
	public static String getAppVersionName(Context context) {
		PackageInfo packageInfo;
		try {
			packageInfo = context.getApplicationContext().getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
		}
		return "";
	}

	/**
	 * dip转px
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px转dip
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
