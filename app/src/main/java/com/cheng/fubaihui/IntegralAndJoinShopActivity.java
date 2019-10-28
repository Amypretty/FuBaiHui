package com.cheng.fubaihui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.cheng.fubaihui.adapter.ShopTypeRclAdapter;
import com.cheng.fubaihui.adapter.ShopsListRclAdapter;
import com.cheng.fubaihui.bean.LocationBean;
import com.cheng.fubaihui.bean.ShopsBean;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.model.TestModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegralAndJoinShopActivity extends BaseMvpActivity<TestModel> implements View.OnClickListener {


    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tv_filter_sheng)
    TextView mTvFilterSheng;
    @BindView(R.id.tv_filter_shi)
    TextView mTvFilterShi;
    @BindView(R.id.tv_filter_qu)
    TextView mTvFilterQu;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.tv_filter)
    TextView mTvFilter;
    @BindView(R.id.rcl)
    RecyclerView mRcl;
    private List<ShopsBean.DataBean> mShopsList = new ArrayList<>();
    private ShopsListRclAdapter mAdapter;
    private OptionsPickerView mPvOptions;
    private List<LocationBean.DataBean.SsxBean> ssx = new ArrayList<>();
    private List<List<LocationBean.DataBean.SsxBean.ShiBean>> mShiList = new ArrayList<>();
    private List<LocationBean.DataBean.DpflBean> mShopTypeList = new ArrayList<>();
    private List<List<List<LocationBean.DataBean.SsxBean.ShiBean.XianBean>>> mXianList = new ArrayList<>();
    private ShopTypeRclAdapter mShopTypeRclAdapter;
    private PopupWindow mShowShopTypePop;

    public static void startIntegralAndJoinShopActivity(Context context, String type) {
        Intent intent = new Intent(context, IntegralAndJoinShopActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public TestModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_integral_and_jion_shope;
    }

    @Override
    public void setUp() {

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        mTitle.setText(type);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRcl.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopsListRclAdapter(mShopsList, this);
        mRcl.setAdapter(mAdapter);
        if ("银积分商城".equals(type))
            //获取银积分商城界面的商户列表
            mPresenter.getData(ApiConfig.POST_SHOPS_LIST, 1);
        else //获取加盟商家的商户列表
            mPresenter.getData(ApiConfig.POST_SHOPS_LIST, 0);
        mPresenter.getData(ApiConfig.POST_LOCATION);
        mLlFilter.setOnClickListener(this);
        mTvFilter.setOnClickListener(this);
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。*/

        for (int i = 0; i < ssx.size(); i++) {//遍历省份
            ArrayList<LocationBean.DataBean.SsxBean.ShiBean> cityList = new ArrayList<>();//该省的城市列表（第二级）
            List<List<LocationBean.DataBean.SsxBean.ShiBean.XianBean>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < ssx.get(i).getShi().size(); c++) {//遍历该省份的所有城市
                LocationBean.DataBean.SsxBean.ShiBean shiBean = ssx.get(i).getShi().get(c);
                cityList.add(shiBean);//添加城市
                ArrayList<LocationBean.DataBean.SsxBean.ShiBean.XianBean> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                if (ssx.get(i).getShi().get(c).getXian() != null) {
                    city_AreaList.addAll(ssx.get(i).getShi().get(c).getXian());
                }
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            mShiList.add(cityList);

            /**
             * 添加地区数据
             */
            mXianList.add(province_AreaList);
        }

    }

    private void createSSXPop() {// 弹出选择器
        mPvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String sheng = ssx.size() > 0 ?
                        ssx.get(options1).getPickerViewText() : "";

                String shi = mShiList.size() > 0
                        && mShiList.get(options1).size() > 0 ?
                        mShiList.get(options1).get(options2).getPickerViewText() : "";


                String qu = mShiList.size() > 0
                        && mXianList.get(options1).size() > 0
                        && mXianList.get(options1).get(options2).size() > 0 ?
                        mXianList.get(options1).get(options2).get(options3).getPickerViewText() : "";

                mTvFilterSheng.setText(sheng);
                mTvFilterQu.setText(qu);
                mTvFilterShi.setText(shi);
                String tx = sheng + shi + qu;
                Toast.makeText(IntegralAndJoinShopActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        mPvOptions.setPicker(ssx, mShiList, mXianList);//三级选择器
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

        switch (whichApi) {
            case ApiConfig.POST_SHOPS_LIST:
                ShopsBean shopsBean = (ShopsBean) successResult;
                List<ShopsBean.DataBean> data = shopsBean.getData();
                mShopsList.addAll(data);
                mAdapter.notifyDataSetChanged();
                break;
            case ApiConfig.POST_LOCATION:

                LocationBean locationBean = (LocationBean) successResult;
                ssx.addAll(locationBean.getData().getSsx());
                Log.i("tag", ssx.size() + "");
                initJsonData();
                createSSXPop();
                mShopTypeList.addAll(locationBean.getData().getDpfl());
                Log.i("mshopTypeList",mShopTypeList.size()+"");
                createShopTypePop();
                break;
        }
    }

    public void createShopTypePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.shop_type_layout, null);
        RecyclerView rcl = view.findViewById(R.id.rcl);
        rcl.setLayoutManager(new LinearLayoutManager(this));
        mShopTypeRclAdapter = new ShopTypeRclAdapter(mShopTypeList, this);
        rcl.setAdapter(mShopTypeRclAdapter);
        mShowShopTypePop = new PopupWindow(view, 350, ViewGroup.LayoutParams.WRAP_CONTENT);
        mShowShopTypePop.setOutsideTouchable(true);
        mShowShopTypePop.setAnimationStyle(R.style.PopAnimation);

    }

    private boolean isShowing = false;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_filter:
                if (mPvOptions != null) {
                    mPvOptions.show();
                } else {
                    Toast.makeText(this, "请稍等正在加载地区数据", Toast.LENGTH_SHORT).show();
                }

                if (mShowShopTypePop != null && mShowShopTypePop.isShowing()) {
                    mShowShopTypePop.dismiss();
                }
                break;
            case R.id.tv_filter:
                if (mPvOptions != null && mPvOptions.isShowing()) {
                    mPvOptions.dismiss();
                }

                if (mShowShopTypePop != null&&!isShowing) {
                    mShowShopTypePop.showAsDropDown(mTvFilter,0,20);
                    isShowing =true;
                    //mShowShopTypePop.showAtLocation(mTvFilter, Gravity.RIGHT, 0, -100);
                }else if (mShowShopTypePop!=null){
                    mShowShopTypePop.dismiss();
                    isShowing =false;
                    //Toast.makeText(this, "请稍等正在加载数据", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "请稍等正在加载数据", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this, "分类pop", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
