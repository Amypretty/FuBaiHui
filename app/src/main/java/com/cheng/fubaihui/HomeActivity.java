package com.cheng.fubaihui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.cheng.fubaihui.MyView.MyBottomView;
import com.cheng.fubaihui.fragment.FirstPageFragment;
import com.cheng.fubaihui.fragment.MyFragment;
import com.cheng.fubaihui.fragment.NewsFragment;
import com.cheng.fubaihui.fragment.ShoppingFragment;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.model.TestModel;

public class HomeActivity extends BaseMvpActivity<TestModel> implements MyBottomView.OnBottomClick {

    private MyBottomView mBottomView;
    private final int HOME_PAGE = 1, NEWS_PAGE = 2, SHOPPING_PAGE = 3, MY_PAGE = 4;
    private FragmentManager mManager;
    private FirstPageFragment mFirstPageFragment;
    private NewsFragment mNewsFragment;
    private ShoppingFragment mShoppingFragment;
    private MyFragment mMyFragment;
    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        Log.i(TAG, "initView: "+"VVVVVVVV");
        mBottomView = findViewById(R.id.bottom_view);
        mBottomView.setBottomBg(Color.WHITE);
        mBottomView.setBottomTextSize(this, 10f);
        mBottomView.setOnBottomClickListener(this);
        mManager = getSupportFragmentManager();//��ȡ��Ƭ������
        showFragment(HOME_PAGE);//��չʾ��һ��ҳ��
    }

    @Override
    public TestModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setUp() {
        mPresenter.getData(1, "1");
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        Log.i("tag", successResult.toString() + "*******");
    }


    //���tab,�л�4����Ƭ
    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (index) {
            case HOME_PAGE:
                if (mFirstPageFragment != null) {
                    Log.i(TAG, "showFragment: "+"x1111");
                    fragmentTransaction.show(mFirstPageFragment);
                } else {
                    Log.i(TAG, "showFragment: "+"11111111");
                    mFirstPageFragment = FirstPageFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mFirstPageFragment);
                }
                break;
            case NEWS_PAGE:
                if (mNewsFragment != null) fragmentTransaction.show(mNewsFragment);
                else {
                    Log.i(TAG, "showFragment: "+"222222222");
                    mNewsFragment = NewsFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mNewsFragment);
                }
                break;
            case SHOPPING_PAGE:
                if (mShoppingFragment != null) fragmentTransaction.show(mShoppingFragment);
                else {
                    Log.i(TAG, "showFragment: "+"33333333");
                    mShoppingFragment = ShoppingFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mShoppingFragment);
                }
                break;
            case MY_PAGE:
                if (mMyFragment != null) fragmentTransaction.show(mMyFragment);
                else {
                    Log.i(TAG, "showFragment: "+"4444444");
                    mMyFragment = MyFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mMyFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction pFragmentTransaction) {
        if (mFirstPageFragment != null) pFragmentTransaction.hide(mFirstPageFragment);
        if (mNewsFragment != null) pFragmentTransaction.hide(mNewsFragment);
        if (mShoppingFragment != null) pFragmentTransaction.hide(mShoppingFragment);
        if (mMyFragment != null) pFragmentTransaction.hide(mMyFragment);
    }

    @Override
    public void onFirstClick() {
        Log.i(TAG, "onFirstClick: "+"VVVVVV");
        showFragment(HOME_PAGE);
    }

    @Override
    public void onSecondClick() {
        Log.i(TAG, "onSecondClick: "+"VVVVVV");
        showFragment(NEWS_PAGE);
    }

    @Override
    public void onThirdClick() {
        Log.i(TAG, "onThirdClick: "+"VVVVVV");
        showFragment(SHOPPING_PAGE);
    }

    @Override
    public void onFourthClick() {
        Log.i(TAG, "onFourthClick: "+"VVVVVV");
        showFragment(MY_PAGE);
    }

    @Override
    public void onFifthClick() {

    }
}
