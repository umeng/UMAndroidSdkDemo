package com.umeng.soexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.analytics.AnalyticsMainActivity;
import com.umeng.soexample.share.HomeActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Context mContext;

    private Button mCommonButton;
    private Button mAnalyticsButton;
    private Button mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        initView();
    }

    private void initView() {
        mCommonButton = (Button) findViewById(R.id.um_common_entrance);
        mCommonButton.setOnClickListener(this);
        mAnalyticsButton = (Button) findViewById(R.id.um_analytics_entrance);
        mAnalyticsButton.setOnClickListener(this);
        mShareButton = (Button) findViewById(R.id.um_share_entrance);
        mShareButton.setOnClickListener(this);
        //mShareButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.um_common_entrance:
                Intent intentCommon = new Intent(MainActivity.this, AnalyticsMainActivity.class);
                MainActivity.this.startActivity(intentCommon);
                break;
            case R.id.um_analytics_entrance:
                Intent intentAnalytics = new Intent(MainActivity.this, com.umeng.message.example.MainActivity.class);
                MainActivity.this.startActivity(intentAnalytics);
                break;
            case R.id.um_share_entrance:
                Intent intentShare = new Intent(MainActivity.this, HomeActivity.class);
                MainActivity.this.startActivity(intentShare);
                break;
        }
    }
}
