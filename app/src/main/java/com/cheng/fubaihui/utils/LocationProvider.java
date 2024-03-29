package com.cheng.fubaihui.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

@SuppressLint("MissingPermission")
public class LocationProvider {
    private Activity mActivity;
    private LocationManager mLocationManager;
    private static final int LOCATION_REQUEST_CODE = 0;
    private static final long REFRESH_TIME = 1000;
    private static final float MIN_DISTANCE = 10;
    private Location mLocation;

    /**
     * 未申请到动态权限，有崩溃风险
     */
    public LocationProvider(Activity pActivity) {
        mActivity = pActivity;
        mLocationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(pActivity, "请打开手机GPS", Toast.LENGTH_SHORT).show();
            mActivity.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), LOCATION_REQUEST_CODE);
            return;
        }
        String lBestProvider = mLocationManager.getBestProvider(getCriteria(), true);
        mLocation = mLocationManager.getLastKnownLocation(lBestProvider);
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                mLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                switch (status) {
                    case android.location.LocationProvider.AVAILABLE:
                        Log.i("", "GPS is available");
                        break;
                    case android.location.LocationProvider.OUT_OF_SERVICE:
                        Log.i("", "GPS is out of service");
                        break;
                    case android.location.LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Log.i("", "GPS is unAvailable");
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                mLocation = mLocationManager.getLastKnownLocation(provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                mLocation = null;
            }
        };
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REFRESH_TIME, MIN_DISTANCE, locationListener);
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    /**
     * @return 定位对象
     */
    public Location getLocation() {
        return mLocation;
    }

    /**
     * 获取地址对象
     */
    private Address getAddress() {
        if (mLocation == null) {
            Toast.makeText(mActivity, "获取位置信息异常", Toast.LENGTH_SHORT).show();
            return null;
        }
        List<Address> addressList = null;
        try {
            Geocoder gc = new Geocoder(mActivity, Locale.getDefault());
            addressList = gc.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (addressList == null) return null;
        return addressList.get(0);
    }

    /**
     * 单一城市名称
     */
    public String getCity() {
        if (getAddress() == null) return null;
        return getAddress().getLocality();
    }

    /**
     * 单一市区
     */
    public String getArea() {
        if (getAddress() == null) return null;
        return getAddress().getSubLocality();
    }

    /**
     * 获取城市和区名称
     */
    public String getCityWithArea() {
        if (getAddress() == null) return null;
        return getAddress().getAddressLine(0);
    }

    /**
     * 获取编码
     */
    public String getCountryCode() {
        if (getAddress() == null) return null;
        return getAddress().getCountryCode();
    }

    /**
     * 获取国家
     */
    public String getCountryName() {
        if (getAddress() == null) return null;
        return getAddress().getCountryName();
    }

}
