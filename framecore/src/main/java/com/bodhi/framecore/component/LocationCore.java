package com.bodhi.framecore.component;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 12:02
 * desc :
 */
public class LocationCore {

    private static LocationCore locationCore = null;

    private LocationCore() {
    }

    public static LocationCore getInstance() {
        if (locationCore == null) {
            locationCore = new LocationCore();
        }

        return locationCore;
    }

    private Context appContext;

    private double latitude;

    private double longitude;

    public void init(Context context) {
        this.appContext = context;

        refreshLocation();
    }

    private void refreshLocation() {
        Location location = getLastKnownLocation();
        if (location!=null) {
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            Log.e("test","位置信息获取成功");
        }else{
            Log.e("test","位置信息为空");
        }

    }

    private Location getLastKnownLocation() {
        LocationManager locationManager = (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location location =null;
            try {
                location=locationManager.getLastKnownLocation(provider);
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }

            if(location==null){
                continue;
            }

            if(bestLocation==null||location.getAccuracy()<bestLocation.getAccuracy()){
                bestLocation=location;
            }

        }

        return bestLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
