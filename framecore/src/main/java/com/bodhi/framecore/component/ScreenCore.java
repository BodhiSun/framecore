package com.bodhi.framecore.component;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 15:39
 * desc :
 */
public class ScreenCore {

    private static ScreenCore screenCore;

    private ScreenCore() {
    }

    public static ScreenCore getInstance() {
        if (screenCore == null) {
            screenCore = new ScreenCore();
        }

        return screenCore;
    }

    private Context appContext;

    private String orientation = "u";// u -- 未知  p - 竖屏   l - 横屏

    private float density;

    private int dpi;

    private float scaledDensity;

    private int screenWidth;

    private int screenHeight;

    public void init(Context context) {
        this.appContext = context;

        Resources resources = appContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        density = dm.density;
        dpi = dm.densityDpi;
        scaledDensity = dm.scaledDensity;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int dp2px(float dp) {
        return (int) (dp * density + 0.5f);
    }

    public int sp2px(float sp) {
        return (int) (sp * scaledDensity + 0.5f);
    }

    public int px2dp(float pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    public void getOrientation(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            orientation = "p";
        } else {
            orientation = "l";
        }
    }


}
