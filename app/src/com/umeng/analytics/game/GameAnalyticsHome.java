package com.umeng.analytics.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.soexample.R;

/**
 * 游戏统计分析(GameAgent)继承于通用统计分析(MobclickAgent),对基本统计分析功能进行扩展，提供一套游戏统计相关的专用API，
 * 需要先正常集成通用统计分析的基本功能，才能使用游戏相关的统计分析。
 *
 * @author ntop
 */
public class GameAnalyticsHome extends Activity {
    private Context mContext;
    private String level = "level-1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_analytics_game);

        mContext = this;
        // 设置输出运行时日志
        UMConfigure.setLogEnabled(true);
        UMGameAgent.init(this);
        // Deprecated UMGameAgent.setPlayerLevel("LV.01");
        UMGameAgent.setPlayerLevel(1);
        // UMGameAgent.setSessionContinueMillis(1000);
//        UMGameAgent.setScenarioType(mContext, EScenarioType.E_UM_GAME);
        UMGameAgent.setScenarioType(mContext, EScenarioType.E_DUM_GAME);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 集成基本统计分析,初始化 Session
        UMGameAgent.onResume(mContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        // //集成基本统计分析, 结束 Session
        UMGameAgent.onPause(mContext);
    }

    /**
     * android:onClick="onButtonClick" 游戏中相关的逻辑演示，比如关卡的开始和结束，游戏中的付费情况或者虚拟消费等。
     *
     * @param view
     */
    public void onButtonClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.umeng_example_analytics_game_start:
                // 关卡开始
                UMGameAgent.startLevel(level);
                break;
            case R.id.umeng_example_analytics_game_fail:
                // 关卡失败
                UMGameAgent.failLevel(level);
                break;
            case R.id.umeng_example_analytics_game_finish:
                // 成功过关
                UMGameAgent.finishLevel(level);
                break;
            case R.id.umeng_example_analytics_game_pay:
                // 使用支付宝 10 元购买 2000 个虚拟币
                UMGameAgent.pay(10, 2000, PayChannels.ZHI_FU_BAO);
                break;
            case R.id.umeng_example_analytics_game_paynbuy:
                // 使用支付宝 10 元购买一把宝剑 (等值虚拟币 1000)
                UMGameAgent.pay(10, "sword", 1, 1000, PayChannels.ZHI_FU_BAO);
                break;
            case R.id.umeng_example_analytics_game_buy:
                // 购买 2 个魔法药水（每个虚拟币单价100）
                UMGameAgent.buy("magic", 2, 100);
                break;
            case R.id.umeng_example_analytics_game_use:
                // 使用 1 个魔法药水（每个虚拟币单价100）
                UMGameAgent.use("magic", 1, 100);
                break;
            case R.id.umeng_example_analytics_game_bonus:
                // 开发商赠送 1000 个虚拟币
                UMGameAgent.bonus(1000, BonusTrigger.KAI_FA_SHANG_ZENG_SONG);
                // 玩家赠送一把宝剑价值 100 个虚拟币
                UMGameAgent.bonus("sword", 1, 100, BonusTrigger.WAN_JIA_ZENG_SONG);
                break;
            case R.id.umeng_example_analytics_game_signin:
                // 用户登录
                UMGameAgent.onProfileSignIn("example_id");
                break;
            case R.id.umeng_example_analytics_game_signoff:
                // 用户退出
                UMGameAgent.onProfileSignOff();
                break;
            case R.id.umeng_example_analytics_game_orderid:
                // 用支付宝 使用了88.88美元(等同于10000个虚拟币) 订单ID是test-ordedid
                UMGameAgent.exchange(88.88, "USD", 10000, PayChannels.ZHI_FU_BAO, "test-ordedid");
                break;
        }
    }

    /**
     * 1-8 为默认支付渠道, 9 ~ 20 保留字段， 21~ 99 为自定义渠道
     *
     * @author ntop
     */
    @SuppressWarnings("unused")
    private interface PayChannels {
        static int APPSTORE = 1; // APPSTORE
        static int ZHI_FU_BAO = 2; // 支付宝(1),
        static int WANG_YIN = 3; // 网银
        static int CAI_FU_TONG = 4;// 财付通(3),
        static int YI_DONG = 5; // 移动(4)
        static int LIAN_TONG = 6; // 联通通信(5),
        static int DIAN_XIN = 7; // 电信
        static int PAYPAL = 8; // PAYPAL

        static int WANDOUJIA = 21;// 自定义支付渠道，豌豆荚支付
    }

    /**
     * <p>
     * 1-3 为默认的游戏奖励触发点
     * </p>
     * <p>
     * 4-20 保留字段
     * </p>
     * <p>
     * 21-99 为自定义触发点（需要在网站上也做出相应的配置）
     * </p>
     *
     * @author ntop
     */
    @SuppressWarnings("unused")
    private interface BonusTrigger {
        static int WAN_JIA_ZENG_SONG = 1;// 玩家赠送(1),
        static int KAI_FA_SHANG_ZENG_SONG = 2;// 开发商赠送(2)
        static int YOU_XI_JIANG_LI = 3;// 游戏奖励(3)

        static int FROM_BOSS = 21;// 自定义触发点，boss 掉落
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Hook();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    // /对于好多应用，会在程序中杀死 进程，这样会导致我们统计不到此时Activity结束的信息，
    // /对于这种情况需要调用 'MobclickAgent.onKillProcess( Context )'
    // /方法，保存一些页面调用的数据。正常的应用是不需要调用此方法的。
    private void Hook() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("退出应用", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                MobclickAgent.onKillProcess(mContext);

                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });
        builder.setNeutralButton("后退一下", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }
}