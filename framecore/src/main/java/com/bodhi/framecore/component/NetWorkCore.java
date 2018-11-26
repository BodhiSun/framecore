package com.bodhi.framecore.component;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 14:53
 * desc :
 */
public class NetWorkCore {

    private static NetWorkCore netWorkCore;

    private NetWorkCore() {
    }

    public static NetWorkCore getInstance() {
        if (netWorkCore == null) {
            netWorkCore = new NetWorkCore();
        }

        return netWorkCore;
    }

    private String mac;
    private String ip;
    private String connect_type;//0 -- unknown  1 -- ethernet  2 -- wifi 3 -- mobile
    private String mobile_type;//unknown 2G 3G 4G
    private String wifi_name;

    private String mcc = "";//mobile country code
    private String mnc = "";// mobile network code

    private String operatorName = "";//运营商名称
    private String county = "";//获取ISO国家码，相当于提供SIM卡的国家码。
    private int cid = -1;//基站编号
    private int lac = -1;//区域位置码

    private Context appContext;

    public void init(Context context) {
        this.appContext = context;

        TelephonyManager tm = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);

        operatorName = tm.getSimOperatorName();

        county = tm.getSimCountryIso();

        mcc = "";
        mnc = "";

        String so = tm.getSimOperator();
        if (!TextUtils.isEmpty(so)) {
            mcc = so.substring(0, 3);
            mnc = so.substring(3);
        }

        CellLocation cellLocation = null;
        try {
            cellLocation = tm.getCellLocation();
            if (cellLocation != null) {
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    if (gsmCellLocation != null) {
                        cid = gsmCellLocation.getCid();
                        lac = gsmCellLocation.getLac();
                    }
                } else if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    if (cdmaCellLocation != null) {
                        cid = cdmaCellLocation.getBaseStationId();
                        lac = cdmaCellLocation.getNetworkId();
                    }
                }
            }

        } catch (SecurityException ex) {

        }

        mac = NetWork.getMac(appContext);

        ip="";
        connect_type="unknown";
        mobile_type="unknown";
        wifi_name = "";

        ConnectivityManager cm = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = cm.getActiveNetworkInfo();
        NetworkInfo mobNI = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNI = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo etherNI = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);

        if(ni == null || !ni.isConnected()){
            return;
        }

        if(wifiNI!=null && wifiNI.isConnected()){
            connect_type="wifi";
            WifiManager wifiManager = (WifiManager) appContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo =wifiManager.getConnectionInfo();
            wifi_name =wifiInfo.getSSID();
            ip = NetWork.getWifiIP(wifiInfo);
        }else if(etherNI!=null&& etherNI.isConnected()){
            connect_type = "ethernet";
        }else if(mobNI!=null && mobNI.isConnected()){
            switch (tm.getNetworkType()) {
                case TelephonyManager.NETWORK_TYPE_LTE:
                case TelephonyManager.NETWORK_TYPE_IWLAN:
                    connect_type = "mobile";
                    mobile_type = "4G";
                    ip = NetWork.getMobileIP();
                    break;
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    connect_type = "mobile";
                    mobile_type = "3G";
                    ip = NetWork.getMobileIP();
                    break;
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    connect_type = "mobile";
                    mobile_type = "2G";
                    ip = NetWork.getMobileIP();
                    break;
                default:
                    connect_type = "mobile";
                    mobile_type = "unknown";
                    ip = NetWork.getMobileIP();
                    break;
            }
        }
    }

    public boolean isWifi(){
        ConnectivityManager cm = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if(ni==null||!ni.isConnected()){
            return false;
        }

        NetworkInfo wifiNI = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(wifiNI==null||wifiNI.isConnected()){
            return true;
        }

        return false;
    }

    public static NetWorkCore getNetWorkCore() {
        return netWorkCore;
    }

    public String getMac() {
        return mac == null ? "" : mac;
    }

    public String getIp() {
        return ip == null ? "" : ip;
    }

    public String getConnect_type() {
        return connect_type == null ? "" : connect_type;
    }

    public String getMobile_type() {
        return mobile_type == null ? "" : mobile_type;
    }

    public String getWifi_name() {
        return wifi_name == null ? "" : wifi_name;
    }

    public String getMcc() {
        return mcc == null ? "" : mcc;
    }

    public String getMnc() {
        return mnc == null ? "" : mnc;
    }

    public String getOperatorName() {
        return operatorName == null ? "" : operatorName;
    }

    public String getCounty() {
        return county == null ? "" : county;
    }

    public int getCid() {
        return cid;
    }

    public int getLac() {
        return lac;
    }
}
