package com.allen.common.gps;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

/**
 * 通过GPS获取地理位置类
 * @author Allen
 *
 */
public class GpsInfo {
	
	public static Location mLocation = null;
	public static Context ctx;
	
	public GpsInfo(Context context) {
		ctx = context;
	}
	
	/**
	 * gps获取当前经度纬度
	 * @return
	 */
	public Location getCurrentGpsLocation() {
		
		setGPS(true); // 强制打开gps

		LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
		GPSLocationListener locationListener = new GPSLocationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		
		if (mLocation == null) {
			mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		if (mLocation != null) {
			mLocation.getLatitude();
			mLocation.getLongitude();
		}
		locationManager.removeUpdates(locationListener);
//		setGPS(false); // 取到经度纬度后关闭gps
		return mLocation;
	}
	
	/**
	 * 强制开启gps 或者关闭gps
	 * 
	 */
	public static void setGPS(boolean status) {
		// 判断当前gps是否开启
    	boolean gpsEnabled = android.provider.Settings.Secure.isLocationProviderEnabled(ctx.getContentResolver(), LocationManager.GPS_PROVIDER);
    	Intent intent = new Intent();
    	intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
    	intent.addCategory("android.intent.category.ALTERNATIVE");
    	// 强制打开
        if(status == true)
        {
	    	if(!gpsEnabled) {	    		
	    		intent.setData(Uri.parse("custom:3"));
	            try {
	            	PendingIntent.getBroadcast(ctx, 0, intent, 0).send();
	            } catch (CanceledException e) {
	            	System.out.println("GPS---->"+e);
	            }
	    	}
        } else {
        	if(gpsEnabled) {	    		
        		intent.setData(Uri.parse("custom:3"));
	            try {
	                PendingIntent.getBroadcast(ctx, 0, intent, 0).send();
	            } catch (CanceledException e) {
	            	System.out.println("GPS---->"+e);
	            }
	    	}
        }
    }
	
	/**
	 * 基站获取当前位置信息
	 * @return
	 */
	public JSONObject getCurrentAPNLocation() {
		 JSONObject holder = new JSONObject();
		try {
	         holder.put("version", "1.1.0");
	         holder.put("host", "maps.google.com");
	         holder.put("address_language", "zh_CN");
	         holder.put("request_address", true);
	         TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
	         GsmCellLocation gcl = (GsmCellLocation) tm.getCellLocation();
	         int cid = gcl.getCid();
	         int lac = gcl.getLac();
	         int mcc = Integer.valueOf(tm.getNetworkOperator().substring(0, 3));
	         int mnc = Integer.valueOf(tm.getNetworkOperator().substring(3, 5));
	         JSONArray array = new JSONArray();
	         JSONObject data = new JSONObject();
	         data.put("cell_id", cid);
	         data.put("location_area_code", lac);
	         data.put("mobile_country_code", mcc);
	         data.put("mobile_network_code", mnc);
	         array.put(data);
	         holder.put("cell_towers", array);
		} catch (Exception e) {
			System.out.println("GPS---->"+e);
		}
		return holder;
	 }
	 
	/**
	 * WIFI获取当前位置信息
	 * @return
	 */
	 private JSONObject getCurrentWIFILocation() {
		 JSONObject holder = new JSONObject();
		 try {
			 holder.put("version", "1.1.0");        
			 holder.put("host", "maps.google.com");       
			 holder.put("address_language", "zh_CN");        
			 holder.put("request_address", true);                
			 WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);               
			 if(wifiManager.getConnectionInfo().getBSSID() == null) {            throw new RuntimeException("bssid is null");        }                
			 JSONArray array = new JSONArray();        
			 JSONObject data = new JSONObject();        
			 data.put("mac_address", wifiManager.getConnectionInfo().getBSSID());          
			 data.put("signal_strength", 8);          
			 data.put("age", 0);          
			 array.put(data);        
			 holder.put("wifi_towers", array);               
		} catch (Exception e) {
			System.out.println("GPS---->"+e);
		}
		 return holder;
	}
	
	/**
	 * 检查GPS是否开启
	 * @return
	 */
	public boolean checkGpsStatus() {
		LocationManager alm = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
		if (!alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
//			// 没有打开gps 跳转到设置界面
//			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			((Activity) ctx).startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
			return false;
		}
		return true;
	}
	
	/**
	 * 当前位置变化监听
	 * @author Allen
	 *
	 */
	class GPSLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			mLocation = location;
		}

		@Override
		public void onProviderDisabled(String provider) {
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
		
	}
}
