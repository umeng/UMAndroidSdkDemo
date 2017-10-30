var isWebviewFlag;

function setWebViewFlag() {
    isWebviewFlag = true;
};

function loadURL(url) {
    var iFrame;
    iFrame = document.createElement("iframe");
    iFrame.setAttribute("src", url);
    iFrame.setAttribute("style", "display:none;");
    iFrame.setAttribute("height", "0px");
    iFrame.setAttribute("width", "0px");
    iFrame.setAttribute("frameborder", "0");
    document.body.appendChild(iFrame);
    iFrame.parentNode.removeChild(iFrame);
    iFrame = null;
};

function exec(funName, args) {
    var commend = {
        functionName : funName,
        arguments : args
    };
    var jsonStr = JSON.stringify(commend);
    var url = "umeng:" + jsonStr;
    loadURL(url);
};

var MobclickAgent = {
    /**
     * 获取Android IMEI
     */
    getDeviceId : function(callBack) {
        if (isWebviewFlag) {
            exec("getDeviceId", [ callBack.name ]);
        }
    },
    /**
     * 自定义事件数量统计
     * 
     * @param eventId
     *            String类型.事件ID，注意需要先在友盟网站注册此ID
     */
    onEvent : function(eventId) {
        if (isWebviewFlag) {
            exec("onEvent", [ eventId ]);
        }
    },
    /**
     * 自定义事件数量统计
     * 
     * @param eventId
     *            String类型.事件ID， 注意需要先在友盟网站注册此ID
     * @param eventLabel
     *            String类型.事件标签，事件的一个属性说明
     */
    onEventWithLabel : function(eventId, eventLabel) {
        if (isWebviewFlag) {
            exec("onEventWithLabel", [ eventId, eventLabel ]);
        }
    },
    /**
     * 自定义事件数量统计
     * 
     * @param eventId
     *            String类型.事件ID， 注意需要先在友盟网站注册此ID
     * @param eventData
     *            Map<String,String>类型.当前事件的属性集合，最多支持10个K-V值
     */
    onEventWithParameters : function(eventId, eventData) {
        if (isWebviewFlag) {
            exec("onEventWithParameters", [ eventId, eventData ]);
        }
    },
    /**
     * 自定义事件数值型统计
     * 
     * @param eventId
     *            String类型.事件ID，注意要先在友盟网站上注册此事件ID
     * @param eventData
     *            Map<String,String>类型.事件的属性集合，最多支持10个K-V值
     * @param eventNum
     *            int 类型.事件持续时长，单位毫秒，您需要手动计算并传入时长，作为事件的时长参数
     * 
     */
    onEventWithCounter : function(eventId, eventData, eventNum) {
        if (isWebviewFlag) {
            exec("onEventWithCounter", [ eventId, eventData, eventNum ]);
        }
    },
    /**
     * 页面统计开始时调用
     * 
     * @param pageName
     *            String类型.页面名称
     */
    onPageBegin : function(pageName) {
        if (isWebviewFlag) {
            exec("onPageBegin", [ pageName ]);
        }
    },
    /**
     * 页面统计结束时调用
     * 
     * @param pageName
     *            String类型.页面名称
     */
    onPageEnd : function(pageName) {
        if (isWebviewFlag) {
            exec("onPageEnd", [ pageName ]);
        }
    },
    /**
     * 统计帐号登录接口 *
     * 
     * @param UID
     *            用户账号ID,长度小于64字节
     */
    profileSignInWithPUID : function(UID) {
        if (isWebviewFlag) {
            exec("profileSignInWithPUID", [ UID ]);
        }
    },
    /**
     * 统计帐号登录接口 *
     * 
     * @param provider
     *            帐号来源.用户通过第三方账号登陆,可以调用此接口进行统计.不能以下划线"_"开头,使用大写字母和数字标识,长度小于32字节;
     *            如果是上市公司,建议使用股票代码.
     * @param UID
     *            用户账号ID,长度小于64字节
     */
    profileSignInWithPUIDWithProvider : function(provider, UID) {
        if (isWebviewFlag) {
            exec("profileSignInWithPUIDWithProvider", [ provider, UID ]);
        }
    },
    /**
     * 帐号统计退出接口
     */
    profileSignOff : function() {
        if (isWebviewFlag) {
            exec("profileSignOff", []);
        }
    },
    /** **************************游戏接口********************************* */
    /**
     * 当玩家建立角色或者升级时，需调用此接口
     * 
     * @param level
     *            int类型.等级
     */
    setUserLevelId : function(level) {
        if (isWebviewFlag) {
            exec("setUserLevelId", [ level ]);
        }
    },
    /**
     * 游戏中关卡开始的时候调用此方法。
     * 
     * @param level
     *            String类型.
     */
    startLevel : function(level) {
        if (isWebviewFlag) {
            exec("startLevel", [ level ]);
        }
    },
    /**
     * 游戏中成功过关的时候调用此方法
     * 
     * @param level
     *            String类型.
     */
    finishLevel : function(level) {
        if (isWebviewFlag) {
            exec("finishLevel", [ level ]);
        }
    },
    /**
     * 游戏中在某一关卡失败的时候调用此方法
     * 
     * @param level
     *            String类型.
     */
    failLevel : function(level) {
        if (isWebviewFlag) {
            exec("failLevel", [ level ]);
        }
    },
    /**
     * 统计订单的接口
     * 
     * @param currencyAmount
     *            double类型.货币金额
     * @param currencyType
     *            String类型.货币类型
     * @param virtualAmount
     *            double类型.虚拟货币金额
     * @param channel
     *            int类型.
     *            </p>
     *            本次消费的途径:1.AppStore 2.支付宝 3.网银 4.财付通 5.移动 6.联通 7.电信 8.paypal
     * @param orderId
     *            String类型.订单ID
     */
    exchange : function(currencyAmount, currencyType, virtualAmount, channel,
            orderId) {
        if (isWebviewFlag) {
            exec("exchange", [ currencyAmount, currencyType, virtualAmount,
                    channel, orderId ]);
        }
    },
    /**
     * 游戏中真实消费（充值）的时候调用此方法 1.AppStore 2.支付宝 3.网银 4.财付通 5.移动 6.联通 7.电信 8.paypal
     * 
     * @param money
     *            double类型.本次消费金额
     * @param coin
     *            double类型.本次消费等值的虚拟币
     * @param source
     *            int类型.本次消费的途径，网银，支付宝 等
     */
    pay : function(money, coin, source) {
        if (isWebviewFlag) {
            exec("pay", [ money, coin, source ]);
        }
    },
    /**
     * 游戏中真实消费（购买物品）的时候调用此方法
     * 
     * @param money
     *            double类型.本地消费金额
     * @param item
     *            String类型. 本次购买物品名称
     * @param number
     *            int类型.本地购买物品数量
     * @param price
     *            double类型.本地购买等值虚拟币
     * @param source
     *            int类型.本次消费的途径，网银支付宝等
     */
    payWithItem : function(money, item, number, price, source) {
        if (isWebviewFlag) {
            exec("payWithItem", [ money, item, number, price, source ]);
        }
    },
    /**
     * 游戏中虚拟消费（购买物品）的时候调用此方法
     * 
     * @param item
     *            String类型.本地购买的物品名称
     * @param number
     *            int类型.本地购买的物品数量
     * @param price
     *            double类型.本地购买花费的虚拟币数量
     */
    buy : function(item, number, price) {
        if (isWebviewFlag) {
            exec("buy", [ item, number, price ]);
        }
    },
    /**
     * 游戏中使用道具时候调用此方法
     * 
     * @param item
     *            String类型. 道具名称
     * @param number
     *            int类型. 数量
     * @param price
     *            double类型.等值虚拟币
     */
    use : function(item, number, price) {
        if (isWebviewFlag) {
            exec("use", [ item, number, price ]);
        }
    },
    /**
     * 游戏中免费获得额外金币时调用此方法
     * 
     * @param item
     *            String类型. 道具名称
     * @param number
     *            int类型. 数量
     * @param price
     *            double类型.等值虚拟币
     * @param source
     *            int类型.本次消费的途径，网银支付宝等
     */
    bonusWithItem : function(item, number, price, source) {
        if (isWebviewFlag) {
            exec("bonusWithItem", [ item, number, price, source ]);
        }
    },
    /**
     * 游戏中免费获得额外金币时调用此方法
     * 
     * @param coin
     *            double类型. 奖励金币数额
     * @param source
     *            int类型.奖励金币的触发原因
     */
    bonus : function(coin, source) {
        if (isWebviewFlag) {
            exec("bonus", [ coin, source ]);
        }
    }

};
