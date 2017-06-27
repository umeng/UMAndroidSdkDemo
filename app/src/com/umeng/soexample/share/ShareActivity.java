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
 * Created by wangfei on 16/11/9.
 */
public class ShareActivity extends Activity {
    private ListView listView;
    private ShareAdapter shareAdapter;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();

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
                Intent intent = new Intent(ShareActivity.this,ShareDetailActivity.class);
                intent.putExtra("platform",platforms.get(position).mPlatform);
                ShareActivity.this.startActivity(intent);
            }
        });
        ((TextView)findViewById(R.id.umeng_title)).setText(R.string.umeng_share_title);
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
        platforms.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN_FAVORITE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.SINA.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QQ.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QZONE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.ALIPAY.toSnsPlatform());
        platforms.add(SHARE_MEDIA.DINGTALK.toSnsPlatform());
        platforms.add(SHARE_MEDIA.RENREN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.DOUBAN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.SMS.toSnsPlatform());
        platforms.add(SHARE_MEDIA.EMAIL.toSnsPlatform());
        platforms.add(SHARE_MEDIA.YNOTE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.EVERNOTE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LAIWANG.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LAIWANG_DYNAMIC.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LINKEDIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.YIXIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.YIXIN_CIRCLE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.TENCENT.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FACEBOOK.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FACEBOOK_MESSAGER.toSnsPlatform());
        platforms.add(SHARE_MEDIA.VKONTAKTE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.TWITTER.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WHATSAPP.toSnsPlatform());
        platforms.add(SHARE_MEDIA.GOOGLEPLUS.toSnsPlatform());
        platforms.add(SHARE_MEDIA.LINE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.INSTAGRAM.toSnsPlatform());
        platforms.add(SHARE_MEDIA.KAKAO.toSnsPlatform());
        platforms.add(SHARE_MEDIA.PINTEREST.toSnsPlatform());
        platforms.add(SHARE_MEDIA.POCKET.toSnsPlatform());
        platforms.add(SHARE_MEDIA.TUMBLR.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FLICKR.toSnsPlatform());
        platforms.add(SHARE_MEDIA.FOURSQUARE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.DROPBOX.toSnsPlatform());
        platforms.add(SHARE_MEDIA.MORE.toSnsPlatform());

    }
}
