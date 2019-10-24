package com.cheng.fubaihui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheng.fubaihui.R;
import com.cheng.fubaihui.frame.BaseFragment;
import com.cheng.fubaihui.frame.ICommonModel;
import com.youth.banner.Banner;

public class FirstPageFragment extends BaseFragment {
    static FirstPageFragment fragment;
    private Banner mBanner;
    private ImageView mImageFirst;
    /**
     * 福百惠商城
     */
    private TextView mTextFirst;
    private RelativeLayout mRlFirst;
    private ImageView mImageSecond;
    /**
     * 银积分商城
     */
    private TextView mTextSecond;
    private RelativeLayout mRlSecond;
    private ImageView mImageThird;
    /**
     * 搜索会员
     */
    private TextView mTextThird;
    private RelativeLayout mRlThird;
    private ImageView mImageFourth;
    /**
     * 加盟商家
     */
    private TextView mTextFourth;
    private RelativeLayout mRlFourth;
    private LinearLayout mTab;
    private LinearLayout mJingxuan;
    private LinearLayout mGonggao;

    public static FirstPageFragment newInstance() {
        if (fragment == null) fragment = new FirstPageFragment();
        return fragment;
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected void initView(View view) {

        mBanner = (Banner) view.findViewById(R.id.banner);
        mImageFirst = (ImageView) view.findViewById(R.id.image_first);
        mTextFirst = (TextView) view.findViewById(R.id.text_first);
        mRlFirst = (RelativeLayout) view.findViewById(R.id.rl_first);
        mImageSecond = (ImageView) view.findViewById(R.id.image_second);
        mTextSecond = (TextView) view.findViewById(R.id.text_second);
        mRlSecond = (RelativeLayout) view.findViewById(R.id.rl_second);
        mImageThird = (ImageView) view.findViewById(R.id.image_third);
        mTextThird = (TextView) view.findViewById(R.id.text_third);
        mRlThird = (RelativeLayout) view.findViewById(R.id.rl_third);
        mImageFourth = (ImageView) view.findViewById(R.id.image_fourth);
        mTextFourth = (TextView) view.findViewById(R.id.text_fourth);
        mRlFourth = (RelativeLayout) view.findViewById(R.id.rl_fourth);
        mTab = (LinearLayout) view.findViewById(R.id.tab);
        mJingxuan = (LinearLayout) view.findViewById(R.id.jingxuan);
        mGonggao = (LinearLayout) view.findViewById(R.id.gonggao);
    }

    @Override
    protected ICommonModel setModle() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_page;
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }
    
}
