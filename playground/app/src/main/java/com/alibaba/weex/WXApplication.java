package com.alibaba.weex;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.alibaba.weex.commons.adapter.ImageAdapter;
import com.alibaba.weex.commons.util.SpUtils;
import com.alibaba.weex.constants.Constants;
import com.alibaba.weex.extend.PlayDebugAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;

/** don' t delete or edit it, it will be used while packaging */
import com.taobao.signcheck.SignCheck;
import java.security.cert.CertificateEncodingException;

public class WXApplication extends Application {

    /** don't delete or edit it, it will be used while packaging */
    /** weex tag head */
    /** weex tag tail */
    private SpUtils mSpUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            ApplicationInfo appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            if (appInfo != null && appInfo.metaData != null) {
                String weexIndex = appInfo.metaData.getString("weex_index");
                if (!TextUtils.isEmpty(weexIndex)) {
                    //设置Week 入口
                    Constants.WeexIndex.setWeexIndex(weexIndex);
                }

                if (BuildConfig.DEBUG) {
                    //设置调试的服务器Ip
                    String debugHost = appInfo.metaData.getString("debug_host");
                    if (!TextUtils.isEmpty(debugHost) && debugHost.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                        initDebugEnvironment(true, debugHost/*"DEBUG_SERVER_HOST"*/);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        WXSDKEngine.initialize(this,
                new InitConfig.Builder()
                        .setImgAdapter(new ImageAdapter())
                        .setDebugAdapter(new PlayDebugAdapter())
                        .build()
        );

        try {
            Fresco.initialize(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //don't delete it, it will be used in release version
        mSpUtils = new SpUtils(this);

        /** don' t delete or edit it, it will be used while packaging */
        /** weex package prevent decompile head */
        /** weex package prevent decompile tail */
    }

    /**
     * @param enable enable remote debugger. valid only if host not to be "DEBUG_SERVER_HOST".
     *               true, you can launch a remote debugger and inspector both.
     *               false, you can  just launch a inspector.
     * @param host   the debug server host, must not be "DEBUG_SERVER_HOST", a ip address or domain will be OK.
     *               for example "127.0.0.1".
     */
    private void initDebugEnvironment(boolean enable, String host) {
        if (!"DEBUG_SERVER_HOST".equals(host)) {
            WXEnvironment.sRemoteDebugMode = enable;
            WXEnvironment.sRemoteDebugProxyUrl = "ws://" + host + ":8088/debugProxy/native";
        }
    }

}
