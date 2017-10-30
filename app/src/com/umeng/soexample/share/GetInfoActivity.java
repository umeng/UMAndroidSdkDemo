package com.umeng.soexample.share;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.soexample.R;
import com.umeng.soexample.share.model.ShareAdapter;

/**
 * Created by wangfei on 16/11/10.
 */
public class GetInfoActivity extends Activity {
    private ListView listView;
    private ShareAdapter shareAdapter;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ,SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN,
            SHARE_MEDIA.FACEBOOK,SHARE_MEDIA.TWITTER,SHARE_MEDIA.LINKEDIN,SHARE_MEDIA.KAKAO,SHARE_MEDIA.VKONTAKTE,SHARE_MEDIA.DROPBOX};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.umeng_blue));
        }
        setContentView(R.layout.umeng_share_share);
        listView = (ListView) findViewById(R.id.list);
        initPlatforms();
        shareAdapter  = new ShareAdapter(this,platforms);
        listView.setAdapter(shareAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GetInfoActivity.this,InfoDetailActivity.class);
                intent.putExtra("platform",platforms.get(position).mPlatform);
                GetInfoActivity.this.startActivity(intent);
            }
        });
        ((TextView)findViewById(R.id.umeng_title)).setText(R.string.umeng_getinfo_title);
        findViewById(R.id.umeng_back).setVisibility(View.VISIBLE);
        findViewById(R.id.umeng_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initPlatforms(){
        platforms.clear();
        for (SHARE_MEDIA e :list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())){
                platforms.add(e.toSnsPlatform());
            }
        }
    }
}
