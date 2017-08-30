package com.umeng.message.example;


import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.inapp.UmengSplashMessageActivity;

/**
 * 全屏消息，推荐作为启动页使用
 */
public class SplashTestActivity extends UmengSplashMessageActivity {

    @Override
    public boolean onCustomPretreatment() {
        InAppMessageManager mInAppMessageManager = InAppMessageManager.getInstance(this);
        //设置应用内消息为debug模式
        mInAppMessageManager.setInAppMsgDebugMode(true);
        mInAppMessageManager.setMainActivityPath("com.umeng.message.example.MainActivity");
        return super.onCustomPretreatment();
    }
}