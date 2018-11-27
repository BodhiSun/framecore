package com.bodhi.framecore.application;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import com.bodhi.framecore.FrameCore;
import com.bodhi.http.HttpCore;
import com.bodhi.http.component.SSLParams;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 17:10
 * desc :
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        HttpCore.getInstance().init(this,appSSLParams());
        FrameCore.getInstance().init(this);
        FrameCore.getInstance().setDebugMode(isDebugMode());
        FrameCore.getInstance().setDefaultStatusBarColor(defaultStatusBarColor());
    }

    protected abstract SSLParams appSSLParams();

    protected abstract boolean isDebugMode();

    protected abstract int defaultStatusBarColor();
}
