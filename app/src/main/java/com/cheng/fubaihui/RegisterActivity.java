package com.cheng.fubaihui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.fubaihui.bean.NoteCode_Bean;
import com.cheng.fubaihui.bean.RegisterBean;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.BaseMvpActivity;
import com.cheng.fubaihui.model.TestModel;
import com.yiyatech.utils.StaticToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS on 2019/10/21.
 */

public class RegisterActivity extends BaseMvpActivity<TestModel> {

    @BindView(R.id.regist_toolbar)
    Toolbar mRegistToolbar;
    @BindView(R.id.regist_return)
    ImageView mRegistReturn;
    @BindView(R.id.regist_ed_phone)
    EditText mRegistEdPhone;
    @BindView(R.id.regist_ed_code)
    EditText mRegistEdCode;
    @BindView(R.id.regist_bt_code)
    Button mRegistBtCode;
    @BindView(R.id.regist_ed_psd)
    EditText mRegistEdPsd;
    @BindView(R.id.regist_ed_writecode)
    EditText mRegistEdWritecode;
    @BindView(R.id.regist_ck)
    CheckBox mRegistCk;
    @BindView(R.id.regist_bt)
    Button mRegistBt;
    @BindView(R.id.regist_tv_login)
    TextView mRegistTvLogin;
    private static final String TAG = "RegisterActivity";
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public TestModel setModel() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void setUp() {

    }


    public void initView() {
        mRegistToolbar.setTitle("");
        setSupportActionBar(mRegistToolbar);

    }

    @OnClick({R.id.regist_return, R.id.regist_toolbar, R.id.regist_ed_phone, R.id.regist_ed_code, R.id.regist_bt_code, R.id.regist_ed_psd, R.id.regist_ed_writecode, R.id.regist_ck, R.id.regist_bt, R.id.regist_tv_login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.regist_return:
                finish();
                break;
            case R.id.regist_toolbar:
                break;
            case R.id.regist_ed_phone:

                break;
            case R.id.regist_ed_code:
                break;
            case R.id.regist_bt_code:
                //获取验证码
                Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                initCode();
                break;
            case R.id.regist_ed_psd:
                break;
            case R.id.regist_ed_writecode:
                break;
            case R.id.regist_ck:
                break;
            case R.id.regist_bt:
                int isck=0;  //0是不同意 默认为0
                String phone = mRegistEdPhone.getText().toString();   // 手机号
                String psd = mRegistEdPsd.getText().toString();      //密码
                String code = mRegistEdCode.getText().toString();  //短信验证码
                String writecode = mRegistEdWritecode.getText().toString();   //推荐吗
                if (mRegistCk.isChecked()){
                    isck=1;
                }else {
                    isck=0;
                }
                isPhone(phone,psd,code,writecode,isck);
                break;
            case R.id.regist_tv_login:
                finish();
                break;
        }
    }

    //验证码
    private void initCode() {
        String phone_code = mRegistEdPhone.getText().toString();
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone_code.length()==11&&phone_code.matches(regex)) {
            StaticToast.showShortToast(this, "请等待接受");
            mPresenter.getData(ApiConfig.POST_PHONE_CODE,phone_code);
        }else{
            StaticToast.showShortToast(this, "手机号输入有误");
        }
    }


    //注册bt
    public void isPhone(String phone, String pwd, String code, String writecode, int isck) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
                String regexpwd= "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,8}$";


        if (phone.length()==11&&!pwd.isEmpty()&&phone.matches(regex)&&pwd.matches(regexpwd)&& !TextUtils.isEmpty(code)&&writecode.length()==6) {
            int notecode=Integer.valueOf(code).intValue();
            int tuijiancode=Integer.valueOf(writecode).intValue();
            int user=Integer.valueOf(isck).intValue();

            mPresenter.getData(ApiConfig.REGISTER,phone,pwd,tuijiancode,user,notecode);
        }else{
            StaticToast.showShortToast(
                    this, "注册失败，请重新注册");
        }
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
            switch (whichApi){
                    case ApiConfig.POST_PHONE_CODE:
                    //注册的手机验证码
                    NoteCode_Bean noteCode_bean= (NoteCode_Bean) successResult;
                        //验证码
                        content = noteCode_bean.getContent();
                    break;

                    case ApiConfig.REGISTER:
                        RegisterBean registerBean= (RegisterBean) successResult;
                        if (registerBean.getCode()==200){
                            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(this, "注册失败,信息有误"+registerBean.getCode(), Toast.LENGTH_SHORT).show();
                        }
                        break;
            }
    }

}
