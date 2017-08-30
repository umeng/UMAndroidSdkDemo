package com.umeng.analytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.dplus.AnalyticsHome;
import com.umeng.analytics.game.GameAnalyticsHome;
import com.umeng.soexample.R;

public class AnalyticsMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_main);
    }

    public void onClick(View v) {
        int id = v.getId();
        Intent in = null;
        if (id == R.id.normal) {
            in = new Intent(this, AnalyticsHome.class);
        } else if (id == R.id.game) {
            in = new Intent(this, GameAnalyticsHome.class);
        }

        startActivity(in);
    }

}
