
# ![](images/logo.png) 友盟+组件化 统计SDK/推送SDK/分享SDK 三合一Demo
-------

本项目为友盟+组件化sdk三合一Demo工程，包含 统计SDK(U-App/U-Dplus)/推送SDK/分享SDK 所对应的jar包及Module。同时包含各SDK的相关接口使用例子，本Demo同时支持AndroidStudio开发环境和Eclipse开发环境。SDK集成步骤及功能说明，请参照[线上SDK集成文档](http://dev.umeng.com/sdk_integate/android_sdk/android_common_guide)。

## SDK目录结构 

* PushSDK -- 推送SDK模块目录。
* ShareSDK -- 分享SDK模块目录，包含友盟+目前支持的所有分享平台。
* app -- 三合一Demo工程目录。
* app/libs/umeng-common-x.x.x.jar -- 公共库(必须)。
* app/libs/umeng-analytics-x.x.x.jar -- 统计SDK jar包。


## 组件化SDK初始化接口
统计SDK和分享SDK 仅需在SDK宿主app对应Application的onCreate函数中调用UMConfigure.init函数即可。推送SDK还需要额外调用PushAgent.register()函数进行初始化。



## Demo源码结构

|         包名      |       功能       |
|------------------|-----------------|
| com.umeng.analytics  | 统计SDK Demo相关代码 |
| com.umeng.message.example  | 推送SDK Demo相关代码 |
| com.umeng.soexample  | 分享SDK Demo相关代码(MainActivity.java为各Demo入口界面) |


## 友盟+ H5 Demo

请参照[H5 Demo](https://github.com/umeng/UMengH5Demo/)

