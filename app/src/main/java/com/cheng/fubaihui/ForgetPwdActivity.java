package com.cheng.fubaihui;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.fubaihui.bean.RePwdBean;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.model.TestModel;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class ForgetPwdActivity extends BaseMvpActivity<TestModel> implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.phoneNum)
    EditText mPhoneNum;
    @BindView(R.id.verify)
    EditText mVerify;
    @BindView(R.id.get_verify)
    TextView mGetVerify;
    @BindView(R.id.new_pwd)
    EditText mNewPwd;
    @BindView(R.id.is_show_pwd)
    ImageView mIsShowPwd;
    @BindView(R.id.re_pwd)
    Button mRepwd;
    boolean isVer = false;
    private boolean mIsPhone;
    private boolean mIsNewPwd;
    private Timer mTimer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mGetVerify.setText("重新获取");
                time = 60;
                mTimer.cancel();
            } else {
                time--;
                mGetVerify.setText("重新发送:" + time);
            }
        }
    };

    @Override
    public TestModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void setUp() {
        initView();
    }

    private void initView() {
        mToolbar.setTitle("");
        mToolbar.setSubtitle("");
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(mToolbar);
        mGetVerify.setEnabled(false);
        mGetVerify.setOnClickListener(this);
        mRepwd.setEnabled(false);
        mRepwd.setOnClickListener(this);
        mIsShowPwd.setOnClickListener(this);
        mPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = s.toString();
                mIsPhone = isPhone(phone);
                if (mIsPhone) {
                    mGetVerify.setTextColor(Color.parseColor("#009847"));
                    mGetVerify.setEnabled(true);
                    if (isVer && mIsNewPwd) {
                        mRepwd.setSelected(true);
                        mRepwd.setEnabled(true);
                    }
                } else {
                    mGetVerify.setTextColor(Color.parseColor("#a19f9f"));
                    mRepwd.setSelected(false);
                    mRepwd.setEnabled(false);
                }
            }
        });

        mVerify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String verify = s.toString();
                if (verify.length() == 4) {
                    isVer = true;
                    if (mIsPhone && mIsNewPwd) {
                        mRepwd.setSelected(true);
                        mRepwd.setEnabled(true);
                    }
                } else {
                    isVer = false;
                    mRepwd.setSelected(false);
                    mRepwd.setEnabled(false);
                }
            }
        });
        mNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newPwd = s.toString();
                if (newPwd.matches("^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,8}$") && mIsPhone && isVer) {
                    mRepwd.setSelected(true);
                    mRepwd.setEnabled(true);
                    mIsNewPwd = true;
                } else {
                    mRepwd.setSelected(false);
                    mRepwd.setEnabled(false);
                    mIsNewPwd = false;
                }
            }
        });
    }

    private int time = 60;

    @Override
    public void onSuccess(int whichApi, Object successResult) {

        switch (whichApi) {
            case ApiConfig.POST_PHONE_CODE:
                //重新发送:60
                mGetVerify.setText("重新发送:" + time);
                mTimer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(time);
                    }
                };
                mTimer.schedule(timerTask, 1000, 1000);
                break;
            case ApiConfig.POST_FORGET_PWD:
                RePwdBean rePwdBean = (RePwdBean) successResult;
                if (rePwdBean.getCode() == 200) {
                    if (mTimer != null)
                        mTimer.cancel();
                    finish();
                } else {
                    Toast.makeText(this, rePwdBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public boolean isPhone(String phoneNUm) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phoneNUm != null && phoneNUm.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    private int isFirstCheckShowPwd = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.is_show_pwd:
                if (isFirstCheckShowPwd == 1) {
                    mNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isFirstCheckShowPwd = 2;
                } else {
                    mNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isFirstCheckShowPwd = 1;
                }
                break;
            case R.id.get_verify:
                mPresenter.getData(ApiConfig.POST_PHONE_CODE, mPhoneNum.getText().toString());
                break;
            case R.id.re_pwd:
                if (mPresenter.getModel() == null)
                    mPresenter.bind(this, new TestModel());

                else {

                }
                mPresenter.getData(ApiConfig.POST_FORGET_PWD, mPhoneNum.getText().toString(),
                        mNewPwd.getText().toString(),
                        mVerify.getText().toString());
                break;
        }
    }
}
