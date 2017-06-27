package com.umeng.soexample.share;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 16/11/10.
 */
public class InfoDetailActivity extends Activity {
    private SHARE_MEDIA share_media;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.umeng_blue));
        }
        setContentView(R.layout.infodetail_share);
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("platform");
        ((TextView) findViewById(R.id.umeng_title)).setText(R.string.umeng_getinfo_title);
        findViewById(R.id.umeng_back).setVisibility(View.VISIBLE);
        result = (TextView) findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod()); // 导致gc lazy

        findViewById(R.id.umeng_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.getbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UMShareAPI.get(InfoDetailActivity.this).getPlatformInfo(InfoDetailActivity.this, share_media, authListener);
            }
        });
        findViewById(R.id.umeng_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("结果：");
            }
        });
        findViewById(R.id.umeng_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) getSystemService(
                    Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(result.getText());
                Toast.makeText(InfoDetailActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String temp = "";
            for (String key : data.keySet()) {
                temp = temp + key + " : " + data.get(key) + "\n";
            }
            result.setText(temp);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            result.setText("错误" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
