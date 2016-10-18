package com.fanhy.rongcloudim.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.fanhy.rongcloudim.contans.ContansURL;
import com.fanhy.rongcloudim.entity.BaseUser;
import com.fanhy.rongcloudim.util.OkHttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by huayong on 2016/10/16.
 */

public class RongCloudApp extends Application {
    public static String appKey = "p5tvi9dstiv54";// key
    public static String appSecret = "blMNAvgLqfCDL6";// secret
    public static String token = "";
    public static BaseUser baseUser;
    public static List<BaseUser> baseUserList = new ArrayList<>();
    public static String talkGroupId;

    @Override
    public void onCreate() {
        super.onCreate();
        String pckName = getApplicationInfo().packageName;
        String processName = getCurProcessName(this);
        // 避免多进程时，多次初始化
        if (pckName.equals(processName)) {
            RongIM.init(this);// 初始化IM SDK

            // 或取用户列表
            String url = ContansURL.getAllUser;
            OkHttpUtil.get(url, callback);
        }
    }

    /**
     * 获取应用程序当前运行进程名
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 建立与融云服务器的连接
     * @param token
     */
    public static void connect(String token) {
        /**
         * IMKit SDK调用第二步,建立与服务器的连接
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {
                Log.d("fanhy", "--onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {
                Log.d("fanhy", "--onSuccess" + userid);
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("fanhy", "--onError" + errorCode);
            }
        });
    }

    OkHttpUtil.ResultCallback callback = new OkHttpUtil.ResultCallback() {
        @Override
        public void onSuccess(Object response) {
            BaseUser[] arr = new Gson().fromJson(response.toString(), BaseUser[].class);
            baseUserList.clear();
            baseUserList.addAll(Arrays.asList(arr));
        }

        @Override
        public void onFailure(Exception e) {
            Log.d("fanhy", "onFailure----用户列表"+"异常:"+e.toString());
        }
    };
}
