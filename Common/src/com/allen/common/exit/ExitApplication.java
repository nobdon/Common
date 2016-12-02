package com.allen.common.exit;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * 程序完美退出
 * @author Allen
 */
public class ExitApplication extends Application {
	private static ExitApplication instance;
	private List<Activity> activityList = new LinkedList<Activity>();

	private ExitApplication() {
	}

	// 单例模式中获取唯一的ExitApplication 实例
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	// 添加Activity 到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
	// 遍历所有Activity 并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}
