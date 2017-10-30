package com.umeng.soexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.analytics.AnalyticsMainActivity;
import com.umeng.soexample.share.HomeActivity;
import com.umeng.error.ErrorActivity;
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Context mContext;

    private Button mAnalyticsButton;
    private Button mPushButton;
    private Button mShareButton;
    private Button mErrorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        initView();
    }

    private void initView() {
        mAnalyticsButton = (Button) findViewById(R.id.um_analytics_entrance);
        mAnalyticsButton.setOnClickListener(this);
        mPushButton = (Button) findViewById(R.id.um_push_entrance);
        mPushButton.setOnClickListener(this);
        mShareButton = (Button) findViewById(R.id.um_share_entrance);
        mShareButton.setOnClickListener(this);
        mErrorButton = (Button) findViewById(R.id.um_error_entrance);
        mErrorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.um_analytics_entrance:
                Intent intentCommon = new Intent(MainActivity.this, AnalyticsMainActivity.class);
                MainActivity.this.startActivity(intentCommon);
                break;
            case R.id.um_push_entrance:
                Intent intentAnalytics = new Intent(MainActivity.this, com.umeng.message.example.SplashTestActivity.class);
                MainActivity.this.startActivity(intentAnalytics);
                break;
            case R.id.um_share_entrance:
                Intent intentShare = new Intent(MainActivity.this, HomeActivity.class);
                MainActivity.this.startActivity(intentShare);
                break;
            case R.id.um_error_entrance:
                Intent intentError = new Intent(MainActivity.this, ErrorActivity.class);
                MainActivity.this.startActivity(intentError);
                break;
        }
    }
}
