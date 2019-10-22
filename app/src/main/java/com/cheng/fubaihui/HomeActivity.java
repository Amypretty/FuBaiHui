package com.cheng.fubaihui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.fubaihui.MyView.MyBottomView;
import com.cheng.fubaihui.fragment.FirstPageFragment;
import com.cheng.fubaihui.fragment.MyFragment;
import com.cheng.fubaihui.fragment.NewsFragment;
import com.cheng.fubaihui.fragment.ShoppingFragment;
import com.cheng.fubaihui.frame.Application1901;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.model.TestModel;
import com.yiyatech.utils.SharedPrefrenceUtils;

import butterknife.BindView;

public class HomeActivity extends BaseMvpActivity<TestModel> implements MyBottomView.OnBottomClick {

    /*private MyBottomView mBottomView;*/
    private final int HOME_PAGE = 0, NEWS_PAGE = 1, SHOPPING_PAGE = 2, MY_PAGE = 3;
    private FragmentManager mManager;
    private FirstPageFragment mFirstPageFragment;
    private NewsFragment mNewsFragment;
    private ShoppingFragment mShoppingFragment;
    private MyFragment mMyFragment;
    private static final String TAG = "HomeActivity";
    @BindView(R.id.tab)
    TabLayout mTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        Log.i(TAG, "initView: "+"VVVVVVVV");
        /*mBottomView = findViewById(R.id.bottom_view);
        mBottomView.setBottomBg(Color.WHITE);
        mBottomView.setBottomTextSize(this, 10f);
        mBottomView.setOnBottomClickListener(this);*/
        mTab.addTab(mTab.newTab().setCustomView(getView(R.string.home,R.drawable.tab1_selected)));
        mTab.addTab(mTab.newTab().setCustomView(getView(R.string.news,R.drawable.tab2_selected)));
        mTab.addTab(mTab.newTab().setCustomView(getView(R.string.shop,R.drawable.tab3_selected)));
        mTab.addTab(mTab.newTab().setCustomView(getView(R.string.my,R.drawable.tab4_selected)));

        mManager = getSupportFragmentManager();//��ȡ��Ƭ������
        showFragment(HOME_PAGE);//��չʾ��һ��ҳ��
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    showFragment(tab.getPosition());
                }else {


                    isLogin(tab.getPosition());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getView(int tabTitle, int tabImg) {
        //View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        View view = View.inflate(this, R.layout.tab_item, null);
        ImageView img = view.findViewById(R.id.tab_img);
        TextView title = view.findViewById(R.id.tab_title);
        img.setImageResource(tabImg);
        title.setText(tabTitle);
        return view;
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
        isLogin(NEWS_PAGE);
    }

    private void isLogin(int type) {
        boolean isLogin = SharedPrefrenceUtils.getBoolean(this, "isLogin");
        if (!isLogin){

            //未登录
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            mTab.setSelected(true);
            Log.i(TAG, "onSecondClick: " + "VVVVVV");
            showFragment(type);
        }
    }

    @Override
    public void onThirdClick() {
        isLogin(SHOPPING_PAGE);
        /*Log.i(TAG, "onThirdClick: "+"VVVVVV");
        showFragment(SHOPPING_PAGE);*/
    }

    @Override
    public void onFourthClick() {
        isLogin(MY_PAGE);
        /*Log.i(TAG, "onFourthClick: "+"VVVVVV");
        showFragment(MY_PAGE);*/
    }

    @Override
    public void onFifthClick() {

    }
}
