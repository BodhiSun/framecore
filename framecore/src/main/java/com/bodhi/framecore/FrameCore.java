package com.bodhi.framecore;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bodhi.framecore.component.AppCore;
import com.bodhi.framecore.component.DeviceCore;
import com.bodhi.framecore.component.LocationCore;
import com.bodhi.framecore.component.NetWorkCore;
import com.bodhi.framecore.component.ScreenCore;
import com.bodhi.framecore.util.SharedprefHelper;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 14:03
 * desc :
 */
public class FrameCore {

    private static FrameCore frameCore;

    private FrameCore() {
    }

    public static FrameCore getInstance() {
        if (frameCore == null) {
            frameCore = new FrameCore();
        }

        return frameCore;
    }

    private Context appContext;

    private boolean isDebug = false;

    private int defaultStatusBarColor = -1;

    public void init(Context context) {
        this.appContext = context;

        AppCore.getInstance().init(appContext);

        ScreenCore.getInstance().init(appContext);

        LocationCore.getInstance().init(appContext);

        DeviceCore.getInstance().init(appContext);

        NetWorkCore.getInstance().init(appContext);

        SharedprefHelper.getInstance().init(appContext);
    }

    public void setDebugMode(boolean debugMode) {
        this.isDebug = debugMode;
    }

    public boolean isDebugMode() {
        return isDebug;
    }

    public void setDefaultStatusBarColor(int color) {
        this.defaultStatusBarColor = color;
    }

    public int getDefaultStatusBarColor() {
        return defaultStatusBarColor;
    }

    public void doLog(String tag,String msg){
        if (isDebug) {
            Log.e(tag,"-"+msg);
        }
    }

    /**
     * 剪贴板
     */
    public void copyToClipboard(String content){
        ClipboardManager cm = (ClipboardManager)appContext.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("default",content));
    }

    public void copyToClipboard(String label,String content){
        ClipboardManager cm = (ClipboardManager) appContext.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(label,content));
    }

    /**
     * 隐藏软键盘
     */
    public void hideIMK(View v){
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    /**
     * 显示软键盘
     */
    public void showIMK(View v){
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,InputMethodManager.RESULT_SHOWN);
    }


}
