package com.cheng.fubaihui.frame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 2019/9/21
 * 1901A张晨星
 */

public abstract class BaseFragment extends Fragment implements ICommonView{
    public CommonPresenter mMainPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(getLayoutId(), null);
        mMainPresenter = new CommonPresenter();
        if(mMainPresenter!=null) {
            mMainPresenter.bind(this, setModle());
        }
        initView(view);
        initdata();
        return view;
    }

    protected abstract void initdata();

    protected abstract void initView(View view);

    protected abstract ICommonModel setModle();

    protected abstract int getLayoutId();


}
