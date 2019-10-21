package com.cheng.fubaihui.bean;

import java.io.Serializable;

/**
 * Created by 任小龙 on 2019/8/1.
 */
public class ParametersInfo implements Serializable {
    private static final long serialVersionUID = 3047872782102167560L;
    public String __timestamp;
    public String deviceToken;
    public String token;

    public ParametersInfo(){}
    public ParametersInfo(String p__timestamp, String pDeviceToken) {
        __timestamp = p__timestamp;
        deviceToken = pDeviceToken;
    }
}
