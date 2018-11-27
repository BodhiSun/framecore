package com.bodhi.frameworkcore.constant;

import android.graphics.Color;

import com.bodhi.http.component.ParamMap;
import com.bodhi.http.exception.DuplicateParamException;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 17:10
 * desc :
 */
public class StaticContants {

    //log的开关
    public static boolean DEBUG_MODE = true;

    //状态栏的背景色
    public static int STATUS_BAR_COLOR = Color.parseColor("#e08d8d");

    //公参
    public static ParamMap commonParamMap;

    //构建路由参数
    public static ParamMap createFinalPm(ParamMap source, String token) {

        ParamMap paramMap = null;
        try {
            paramMap = ParamMap.cloneFrom(commonParamMap);
            paramMap.param("time", (System.currentTimeMillis() / 1000) + "");
            paramMap.param("token", token);
            paramMap.append(source);

        } catch (DuplicateParamException e) {
            e.printStackTrace();
        }

        return paramMap;
    }

}
