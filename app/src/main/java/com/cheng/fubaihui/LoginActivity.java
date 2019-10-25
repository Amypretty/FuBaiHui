package com.cheng.fubaihui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.fubaihui.bean.LoginBean;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.Application1901;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.frame.ICommonModel;
import com.cheng.fubaihui.model.TestModel;
import com.yiyatech.utils.SharedPrefrenceUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ASUS on 2019/10/22.
 */

public class LoginActivity extends BaseMvpActivity {
    @BindView(R.id.Registertv)
    TextView mRegistertv;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.pwd)
    EditText mPwd;
    @BindView(R.id.login_bt)
    Button mLoginBt;
    @BindView(R.id.forget)
    TextView mForget;

    boolean isFirstOpen = false;

   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        *//*isFirstOpen = SharedPrefrenceUtils.getBoolean(this, "isfirtopen");
        if (isFirstOpen) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }else {
            isFirstOpen = true;
            SharedPrefrenceUtils.saveBoolean(this, "isfirtopen", isFirstOpen);
        }*//*
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public ICommonModel setModel() {
        return new TestModel();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setUp() {

    }


    @OnClick({R.id.Registertv, R.id.login_bt, R.id.forget})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.Registertv:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_bt:
                String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
                String regexpwd = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,8}$";
                String phone = mUsername.getText().toString();
                String pwd = mPwd.getText().toString();
                if (phone.length() == 11 && !pwd.isEmpty() && phone.matches(regex) && pwd.matches(regexpwd)) {
                    Toast.makeText(this, "登陆", Toast.LENGTH_SHORT).show();
                    mPresenter.getData(ApiConfig.LOGINING, phone, pwd);
                } else {
                    Toast.makeText(this, "请输入正确的账号或密码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                //忘记密码
                break;
        }
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.LOGINING:
                LoginBean loginBean = (LoginBean) successResult;
                if (loginBean.getCode() == 200) {
       Toast.makeText(this, "" + loginBean.getMsg(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this, HomeActivity.class));
                    SharedPrefrenceUtils.saveBoolean(this,"isLogin", true);
                    Application1901.getApplication().mUid = loginBean.getData().getUid()+"";
                    finish();
                } else {
                    Toast.makeText(this, "" + loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

}

