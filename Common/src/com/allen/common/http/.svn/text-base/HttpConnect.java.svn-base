package com.allen.common.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/** Http 
 * 客户端与服务端的接口交互类
 * 并获取返回值
 * @author allen
 */
public class HttpConnect {
	private Context ctx;
	private Handler handler;
	private int SUCESS = 1; // 请求成功
	private int ERROR = -1; // 异常
	private int NONE = 0;   // 请求失败
	public HttpConnect(Context context, Handler handler) {
		this.ctx = context;
		this.handler = handler;
	}

	public void httppost(String url,String pramas) {
		// 第一步，创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		// 设置HTTP POST请求参数必须用NameValuePair对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("obj", pramas));
		System.out.println("result1");
		// 设置httpPost请求参数
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 第二步，使用execute方法发送HTTP GET请求，并返回HttpResponse对象
			HttpResponse httpResponse;
			try {
				httpResponse = new DefaultHttpClient().execute(httpPost);
				System.out.println("result");
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					// 第三步，使用getEntity方法活得返回结果
					String result = EntityUtils.toString(httpResponse
							.getEntity());
					sendManage(SUCESS, result);
					System.out.println("result" + result);
				}else{
					sendManage(ERROR,"");
					System.out.println(EntityUtils.toString(httpResponse
							.getEntity()));
				}
			} catch (Exception e) {
				sendManage(NONE,"");
				System.out.println("e--->"+e);
			}
		} catch (UnsupportedEncodingException e) {
			sendManage(ERROR,"");
			System.out.println("e--->"+e);
		}
	}
	
	
	
	/**
	 * 获取版本的xml配置文件
	 * @return
	 */
//	public String getVersionXml() {
//		String result = null;
//		// 服务器响应字符串
//		HttpClient client = new DefaultHttpClient();
//		// GET方法请求
//		BufferedReader reader = null;
//		HttpGet get = new HttpGet(CommParams.VERSION_XML_URL);
//		try {
//			HttpResponse response = client.execute(get);
//			reader = new BufferedReader(new InputStreamReader(response
//					.getEntity().getContent(), "UTF-8"));
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		StringBuffer sb = new StringBuffer();
//		String line = null;
//		try {
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//			reader.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		result = sb.toString();
//		
//		return result;
//	}
	
	
	// 返回数据和状态码
	public void sendManage(int what,String string) {
		if(null != handler) {
			Message message = handler.obtainMessage(what, string);
			handler.sendMessage(message);
		}
	}
	
	
	
}
