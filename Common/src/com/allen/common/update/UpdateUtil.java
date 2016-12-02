package com.allen.common.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.allen.common.R;
import com.allen.common.custom.MyAlertDialog;
import com.allen.common.http.HttpConnect;

/**
 * 更新工具类
 * @author Allen
 *
 */
public class UpdateUtil {
	private Context mContext;
	// 提示语
	public String updateMsg ;
	// 返回的安装包url
	private String apkUrl = "";
	private Dialog noticeDialog;
	private String apkName = "WTS.apk";
	/* 下载包安装路径*/
	private static  String savePath ;
	private static  String saveFileName ;
	/* 进度条与通知ui刷新的handler和msg常量 */
	private ProgressBar mProgress;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private int progress;
	private Thread downLoadThread;
	private boolean interceptFlag = false;
	private Dialog dialog;
	private String apkLoadUrl,versionCode,updateContent;
	
	
	/**
	 * 检查是否有新的版本
	 */
	public void checkUpdate() {
		try {
			// 解析xml
			praseXML(getVersionXml("http://www.angico.cn/app/apk.xml"));
		} catch (Exception e) {
			System.out.println("startactivity 获取版本信息异常--->" + e);
		}
	}
	
	/**
	 * 解析version XML文件
	 * 
	 * @param info
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public void praseXML(String info) throws XmlPullParserException,
			IOException {

		// 定义工厂 XmlPullParserFactory
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		// 定义解析器 XmlPullParser
		XmlPullParser xmlparser = factory.newPullParser();
		xmlparser.setInput(new StringReader(info));
		int evtType = xmlparser.getEventType();
		while (evtType != XmlPullParser.END_DOCUMENT) {
			switch (evtType) {
			case XmlPullParser.START_TAG:
				String tagName = xmlparser.getName();

				// 设置值
				if (tagName.equalsIgnoreCase("url")) {
					apkLoadUrl = xmlparser.nextText();
					apkUrl = xmlparser.nextText();
				} else if (tagName.equalsIgnoreCase("versionCode")) {
					versionCode = xmlparser.nextText();
				} else if (tagName.equalsIgnoreCase("updateContent")) {
					updateContent = xmlparser.nextText();
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			evtType = xmlparser.next();
		}

		// 对比版本号提示更新
		comPareVersion();
	}

	/**
	 * 对比本地和服务端的版本号
	 */
	public void comPareVersion() {
		// 对比版本信息
		PackageManager manager = mContext.getPackageManager();
		String localVersion = null;
		try {
			PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
			localVersion = info.versionName;
			// 获取不到正确的版本号的时候 直接返回
			if("".equals(versionCode) || null == versionCode) {
				return;
			}
			// 如果版本不一致 提示更新
			if (!localVersion.equals(versionCode)) {
				checkUpdateInfo();
			} 
		} catch (Exception e) {
			System.out.println("startactivity 对比版本信息异常--->" + e);
		}
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				if(dialog!=null){
					dialog.dismiss();
					dialog = null;
				}
				installApk();
				
				break;
			default:
				break;
			}
		};
	};

	public UpdateUtil(Context context) {
		this.mContext = context;
		if(getSDPath()!=null){
			savePath =getSDPath()+"/"+"download"+"/";
		}else{
			
		}
		saveFileName = savePath + apkName;
		updateMsg = "检测到新版本，是否更新？";
	}

	// 外部接口让主Activity调用
	public void checkUpdateInfo() {
		showDialog();
	}
	
	public String getSDPath(){ 
        File sdDir = null;
      //判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState()   
                            .equals(android.os.Environment.MEDIA_MOUNTED);   
        if(sdCardExist){
              //获取根目录
          sdDir = Environment.getExternalStorageDirectory();
        } 
        if(sdDir!=null){
        	return sdDir.toString(); 
        }else{
        	return null;
        }
        
	} 

	
	
	private void showDialog() {
		final MyAlertDialog ad=new MyAlertDialog(mContext);
		ad.setTitle("更新提示");
		ad.setMessage(updateMsg);
		ad.setPositiveButton("现在更新", new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ad.dismiss();
				showDownloadDialog2();
			}
		});
		
		ad.setNegativeButton("以后再说", new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ad.dismiss();
			}
		});

	}

	private void showDownloadDialog2() {
		dialog = new Dialog(mContext,R.style.dialog);
		LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflater.inflate(R.layout.myprogressbar, null);
		dialog.setContentView(view);
		mProgress = (ProgressBar)dialog.findViewById(R.id.progressBar1);
		Button btn = (Button) dialog.findViewById(R.id.progress_btn);
		//设置进度条是否自动旋转,即设置其不确定模式,false表示不自动旋转 
		 
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				interceptFlag = true;
			}
		});
		 
		 dialog.show();
		 downloadApk();
	}
	
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				if(savePath==null){
					if(dialog!=null){
						dialog.dismiss();
						dialog = null;
					}
					return;
				}
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);
				int count = 0;
				byte buf[] = new byte[1024];
				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载
				fos.close();
				is.close();
				if(dialog!=null){
					dialog.dismiss();
					dialog = null;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				if(dialog!=null){
					dialog.dismiss();
					dialog = null;
				}
				e.printStackTrace();
			}
		}
	};

	/**
	 * 下载apk
	 * 
	 * @param url
	 */
	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(intent);
	}
	
	/**
	 * 获取服务端版本的xml配置文件
	 * @return
	 */
	public String getVersionXml(String updateXmlUrl) {
		String result = null;
		// 服务器响应字符串
		HttpClient client = new DefaultHttpClient();
		// GET方法请求
		BufferedReader reader = null;
		HttpGet get = new HttpGet(updateXmlUrl);
		try {
			HttpResponse response = client.execute(get);
			reader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent(), "UTF-8"));
		} catch (Exception e) {
			System.out.println(e);
		}
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		result = sb.toString();
		
		return result;
	}
}
