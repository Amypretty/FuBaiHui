package com.cheng.fubaihui.fragment;


import android.view.View;

import com.cheng.fubaihui.R;
import com.cheng.fubaihui.frame.BaseFragment;
import com.cheng.fubaihui.frame.ICommonModel;

public class MyFragment extends BaseFragment {
    static MyFragment fragment;


    public static MyFragment newInstance() {
        if (fragment == null) fragment = new MyFragment();
        return fragment;
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected ICommonModel setModle() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }
}
