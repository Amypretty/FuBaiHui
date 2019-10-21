package com.cheng.fubaihui.fragment;


import android.view.View;

import com.cheng.fubaihui.R;
import com.cheng.fubaihui.frame.BaseFragment;
import com.cheng.fubaihui.frame.ICommonModel;

public class NewsFragment extends BaseFragment {
    static NewsFragment fragment;


    public static NewsFragment newInstance() {
        if (fragment == null) fragment = new NewsFragment();
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
        return R.layout.fragment_news;
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }
}
