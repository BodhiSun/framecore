package com.bodhi.framecore.component;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/22 19:07
 * desc :
 */
public class DeviceCore {

    private static DeviceCore deviceCore;

    private DeviceCore() {
    }

    public static DeviceCore getInstance() {
        if (deviceCore == null) {
            deviceCore = new DeviceCore();
        }

        return deviceCore;
    }

    private Context appContext;

    private String os;
    private int osVersion;
    private String brand;
    private String model;
    private String manufacturer;
    private String androidId;
    private String imei;
    private String imsi;
    private String ua;
    private String iccid;
    private String serialNumber;
    private int type;//0 -- 未知  1 -- 手机  2 -- 电脑  3 -- 平板

    public void init(Context context) {
        this.appContext = context;

        brand = Build.BRAND;
        model = Build.MODEL;
        manufacturer = Build.MANUFACTURER;
        os = "Android";
        osVersion = Build.VERSION.SDK_INT;

        refresh();

    }

    private void refresh() {
        imei = "";
        imsi = "";

        Context applicationContext = appContext.getApplicationContext();
        androidId = Settings.Secure.getString(applicationContext.getContentResolver(), Settings.Secure.ANDROID_ID);

        TelephonyManager tm = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            imei = tm.getDeviceId();
        } catch (SecurityException ex) {
            imei = "";
        }

        try {
            imsi = tm.getSubscriberId();
        } catch (SecurityException ex) {
            imsi = "";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                ua = WebSettings.getDefaultUserAgent(appContext);
            } catch (Exception e) {
                ua = System.getProperty("http.agent");
            }
        } else {
            ua = System.getProperty("http.agent");
        }

        String lua = ua.toLowerCase();
        if (lua.contains("android")) {
            if (imei.equals("unknown")) {
                type = 3;
            } else {
                type = 1;
            }
        } else {
            type = 2;
        }

        try {
            iccid = tm.getLine1Number();
        } catch (SecurityException ex) {
            iccid = "";
        }

        try {
            serialNumber = tm.getSimSerialNumber();
        } catch (SecurityException ex) {
            serialNumber = "";
        }

    }

    public Context getAppContext() {
        return appContext;
    }

    public String getOs() {
        return os == null ? "" : os;
    }

    public int getOsVersion() {
        return osVersion;
    }

    public String getBrand() {
        return brand == null ? "" : brand;
    }

    public String getModel() {
        return model == null ? "" : model;
    }

    public String getManufacturer() {
        return manufacturer == null ? "" : manufacturer;
    }

    public String getAndroidId() {
        return androidId == null ? "" : androidId;
    }

    public String getImei() {
        return imei == null ? "" : imei;
    }

    public String getImsi() {
        return imsi == null ? "" : imsi;
    }

    public String getUa() {
        return ua == null ? "" : ua;
    }

    public String getIccid() {
        return iccid == null ? "" : iccid;
    }

    public String getSerialNumber() {
        return serialNumber == null ? "" : serialNumber;
    }

    public int getType() {
        return type;
    }
}
