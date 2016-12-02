package com.allen.common.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.allen.common.custom.NewToast;

/**
 * 网络类型 网络是否连接等
 * @author Allen
 *
 */
public class NetWorkUtil {
	private Context ctx = null;
	/** 没有网络 */  
	public static final int NETWORKTYPE_INVALID = 0;
	/** wap网络 */  
	public static final int NETWORKTYPE_WAP = 1;
	/** 2G网络 */  
	public static final int NETWORKTYPE_2G = 2;
	/** 3G和3G以上网络，或统称为快速网络 */  
	public static final int NETWORKTYPE_3G = 3;
	/** wifi网络 */  
	public static final int NETWORKTYPE_WIFI = 4;
	
	public NetWorkUtil(Context context) {
		ctx = context;
	}
	
	/**
	 * 检查网络
	 * @return
	 */
	public boolean checkNet(){
		ConnectivityManager conn = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWorkInfo = conn.getActiveNetworkInfo();
		if(netWorkInfo == null || !netWorkInfo.isAvailable()){
			NewToast.showToast(ctx, "网络异常", 3000);
			return false;
		}
		return true;
	}
	
	/**
	 *  获取网络类型
	 * @param context
	 * @return
	 */
	public int getNetWorkType() { 
		  
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();  
        int mNetWorkType = -1;
		if (networkInfo != null && networkInfo.isConnected()) {  
            String type = networkInfo.getTypeName();  
  
            if (type.equalsIgnoreCase("WIFI")) {  
                mNetWorkType = NETWORKTYPE_WIFI;  
            } else if (type.equalsIgnoreCase("MOBILE")) {  
                String proxyHost = android.net.Proxy.getDefaultHost();  
  
                mNetWorkType = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(ctx) ? NETWORKTYPE_3G : NETWORKTYPE_2G)  
                        : NETWORKTYPE_WAP;  
            }  
        } else {  
            mNetWorkType = NETWORKTYPE_INVALID;  
        }  
  
        return mNetWorkType;  
    }   
	
	/**
	 * 判断是否为快速网络
	 * @param context
	 * @return
	 */
	private static boolean isFastMobileNetwork(Context context) {  
		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);  
		switch (telephonyManager.getNetworkType()) {  
		       case TelephonyManager.NETWORK_TYPE_1xRTT:  
		           return false; // ~ 50-100 kbps  
		       case TelephonyManager.NETWORK_TYPE_CDMA:  
		           return false; // ~ 14-64 kbps  
		       case TelephonyManager.NETWORK_TYPE_EDGE:  
		           return false; // ~ 50-100 kbps  
		       case TelephonyManager.NETWORK_TYPE_EVDO_0:  
		           return true; // ~ 400-1000 kbps  
		       case TelephonyManager.NETWORK_TYPE_EVDO_A:  
		           return true; // ~ 600-1400 kbps  
		       case TelephonyManager.NETWORK_TYPE_GPRS:  
		           return false; // ~ 100 kbps  
		       case TelephonyManager.NETWORK_TYPE_HSDPA:  
		           return true; // ~ 2-14 Mbps  
		       case TelephonyManager.NETWORK_TYPE_HSPA:  
		           return true; // ~ 700-1700 kbps  
		       case TelephonyManager.NETWORK_TYPE_HSUPA:  
		           return true; // ~ 1-23 Mbps  
		       case TelephonyManager.NETWORK_TYPE_UMTS:  
		           return true; // ~ 400-7000 kbps  
		       case TelephonyManager.NETWORK_TYPE_EHRPD:  
		           return true; // ~ 1-2 Mbps  
		       case TelephonyManager.NETWORK_TYPE_EVDO_B:  
		           return true; // ~ 5 Mbps  
		       case TelephonyManager.NETWORK_TYPE_HSPAP:  
		           return true; // ~ 10-20 Mbps  
		       case TelephonyManager.NETWORK_TYPE_IDEN:  
		           return false; // ~25 kbps  
		       case TelephonyManager.NETWORK_TYPE_LTE:  
		           return true; // ~ 10+ Mbps  
		       case TelephonyManager.NETWORK_TYPE_UNKNOWN:  
		           return false;  
		       default:  
		           return false;  
		}  
	}  
	
	/**
	 * 获取手机ip
	 * @return
	 */
	public String getPhoneIp() {
        try {  
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {  
                NetworkInterface intf = en.nextElement();  
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
                    InetAddress inetAddress = enumIpAddr.nextElement();  
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {  
                    //if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {  
                        return inetAddress.getHostAddress().toString();  
                    }  
                }  
            }  
        } catch (Exception e) {  
        }  
        return ""; 
    }
}
