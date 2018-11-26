package com.bodhi.framecore.util;

import android.content.Context;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 16:25
 * desc :
 */
public class SharedprefHelper {

    private static SharedprefHelper sharedprefHelper;

    private SharedprefHelper(){};

    public static SharedprefHelper getInstance(){
        if (sharedprefHelper==null) {
            sharedprefHelper = new SharedprefHelper();
        }

        return sharedprefHelper;
    }

    private Context context;

    public void init(Context context){
        this.context=context;
    }

    public String getStoredString(String type, String key) {
        return context.getSharedPreferences(type, Context.MODE_PRIVATE).getString(key, "");
    }

    public void storeString(String type, String key, String value) {
        context.getSharedPreferences(type, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public int getStoredInt(String type, String key, int defaultValue) {
        return context.getSharedPreferences(type, Context.MODE_PRIVATE).getInt(key, defaultValue);
    }

    public void storeInt(String type, String key, int value) {
        context.getSharedPreferences(type, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
    }

    public long getStoredLong(String type, String key, long defaultValue) {
        return context.getSharedPreferences(type, Context.MODE_PRIVATE).getLong(key, defaultValue);
    }

    public void storeLong(String type, String key, long value) {
        context.getSharedPreferences(type, Context.MODE_PRIVATE).edit().putLong(key, value).commit();
    }

}
