package com.umeng.analytics.dplus;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.umeng.soexample.R;

import com.umeng.analytics.MobclickAgent;

public class WebviewAnalytic extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umeng_example_analytics_webview);

        WebView webView = (WebView) findViewById(R.id.webview);
        if (Build.VERSION.SDK_INT > 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/demo.html");
        webView.setWebViewClient(new MyWebviewClient());

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:setWebViewFlag()");
            if (url != null && url.endsWith("/index.html")) {
                MobclickAgent.onPageStart("index.html");
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                Log.d("UMHybrid", "shouldOverrideUrlLoading url:" + url);
                String decodedURL = java.net.URLDecoder.decode(url, "UTF-8");
                UMHybrid.getInstance(WebviewAnalytic.this).execute(decodedURL, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

}
