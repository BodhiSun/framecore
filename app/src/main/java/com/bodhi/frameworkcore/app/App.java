package com.bodhi.frameworkcore.app;

import android.content.Context;

import com.bodhi.framecore.FrameCore;
import com.bodhi.framecore.application.BaseApplication;
import com.bodhi.framecore.component.AppCore;
import com.bodhi.framecore.component.DeviceCore;
import com.bodhi.framecore.component.NetWork;
import com.bodhi.framecore.component.NetWorkCore;
import com.bodhi.frameworkcore.constant.StaticContants;
import com.bodhi.http.component.ParamMap;
import com.bodhi.http.exception.DuplicateParamException;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 17:10
 * desc :
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initCommonParamMap();
    }

    private void initCommonParamMap() {
        StaticContants.commonParamMap = ParamMap.create();

        try {
            StaticContants.commonParamMap
                    .param("version", AppCore.getInstance().getVerName())
                    .param("device", DeviceCore.getInstance().getModel())
                    .param("device_code", DeviceCore.getInstance().getImei())
                    .param("ip", NetWorkCore.getInstance().getIp())
                    .param("network", NetWorkCore.getInstance().getConnect_type())
                    .param("os", DeviceCore.getInstance().getOs())
                    .param("osversion", DeviceCore.getInstance().getOsVersion() + "")
                    .param("channel", AppCore.getInstance().getChannel())
                    .param("version", AppCore.getInstance().getVerCode() + "");
        } catch (DuplicateParamException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Context appSSLParams() {
        return null;
    }

    @Override
    protected boolean isDebugMode() {
        return StaticContants.DEBUG_MODE;
    }

    @Override
    protected int defaultStatusBarColor() {
        return StaticContants.STATUS_BAR_COLOR;
    }
}
