package com.cheng.fubaihui.frame;

/**
 * Created by 任小龙 on 2019/9/19.
 */
public interface ICommonPresenter<P> {
    void getData(int whichApi, P... params);
}
