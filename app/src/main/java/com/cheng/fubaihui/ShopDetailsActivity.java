package com.cheng.fubaihui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cheng.fubaihui.bean.ShopDetailsBean;
import com.cheng.fubaihui.bean.ShopsBean;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.frame.Config;
import com.cheng.fubaihui.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailsActivity extends BaseMvpActivity<TestModel> {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.company_name)
    TextView mCompanyName;
    @BindView(R.id.company_context)
    ImageView mCompanyContext;
    @BindView(R.id.iv_location)
    ImageView mIvLocation;
    @BindView(R.id.location)
    TextView mLocation;
    @BindView(R.id.juli)
    TextView mJuli;
    @BindView(R.id.call_phone)
    ImageView mCallPhone;
    private String mPhone;
    private String mContent;
    private PopupWindow mPopupWindow;

    public static void startShopDetailsActivity(Context context, ShopsBean.DataBean dataBean) {
        Intent intent = new Intent(context, ShopDetailsActivity.class);
        intent.putExtra("data", dataBean);
        context.startActivity(intent);
    }

    @Override
    public TestModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_details;
    }

    @Override
    public void setUp() {
        Intent intent = getIntent();
        ShopsBean.DataBean data = intent.getParcelableExtra("data");
        String shopId = data.getId();
        mTitle.setText(data.getName());
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCompanyName.setText(data.getName());
        mCompanyContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContent!=null&&mPopupWindow!=null){
                    mPopupWindow.showAtLocation(mCompanyContext, Gravity.CENTER,0,0);
                }else if (mContent!=null){
                    createPop();
                    mPopupWindow.showAtLocation(mCompanyContext, Gravity.CENTER,0,0);
                }
                Toast.makeText(ShopDetailsActivity.this, "展示公司信息", Toast.LENGTH_SHORT).show();
            }
        });
        mCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ShopDetailsActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ShopDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                } else {
                    if (mPhone != null)
                        callPhone(mPhone);
                }
                //Toast.makeText(ShopDetailsActivity.this, "打电话", Toast.LENGTH_SHORT).show();
            }
        });
        mPresenter.getData(ApiConfig.POST_SHOP_DETAILS, shopId);
    }

    public void createPop(){
        View view = LayoutInflater.from(this).inflate(R.layout.show_company_item, null);
        TextView tv_show = view.findViewById(R.id.tv_show);
        if (mContent!=null){
            Log.i("mContent",mContent);
            tv_show.setText(Html.fromHtml(mContent));
        }
        ImageView dismiss = view.findViewById(R.id.dismiss);
        mPopupWindow = new PopupWindow(view, 800, 1200);
        mPopupWindow.setOutsideTouchable(true);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100: //拨打电话
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    Toast.makeText(this, "请允许拨号权限后再试", Toast.LENGTH_SHORT).show();
                } else {//成功
                    if (mPhone != null)
                        callPhone(mPhone);
                }
                break;
        }

    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {

        switch (whichApi) {
            case ApiConfig.POST_SHOP_DETAILS:
                RoundedCorners corners = new RoundedCorners(10);
                RequestOptions placeholder = new RequestOptions().placeholder(R.drawable.fbh);
                RequestOptions transform = placeholder.transform(corners);
                ShopDetailsBean shopDetailsBean = (ShopDetailsBean) successResult;
                Glide.with(this)
                        .load(Config.BASEURL + shopDetailsBean.getData().getLogo())
                        .apply(transform)
                        .into(mLogo);
                mLocation.setText(shopDetailsBean.getData().getDistrict() + shopDetailsBean.getData().getAddress_detail());
                Log.i("tag", shopDetailsBean.getData().getJuli() + "");
                mJuli.setText("(距离" + shopDetailsBean.getData().getJuli() + "km)");
                mPhone = shopDetailsBean.getData().getPhone();
                mContent = shopDetailsBean.getData().getContent();
                Log.i("mContent",mContent+"++++++++++++++++++");
                break;
        }
    }

    @SuppressLint("MissingPermission")
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri parse = Uri.parse("tel:" + phoneNum);
        intent.setData(parse);
        startActivity(intent);
    }
}
