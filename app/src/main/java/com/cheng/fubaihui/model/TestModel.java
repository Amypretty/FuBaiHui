package com.cheng.fubaihui.model;


import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.ICommonModel;
import com.cheng.fubaihui.frame.ICommonView;
import com.cheng.fubaihui.frame.NetManager;


public class TestModel implements ICommonModel {

    @Override
    public void getData(final int whichApi, final ICommonView presenterCallBack, Object... params) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi){
            case ApiConfig.POST_NEWS_INFORMATION_TEST:
                manager.method(manager.getNetService().getnewsinformation(),whichApi,presenterCallBack);
                break;

        }
    }
}
