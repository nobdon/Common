package com.allen.common.comm;

import java.util.Stack;

import android.app.Activity;

/**
 * 自定义activity栈,用于应用退出及跳转管理 
 * 创建人：Allen      
 */
public class ActivityManager {
	private static Stack<Activity> activityStack;

	private static ActivityManager instance;

	private ActivityManager() {
		
	}

	/**
	 * 
	 * getInstance 获取自定义栈实例
	 * 
	 * @return ActivityManager
	 * @Exception 异常对象
	 */
	public static ActivityManager getInstance() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	/**
	 * 
	 * popActivity 弹出
	 * 
	 * @param activity
	 */
	public void popActivity(Activity activity) {
		if (activity != null && activityStack != null) {
			if (activityStack.contains(activity)) {
				activityStack.remove(activity);
				activity.finish();
			}
			activity = null;
		}
	}

	/**
	 * 
	 * currentActivity 返回当前栈顶元素
	 * @param   name    
	 * @param  @return        
	 * @return String      
	 * @Exception 异常对象
	 */
	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack == null) {
			return null;
		}
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 
	 * pushActivity 压栈   
	 * @param   name    
	 * @param  @return        
	 * @return String      
	 * @Exception 异常对象
	 */
	public void pushActivity(Activity activity) {
		if (activityStack == null)
			activityStack = new Stack<Activity>();

		if (activity != null)
			activityStack.add(activity);
	}

	/**
	 * 
	 * popAllActivityExceptOne 关闭除     cls类型以外的所有activity
	 * @param   name    
	 * @param  @return        
	 * @return String      
	 * @Exception 异常对象
	 */
	public void popAllActivityExceptOne(Class<?> cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null || activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	/**
	 * 
	 * popActivityByClassName 通过类名关闭activity     
	 * @param   name    
	 * @param  @return        
	 * @return String      
	 * @Exception 异常对象
	 */
	public void popActivityByClassName(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity != null && activity.getClass().equals(cls))
				popActivity(activity);
		}

	}

	/**
	 * 
	 * popAllActivity关闭所有     
	 * @param   name    
	 * @param  @return        
	 * @return String      
	 * @Exception 异常对象
	 */
	public void popAllActivity() {
		if (activityStack != null) {
			for (Activity activity : activityStack) {
				if (activity != null)
					activity.finish();
			}
			activityStack.clear();
		}
	}
}
