package com.cheng.fubaihui.model;


import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.Config;
import com.cheng.fubaihui.frame.ICommonModel;
import com.cheng.fubaihui.frame.ICommonView;
import com.cheng.fubaihui.frame.NetManager;

/**
 * Created by 任小龙 on 2019/9/19.
 */
public class TestModel implements ICommonModel {

    @Override
    public void getData(final int whichApi, final ICommonView presenterCallBack, Object[] params) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi){
            case 1:
                manager.method(manager.getNetService().getPersonRankInfo((String) params[0]),whichApi,presenterCallBack);
                break;
        }
    }
}
