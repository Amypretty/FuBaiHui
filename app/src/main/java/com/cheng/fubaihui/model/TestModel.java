package com.cheng.fubaihui.model;


import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.Config;
import com.cheng.fubaihui.frame.ICommonModel;
import com.cheng.fubaihui.frame.ICommonView;
import com.cheng.fubaihui.frame.NetManager;


public class TestModel implements ICommonModel {

    @Override
    public void getData(final int whichApi, final ICommonView presenterCallBack, Object... params) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi) {
            case ApiConfig.POST_NEWS_INFORMATION_TEST:
                manager.method(manager.getNetService().getnewsinformation(), whichApi, presenterCallBack);
                break;
            case ApiConfig.POST_PHONE_CODE:
                manager.method(manager.getNetService(Config.BASEURL).phone_code((String) params[0]), whichApi, presenterCallBack);
                break;

            case ApiConfig.REGISTER:
                manager.method(manager.getNetService(Config.BASEURL).register((String) params[0],
                        (String) params[1],
                        (Number) params[2],
                        (Number) params[3],
                        (Number) params[4]), whichApi, presenterCallBack);
                break;
            case ApiConfig.LOGINING:
                manager.method(manager.getNetService(Config.BASEURL).login((String) params[0],
                        (String) params[1]), whichApi, presenterCallBack);
                break;
            case ApiConfig.POST_FORGET_PWD:

                manager.method(manager.getNetService().rePwd((String) params[0],
                        (String) params[1], (String) params[2]), whichApi, presenterCallBack);
                break;

            case ApiConfig.POST_SHOPS_LIST:
                manager.method(manager.getNetService().getShopList((Integer) params[0]),
                        whichApi, presenterCallBack);
                break;
            case ApiConfig.POST_LOCATION:
                manager.method(manager.getNetService().getLocation(),
                        whichApi, presenterCallBack);
                break;
            case ApiConfig.POST_SHOP_DETAILS:
                manager.method(manager.getNetService().getShopDetails((String)params[0]),
                        whichApi, presenterCallBack);
                break;

        }
    }
}
