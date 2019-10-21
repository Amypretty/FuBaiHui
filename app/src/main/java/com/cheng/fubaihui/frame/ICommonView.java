package com.cheng.fubaihui.frame;

/**
 * Created by 任小龙 on 2019/9/19.
 */
public interface ICommonView<S> {
    void onSuccess(int whichApi, S successResult);
    void onFailed(int whichApi, Throwable failedResult);
}
