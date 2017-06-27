package com.umeng.analytics.dplus;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.game.UMGameAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UMHybrid {
    private static Context mContext = null;
    /**
     * 可以设置是否为游戏，如果是游戏会进行初始化
     */
    private static boolean isGameInited = false;

    private static class Holder {
        private static final UMHybrid INSTANCE = new UMHybrid();
    }

    private UMHybrid() {
    }

    /**
     * 初始化游戏
     */
    private void initGame() {
        UMGameAgent.init(mContext);
        UMGameAgent.setPlayerLevel(1);
        MobclickAgent.setScenarioType(mContext, EScenarioType.E_UM_GAME);
        isGameInited = true;
    }

    public static UMHybrid getInstance(Context context) {
        if (context != null) {
            mContext = context.getApplicationContext();
        }
        return Holder.INSTANCE;
    }

    public void execute(final String url, final WebView webView) throws Exception {

        if (url.startsWith("umeng")) {
            String str = url.substring(6);

            JSONObject jsonObj = new JSONObject(str);
            String functionName = jsonObj.getString("functionName");
            JSONArray args = jsonObj.getJSONArray("arguments");
            // Log.d("UMHybrid", "functionName:" + functionName + "|||args:" +
            // args.toString());
            if (functionName.equals("getDeviceId")) {
                getDeviceId(args, webView);
            } else {
                Class<UMHybrid> classType = UMHybrid.class;
                Method method = classType.getDeclaredMethod(functionName, JSONArray.class);
                method.invoke(getInstance(mContext), args);
            }
        }
    }

    private void getDeviceId(JSONArray args, WebView webView) {
        Log.d("UMHybrid", "getDeviceId  args:" + args.toString());
        try {
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = tm.getDeviceId();
            String callBack = args.getString(0);
            webView.loadUrl("javascript:" + callBack + "('" + deviceId + "')");
        } catch (Exception e) {
            e.toString();
        }
    }


    @SuppressWarnings("unused")
    private void onEvent(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "onEvent  args:" + args.toString());
        String eventId = args.getString(0);
        MobclickAgent.onEvent(mContext, eventId);
    }

    @SuppressWarnings("unused")
    private void onEventWithLabel(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "onEventWithLabel  args:" + args.toString());
        String eventId = args.getString(0);
        String label = args.getString(1);
        MobclickAgent.onEvent(mContext, eventId, label);
    }

    @SuppressWarnings({"unused", "unchecked"})
    private void onEventWithParameters(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "onEventWithParameters  args:" + args.toString());
        String eventId = args.getString(0);
        JSONObject obj = args.getJSONObject(1);
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> it = obj.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object o = obj.get(key);
            if (o instanceof Integer) {
                String value = String.valueOf(o);
                map.put(key, value);
            } else if (o instanceof String) {
                String strValue = (String) o;
                map.put(key, strValue);
            }
        }
        MobclickAgent.onEvent(mContext, eventId, map);
    }

    @SuppressWarnings({"unused", "unchecked"})
    private void onEventWithCounter(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "onEventWithCounter  args:" + args.toString());
        String eventId = args.getString(0);
        JSONObject obj = args.getJSONObject(1);
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> it = obj.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object o = obj.get(key);
            if (o instanceof Integer) {
                String value = String.valueOf(o);
                map.put(key, value);
            } else if (o instanceof String) {
                String strValue = (String) o;
                map.put(key, strValue);
            }
        }
        int value = args.getInt(2);
        MobclickAgent.onEventValue(mContext, eventId, map, value);
    }

    @SuppressWarnings({"unused"})
    private void onPageBegin(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "onPageBegin  args:" + args.toString());
        String pageName = args.getString(0);
        MobclickAgent.onPageStart(pageName);
    }

    @SuppressWarnings({"unused"})
    private void onPageEnd(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "onPageEnd  args:" + args.toString());
        String pageName = args.getString(0);
        MobclickAgent.onPageEnd(pageName);
    }

    @SuppressWarnings({"unused"})
    private void profileSignInWithPUID(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "profileSignInWithPUID  args:" + args.toString());
        String puid = args.getString(0);
        MobclickAgent.onProfileSignIn(puid);
    }

    @SuppressWarnings({"unused"})
    private void profileSignInWithPUIDWithProvider(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "profileSignInWithPUIDWithProvider  args:" + args.toString());
        String puid = args.getString(0);
        String provider = args.getString(1);
        MobclickAgent.onProfileSignIn(puid, provider);
    }

    @SuppressWarnings({"unused"})
    private void profileSignOff(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "profileSignOff");
        MobclickAgent.onProfileSignOff();
    }

    @SuppressWarnings({"unused"})
    private void setUserLevelId(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "setUserLevelId [" + isGameInited + "] args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        int level = args.getInt(0);
        UMGameAgent.setPlayerLevel(level);
    }

    @SuppressWarnings({"unused"})
    private void startLevel(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "startLevel  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        String level = args.getString(0);
        UMGameAgent.startLevel(level);
    }

    @SuppressWarnings({"unused"})
    private void failLevel(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "failLevel  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        String level = args.getString(0);
        UMGameAgent.failLevel(level);
    }

    @SuppressWarnings({"unused"})
    private void finishLevel(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "finishLevel  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        String level = args.getString(0);
        UMGameAgent.finishLevel(level);
    }

    @SuppressWarnings({"unused"})
    private void exchange(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "exchange  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        double currencyAmount = args.getDouble(0);
        String currencyType = args.getString(1);
        double virtualAmount = args.getDouble(2);
        int channel = args.getInt(3);
        String orderId = args.getString(4);
        UMGameAgent.exchange(currencyAmount, currencyType, virtualAmount, channel, orderId);
    }

    @SuppressWarnings({"unused"})
    private void pay(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "pay  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        double money = args.getDouble(0);
        double coin = args.getDouble(1);
        int source = args.getInt(2);
        UMGameAgent.pay(money, coin, source);
    }

    @SuppressWarnings({"unused"})
    private void payWithItem(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "payWithItem  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        double money = args.getDouble(0);
        String item = args.getString(1);
        int number = args.getInt(2);
        double price = args.getDouble(3);
        int source = args.getInt(4);
        UMGameAgent.pay(money, item, number, price, source);
    }

    @SuppressWarnings({"unused"})
    private void buy(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "buy  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        String item = args.getString(0);
        int number = args.getInt(1);
        double price = args.getDouble(2);
        UMGameAgent.buy(item, number, price);
    }

    @SuppressWarnings({"unused"})
    private void use(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "use  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        String item = args.getString(0);
        int number = args.getInt(1);
        double price = args.getDouble(2);
        UMGameAgent.use(item, number, price);
    }

    @SuppressWarnings({"unused"})
    private void bonus(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "bonus  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        double coin = args.getDouble(0);
        int source = args.getInt(1);
        UMGameAgent.bonus(coin, source);
    }

    @SuppressWarnings({"unused"})
    private void bonusWithItem(final JSONArray args) throws JSONException {
        Log.d("UMHybrid", "bonusWithItem  args:" + args.toString());
        if (!isGameInited) {
            initGame();
        }
        String item = args.getString(0);
        int number = args.getInt(1);
        double price = args.getDouble(2);
        int source = args.getInt(3);
        UMGameAgent.bonus(item, number, price, source);
    }
}
