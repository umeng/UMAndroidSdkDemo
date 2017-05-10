/**
 * Copyright (C) 2013 Umeng, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.umeng.message.example;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.example.notification.DebugNotification;
import com.umeng.message.tag.TagManager;
import com.umeng.CommonApplication;
import com.umeng.soexample.R;

import java.util.List;

public class MainActivity extends Activity {
    private String Isenable = "false";
    protected static final String TAG = MainActivity.class.getSimpleName();
    private ImageButton btnAddTag, btnListTag, btnAddAlias, btnAliasType, btnAddExclusiveAlias,
            btnDeletTag, btnDeletAlias, btnSerialNet, btnAppInfo;
    private EditText edalias, edaliasType;
    private PushAgent mPushAgent;
    private TextView applog;
    private MyReceiver myReceiver;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_main);
        handler = new Handler();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CommonApplication.UPDATE_STATUS_ACTION);
        registerReceiver(myReceiver, filter);

        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        initUi();
    }

    /**
     * 初始化UI界面
     */
    private void initUi() {
        btnAddTag = (ImageButton) findViewById(R.id.um_addtags);
        btnDeletTag = (ImageButton) findViewById(R.id.um_delettags);
        btnListTag = (ImageButton) findViewById(R.id.um_listtags);
        btnAddAlias = (ImageButton) findViewById(R.id.um_setalias);
        btnAliasType = (ImageButton) findViewById(R.id.um_aliastype);
        btnAddExclusiveAlias = (ImageButton) findViewById(R.id.um_exclalias);
        btnDeletAlias = (ImageButton) findViewById(R.id.umeng_deletalias);
        btnSerialNet = (ImageButton) findViewById(R.id.umeng_serialnet);
        btnAppInfo = (ImageButton) findViewById(R.id.um_appinfo);
        edalias = (EditText) findViewById(R.id.um_etalias);
        edaliasType = (EditText) findViewById(R.id.um_etaliastype);
        applog = (TextView) findViewById(R.id.log);
        btnAddTag.setOnClickListener(clickListener);
        btnDeletTag.setOnClickListener(clickListener);
        btnListTag.setOnClickListener(clickListener);
        btnAddAlias.setOnClickListener(clickListener);
        btnAliasType.setOnClickListener(clickListener);
        btnAddExclusiveAlias.setOnClickListener(clickListener);
        btnDeletAlias.setOnClickListener(clickListener);
        btnSerialNet.setOnClickListener(clickListener);
        btnAppInfo.setOnClickListener(clickListener);
        btnAliasType.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(0, 1, 0, "SINA_WEIBO");
                menu.add(0, 2, 1, "BAIDU");
                menu.add(0, 3, 2, "KAIXIN");
                menu.add(0, 4, 3, "QQ");
                menu.add(0, 5, 4, "RENREN");
                menu.add(0, 6, 5, "TENCENT_WEIBO");
                menu.add(0, 7, 6, "WEIXIN");
            }
        });
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnAddTag) {
                addTag();
            } else if (v == btnDeletTag) {
                deletTag();
            } else if (v == btnListTag) {
                listTag();
            } else if (v == btnAddAlias) {
                addAlias();
            } else if (v == btnAliasType) {
                addAliasType();
            } else if (v == btnAddExclusiveAlias) {
                AddExclusiveAlias();
            } else if (v == btnDeletAlias) {
                deletAlias();
            } else if (v == btnSerialNet) {
                SerialNet();
            } else if (v == btnAppInfo) {
                appInfo();
//                InAppManager.showCardMessage(MainActivity.this, "card");
            }

        }
    };

    private void appInfo() {
        String pkgName = getApplicationContext().getPackageName();
        String info = String.format("DeviceToken:%s\n" + "SdkVersion:%s\nAppVersionCode:%s\nAppVersionName:%s",
                mPushAgent.getRegistrationId(), MsgConstant.SDK_VERSION,
                UmengMessageDeviceConfig.getAppVersionCode(this), UmengMessageDeviceConfig.getAppVersionName(this));
        updatelog("应用包名:" + pkgName + "\n" + info);
    }

    private void SerialNet() {
        DebugNotification.transmission(MainActivity.this, handler);
    }

    private void deletAlias() {
        String exclusiveAlias = edalias.getText().toString();
        String aliasType = edaliasType.getText().toString();
        if (TextUtils.isEmpty(exclusiveAlias)) {
            toast("请先输入要删除的 Alias");
            return;
        }
        if (TextUtils.isEmpty(aliasType)) {
            toast("请先输入要删除的 Alias Type");
            return;
        }
        mPushAgent.removeAlias(exclusiveAlias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String s) {
                if (Boolean.TRUE.equals(isSuccess))
                    Log.i(TAG, "delet alias was set successfully.");

                final boolean success = isSuccess;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        edalias.setText("");
                        edaliasType.setText("");
                        updatelog("Delet Alias:" + (success ? "Success" : "Fail"));
                    }
                });
            }
        });
    }

    private void AddExclusiveAlias() {
        String exclusiveAlias = edalias.getText().toString();
        String aliasType = edaliasType.getText().toString();
        if (TextUtils.isEmpty(exclusiveAlias)) {
            toast("请先输入Exclusive Alias");
            return;
        }
        if (TextUtils.isEmpty(aliasType)) {
            toast("请先输入Alias Type");
            return;
        }
        mPushAgent.addExclusiveAlias(exclusiveAlias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                Log.i(TAG, "isSuccess:" + isSuccess + "," + message);
                if (Boolean.TRUE.equals(isSuccess))
                    Log.i(TAG, "exclusive alias was set successfully.");

                final boolean success = isSuccess;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        edalias.setText("");
                        updatelog("Add Exclusive Alias:" + (success ? "Success" : "Fail"));
                    }
                });
            }
        });
    }

    private void addAliasType() {
        btnAliasType.showContextMenu();

    }

    private void addAlias() {
        String alias = edalias.getText().toString();
        String aliasType = edaliasType.getText().toString();
        if (TextUtils.isEmpty(alias)) {
            toast("请先输入Alias");
            return;
        }
        if (TextUtils.isEmpty(aliasType)) {
            toast("请先输入Alias Type");
            return;
        }
        mPushAgent.addAlias(alias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                Log.i(TAG, "isSuccess:" + isSuccess + "," + message);
                if (isSuccess)
                    Log.i(TAG, "alias was set successfully.");

                final boolean success = isSuccess;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        edalias.setText("");
                        edaliasType.setText("");
                        updatelog("Add Alias:" + (success ? "Success" : "Fail"));
                    }
                });
            }
        });
    }

    private void listTag() {
        mPushAgent.getTagManager().list(new TagManager.TagListCallBack() {
            @Override
            public void onMessage(final boolean isSuccess, final List<String> result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            if (result != null) {
                                StringBuilder info = new StringBuilder();
                                info.append("Tags:");
                                for (int i = 0; i < result.size(); i++) {
                                    String tag = result.get(i);
                                    info.append("\n" + tag);
                                }
                                updatelog(info.toString());
                            } else {
                                updatelog("");
                            }
                        } else {
                            updatelog("Tags获取失败");
                        }

                    }
                });

            }
        });
    }

    private void deletTag() {
        final String tag = edalias.getText().toString();
        if (TextUtils.isEmpty(tag)) {
            toast("请输入要删除的Tag");
            return;
        }
        mPushAgent.getTagManager().delete(new TagManager.TCallBack() {
            @Override
            public void onMessage(boolean isSuccess, final ITagManager.Result result) {
                Log.i(TAG, "isSuccess:" + isSuccess);
                if (isSuccess)
                    Log.i(TAG, "deletTag was set successfully.");
                final boolean success = isSuccess;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updatelog("delet Tags:" + (success ? "Success" : "Fail"));
                    }
                });
            }
        }, tag);
    }

    private void addTag() {
        String tag = edalias.getText().toString();
        if (TextUtils.isEmpty(tag)) {
            toast("请先输入Tag");
            return;
        }
        mPushAgent.getTagManager().add(new TagManager.TCallBack() {
            @Override
            public void onMessage(final boolean isSuccess, final ITagManager.Result result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        edalias.setText("");
                        if (isSuccess) {
                            updatelog("Add Tag:" + result);
                        } else {
                            updatelog("Add Tag:" + "加入tag失败");
                        }
                    }
                });
            }
        }, tag);
    }

    public void updatelog(String log) {
        applog.append(log + "\n");
    }

    private Toast mToast;

    public void toast(String str) {
        if (mToast == null)
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setText(str);
        mToast.show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        edaliasType.setText(item.getTitle());
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.umeng_main_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_clearlog:
                applog.setText("");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            appInfo();
        }
    }
}