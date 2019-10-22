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
import com.cheng.fubaihui.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends BaseFragment {
    static MyFragment fragment;
    @BindView(R.id.head_portrait)
    ImageView mHeadPortrait;
    @BindView(R.id.relax_head)
    RelativeLayout mRelaxHead;
    @BindView(R.id.youhuijuan)
    RelativeLayout mYouhuijuan;
    @BindView(R.id.setting)
    RelativeLayout mSetting;
    @BindView(R.id.myTuiJian)
    RelativeLayout mMyTuiJian;
    @BindView(R.id.tuiJianMa)
    RelativeLayout mTuiJianMa;
    @BindView(R.id.receiveAdress)
    RelativeLayout mReceiveAdress;
    @BindView(R.id.shangJiaEnter)
    RelativeLayout mShangJiaEnter;
    @BindView(R.id.shangJiaLogin)
    RelativeLayout mShangJiaLogin;
    @BindView(R.id.hotLine)
    RelativeLayout mHotLine;
    @BindView(R.id.linear)
    LinearLayout mLinear;
    @BindView(R.id.li)
    LinearLayout mLi;


    public static MyFragment newInstance() {
        if (fragment == null)
            fragment = new MyFragment();
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
        return new TestModel();
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

    @OnClick({R.id.head_portrait, R.id.name, R.id.relax_head, R.id.coupon, R.id.youhuijuan, R.id.iv_setting, R.id.setting, R.id.mine, R.id.myTuiJian, R.id.referral, R.id.tuiJianMa, R.id.site, R.id.receiveAdress, R.id.house, R.id.shangJiaEnter, R.id.house1, R.id.shangJiaLogin, R.id.phone, R.id.hotLine, R.id.linear, R.id.myFriend, R.id.myCollect, R.id.myWallet, R.id.myIndent, R.id.li})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.relax_head://头部个人资料
                break;
            case R.id.youhuijuan://优惠卷
                break;
            case R.id.setting://设置
                break;
            case R.id.myTuiJian://我的推荐
                break;
            case R.id.tuiJianMa://推荐码
                break;
            case R.id.receiveAdress://收货地址
                break;
            case R.id.shangJiaEnter://商家入驻
                break;
            case R.id.shangJiaLogin://商家登录
                break;
            case R.id.hotLine://平台热线
                break;
            case R.id.myFriend://我的好友
                break;
            case R.id.myCollect://我的收藏
                break;
            case R.id.myWallet://我的钱包
                break;
            case R.id.myIndent://我的订单
                break;
        }
    }
}


