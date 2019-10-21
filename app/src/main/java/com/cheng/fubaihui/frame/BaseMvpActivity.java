package com.cheng.fubaihui.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/9/20.
 */
public abstract class BaseMvpActivity<M extends ICommonModel> extends BaseActivity implements ICommonView{
    public CommonPresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = new CommonPresenter();
        mPresenter.bind(this, setModel());
        setUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBind();
    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {
        showLog("错误接口是："+whichApi+":"+failedResult != null ? failedResult.getMessage():"错误原因未知");
    }

    public abstract M setModel();

    public abstract int getLayoutId();

    public abstract void setUp();
}
