package com.umeng.message.example;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

public class Helper {
	
	public static boolean isServiceRunning(Context context, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
		if(serviceList == null || serviceList.isEmpty())
			return false;
		for(int i = 0; i < serviceList.size(); i++) {
			if(serviceList.get(i).service.getClassName().equals(className) && TextUtils.equals(
					serviceList.get(i).service.getPackageName(), context.getPackageName())) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}

}
