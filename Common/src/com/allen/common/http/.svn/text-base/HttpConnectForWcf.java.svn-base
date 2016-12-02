package com.allen.common.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**http 访问wcf接口
 * 客户端与服务端的接口交互类
 * 并获取返回值
 * @author ysj
 */
public class HttpConnectForWcf {
	private Context ctx;
	private Handler handler;
	private int SUCESS = 1; // 请求成功
	private int ERROR = -1; // 异常
	private int NONE = 0;   // 请求失败
	public static HttpGet request = new HttpGet();
	public static HttpPost httpPost = new HttpPost();
	public static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public HttpConnectForWcf(Context context, Handler handler) {
		this.ctx = context;
		this.handler = handler;
	}
	
	/**
	 * GET 请求服务端 得到返回值
	 * @param _url
	 */
	public void getData(String url) {
		System.out.println("URI : "+url);
		try {
			// get请求 还有一种httppost
			URI uri = URI.create(url);
//			HttpGet request = new HttpGet(url);
			request.setURI(uri);
			// 设置请求头
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
	        // 发起请求
	        HttpResponse response = httpClient.execute(request);
	        // 判断是否请求成功
	        if(response.getStatusLine().getStatusCode() == 200) {
	        	// 注意 response.getEntity 只能调用一次  
				// 否则就会报异常     java.lang.IllegalStateException: Content has been consumed
		        // 转成String
		        String result = EntityUtils.toString(response.getEntity());
		        	sendManage(SUCESS, result);
		 //       System.out.println(result);
		        
		        // 活得返回值通过流转成string
//		        HttpEntity responseEntity = response.getEntity();
//		        char[] buffer = new char[(int)responseEntity.getContentLength()];
//		        InputStream stream = responseEntity.getContent();
//		        InputStreamReader reader = new InputStreamReader(stream);
//		        reader.read(buffer);
//		        stream.close();
		        // 得到String类型的返回值 返回到前端
//		        String result = new String(buffer);
	        }else{
	        	sendManage(NONE, "");
	        }
		} catch (Exception e) {
			System.out.println(e);
			// 异常
			sendManage(ERROR, "");
		}
	}
	
	/**
	 * wcf POST 请求服务端
	 */
	public void dataPost(String _data,String _url) {
		try {
			URI uri = URI.create(_url);
			
			httpPost.setURI(uri);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			StringEntity entity = new StringEntity( _data, HTTP.UTF_8);
//			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			
			
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println("返回码:"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				sendManage(SUCESS, result);
			}else{
				sendManage(NONE, "");
			}
		} catch (Exception e) {
			sendManage(ERROR, "");
			System.out.println(e);
		}
	}
	
	// 返回数据和状态码
	public void sendManage(int what,String string) {
		if(null != handler) {
			Message message = handler.obtainMessage(what, string);
			handler.sendMessage(message);
		}
	}
	
	
	
}
