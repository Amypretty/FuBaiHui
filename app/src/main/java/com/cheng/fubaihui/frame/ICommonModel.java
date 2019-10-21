package com.cheng.fubaihui.frame;

/**
 * Created by 任小龙 on 2019/9/19.
 */
public interface ICommonModel<P> {
    void getData(int whichApi, ICommonView presenterCallBack, P... params);
}
