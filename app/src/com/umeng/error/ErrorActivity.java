package com.umeng.error;

/**
 * Created by zhz on 2017/8/31.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.dplus.AnalyticsHome;
import com.umeng.analytics.game.GameAnalyticsHome;
import com.umeng.soexample.R;
import android.util.Log;


public class ErrorActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_main);
    }

    public void onClick(View v) {
        int id = v.getId();
        Intent in = null;
        if (id == R.id.java) {
            Log.i("break","java");
            beginA();
        } else if (id == R.id.cpp) {
            Log.i("break","cpp");
            stringFromJNI();
        }
    }
    private void beginA(){
        beginb();
    }
    private void beginb(){
        beginc();
    }
    private void beginc(){
        begind();
    }
    private void begind(){
        begine();
    }
    private void begine(){
        beginf();
    }
    private void beginf(){
        int result = 10 / 0;
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
