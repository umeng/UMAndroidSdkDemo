package com.umeng.soexample.share;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.socialize.UmengTool;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 16/11/10.
 */
public class CheckActivity extends Activity {
    private LinearLayout list;
    private int checkstyle =0;
    private EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.umeng_blue));

        }
        setContentView(R.layout.umeng_check_share);
        ((TextView)findViewById(R.id.umeng_title)).setText(R.string.umeng_check_title);
        findViewById(R.id.umeng_back).setVisibility(View.VISIBLE);
        list = (LinearLayout) findViewById(R.id.list);
        findViewById(R.id.umeng_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ed = (EditText) findViewById(R.id.editcheck);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.getVisibility()!= View.VISIBLE){
                    list.setVisibility(View.VISIBLE);
                }else {
                    list.setVisibility(View.GONE);
                }
            }
        });
        findViewById(R.id.checkbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (checkstyle ==1){
                  UmengTool.getSignature(CheckActivity.this);
              }else if (checkstyle ==2 ){
                  UmengTool.checkSina(CheckActivity.this);
              }else if (checkstyle ==3 ){
                  UmengTool.checkWx(CheckActivity.this);
              }else if (checkstyle ==4 ){
                  UmengTool.checkAlipay(CheckActivity.this);
              }else if (checkstyle ==5){
                  UmengTool.checkQQ(CheckActivity.this);
              }else if (checkstyle ==6){
                  UmengTool.checkFacebook(CheckActivity.this);
              }
              else if (checkstyle ==7){
                  UmengTool.checkVK(CheckActivity.this);
              }
            }
        });
        findViewById(R.id.checksign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 1;
                ed.setText(R.string.umeng_check_sign);
            }
        });
        findViewById(R.id.checksina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 2;
                ed.setText(R.string.umeng_check_sina);
            }
        });
        findViewById(R.id.checkwx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 3;
                ed.setText(R.string.umeng_check_wx);
            }
        });
        findViewById(R.id.checkalipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 4;
                ed.setText(R.string.umeng_check_alipay);
            }
        });
        findViewById(R.id.checkqq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 5;
                ed.setText(R.string.umeng_check_qq);
            }
        });
        findViewById(R.id.checkfb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 6;
                ed.setText(R.string.umeng_check_fb);
            }
        });
        findViewById(R.id.checkvk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                checkstyle = 7;
                ed.setText(R.string.umeng_check_vk);
            }
        });

    }
}
