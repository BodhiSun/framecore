package com.bodhi.framecore.component;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bodhi.framecore.Defines;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/22 17:59
 * desc :
 */
public class AppCore implements Defines {

    private static AppCore appCore = null;

    private AppCore() {
    }

    ;

    public static AppCore getInstance() {
        if (appCore == null) {
            appCore = new AppCore();
        }

        return appCore;
    }

    private Context appContext;

    private PackageManager packageManager;

    private String pkgName;

    private int verCode;

    private String verName;

    private String channel;

    public void init(Context context) {
        this.appContext = context;

        packageManager = appContext.getPackageManager();

        pkgName = appContext.getPackageName();

        try {
            PackageInfo pi = packageManager.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
            verCode = pi.versionCode;
            verName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ApplicationInfo ai = packageManager.getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
            Bundle metaData = ai.metaData;
            if (metaData != null) {
                channel = metaData.getString(KEY_CHANNEL);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PackageManager getPackageManager() {
        return packageManager;
    }

    public String getPkgName() {
        return pkgName == null ? "" : pkgName;
    }

    public int getVerCode() {
        return verCode;
    }

    public String getVerName() {
        return verName == null ? "" : verName;
    }

    public String getChannel() {
        return channel == null ? "" : channel;
    }

    public String getMetaValue(String key) {
        try {
            ApplicationInfo ai = packageManager.getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
            return ai.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public PackageInfo getPackageInfo(String pkgName) {
        try {
            packageManager.getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isAppInstalled(String pkgName) {
        PackageInfo packageInfo = getPackageInfo(pkgName);
        return packageInfo != null;
    }

    public boolean isSystemApp(String pkgName) {
        PackageInfo packageInfo = getPackageInfo(pkgName);
        if (packageInfo == null) {
            return false;
        }

        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}
