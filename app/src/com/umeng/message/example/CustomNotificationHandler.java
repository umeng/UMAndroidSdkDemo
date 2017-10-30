package com.umeng.message.example;

import android.content.Context;
import com.umeng.message.common.UmLog;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

//使用自定义的NotificationHandler，来结合友盟统计处理消息通知
//参考http://bbs.umeng.com/thread-11112-1-1.html
public class CustomNotificationHandler extends UmengNotificationClickHandler {
	
	private static final String TAG = CustomNotificationHandler.class.getName();
	
	@Override
	public void dismissNotification(Context context, UMessage msg) {
		UmLog.d(TAG, "dismissNotification");
		super.dismissNotification(context, msg);
	}
	
	@Override
	public void launchApp(Context context, UMessage msg) {
		UmLog.d(TAG, "launchApp");
		super.launchApp(context, msg);
	}
	
	@Override
	public void openActivity(Context context, UMessage msg) {
		UmLog.d(TAG, "openActivity");
		super.openActivity(context, msg);
	}
	
	@Override
	public void openUrl(Context context, UMessage msg) {
		UmLog.d(TAG, "openUrl");
		super.openUrl(context, msg);
	}
	
	@Override
	public void dealWithCustomAction(Context context, UMessage msg) {
		UmLog.d(TAG, "dealWithCustomAction");
		super.dealWithCustomAction(context, msg);
	}
	
	@Override
	public void autoUpdate(Context context, UMessage msg) {
		UmLog.d(TAG, "autoUpdate");
		super.autoUpdate(context, msg);
	}

}
