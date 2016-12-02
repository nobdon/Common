package com.allen.common.custom;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 自定义等待条
 * @author Allen
 *
 */
public class MyLoading {
	private static ProgressDialog progressDialog;
	
	public static void showProgressBar(Context ctx,String content) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(ctx);
			// 设置进度条风格，风格为圆形，旋转的
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  // ProgressDialog.STYLE_HORIZONTAL样式 就变成进度条了
			progressDialog.setIndeterminate(true);
			// progressDialog.setCancelable(false);
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.setMessage(content);
		try {
			progressDialog.show();
		} catch (Exception e) {
		}
	}
    
	public static void hideProgressBar() {
    	if (progressDialog != null) {
			try {
				progressDialog.dismiss();
			} catch (Exception e) {
			}
		}
	}
}
