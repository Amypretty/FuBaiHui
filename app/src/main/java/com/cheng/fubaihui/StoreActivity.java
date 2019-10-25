package com.cheng.fubaihui;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cheng.fubaihui.adapter.MallnameAdapter;
import com.cheng.fubaihui.adapter.ShopAdapter;
import com.cheng.fubaihui.bean.BannerInfo;
import com.cheng.fubaihui.bean.MallnameInfo;
import com.cheng.fubaihui.bean.ShopInfo;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.model.TestModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StoreActivity extends BaseMvpActivity<TestModel> implements View.OnClickListener {
    @BindView(R.id.storeRec)
    RecyclerView mStoreRec;
    @BindView(R.id.mybanner)
    Banner mMybanner;
    @BindView(R.id.shopRec)
    RecyclerView mShopRec;
    @BindView(R.id.tv_exct)
    RelativeLayout mTvexct;
    private List<MallnameInfo.DataBean> mList = new ArrayList<>();
    List<String> banner = new ArrayList<>();
    private List<ShopInfo.DataBean> mShop = new ArrayList<>();
    private MallnameAdapter mMallnameAdapter;
    private ShopAdapter mShopAdapter;
//    private String mId;


    @Override
    public int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    public void setUp() {
        initView();
        mPresenter.getData(ApiConfig.GET_BANNER_LIST);
        mPresenter.getData(ApiConfig.GET_MALL_NAME_LIST);

    }

    @Override
    public TestModel setModel() {
        return new TestModel();
    }

    private void initView() {
        mTvexct.setOnClickListener(this);
        mStoreRec.setLayoutManager(new LinearLayoutManager(this));
        mMallnameAdapter = new MallnameAdapter(this, mList);
        mStoreRec.setAdapter(mMallnameAdapter);
        mStoreRec.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        mMallnameAdapter.setOnClickItemClick(new MallnameAdapter.OnClickItemClick() {
            @Override
            public void OnItemClick(int position) {
                String mId = mList.get(position).getId();
                Log.e("11", "OnItemClick: " + mId);
                mPresenter.getData(ApiConfig.GET_SHOP_LIST, mId);
            }
        });
        mShopRec.setLayoutManager(new GridLayoutManager(this, 2));
        mShopAdapter = new ShopAdapter(this, mShop);
        mShopRec.setAdapter(mShopAdapter);


    }


    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.GET_MALL_NAME_LIST://侧面tab

                MallnameInfo mallnameInfo = (MallnameInfo) successResult;
                List<MallnameInfo.DataBean> data = mallnameInfo.getData();

                if (mallnameInfo.getData() != null && mallnameInfo.getData().size() > 0)
                    mList.addAll(data);

                mMallnameAdapter.notifyDataSetChanged();
                break;

            case ApiConfig.GET_BANNER_LIST://banner
                BannerInfo bannerInfo = (BannerInfo) successResult;
                List<BannerInfo.DataBean> infoData = bannerInfo.getData();
                Log.e("1111", "onSuccess: " + infoData.size());
                for (int i = 0; i < infoData.size(); i++) {
                    String picture = infoData.get(i).getImg();
                    banner.add("http://newwasj.zhangtongdongli.com/" + picture);
                }
                Log.e("1111", "onSuccess: " + banner.size());

                mMybanner.setImages(banner).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        String str = (String) path;
                        Glide.with(context).load(path).into(imageView);
                    }
                }).start();
//                mMybanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                break;
            case ApiConfig.GET_SHOP_LIST:
                mShop.clear();
                ShopInfo shopInfo = (ShopInfo) successResult;
                Log.e("1111", "onSuccess: " + shopInfo);
                List<ShopInfo.DataBean> dataBeans = shopInfo.getData();
                if (shopInfo.getData() != null && shopInfo.getData().size() > 0) {
                    mShop.addAll(dataBeans);
                }
                mShopAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exct:
                onBackPressed();
                break;
        }
    }
}
