package com.cheng.fubaihui.frame;

import android.app.Application;
import android.content.Context;

import com.cheng.fubaihui.safe.DeviceUuidFactory;

import java.util.UUID;




/**
 * Created by 任小龙 on 2019/9/20.
 */
public class Application1901 extends Application {
    private static Application1901 sApplication;
    public String mToken;
    public UUID mUuid;
    public String mUid;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        mUuid = DeviceUuidFactory.getInstance(getApplicationContext()).getDeviceUuid();
    }

    public static Application1901 getApplication() {
        return sApplication;
    }

    public static Context getAppContext() {
        return sApplication.getApplicationContext();
    }
}
