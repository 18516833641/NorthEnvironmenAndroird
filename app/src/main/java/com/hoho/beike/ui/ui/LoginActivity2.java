package com.hoho.beike.ui.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Request;
import com.hoho.beike.R;
import com.hoho.beike.bean.LoginBean;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.utils.Const;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity2 extends AppCompatActivity {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.psd_et)
    EditText psdEt;
    @BindView(R.id.forget_tv)
    TextView forgetTv;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();

    }


    private void login() {
        String userName = nameEt.getText().toString().trim();
        String psd = psdEt.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShort("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(psd)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", psd);
        Request.Builder.create("http://39.101.181.123:8080/bk/rest/bkUserController?username=" + userName + "&password=" + psd)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
//                .addParams(map)
                .setActivity(this)
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);

                        LoginBean loginBean = LoginBean.objectFromData(s);
                        if (loginBean.success) {
                            SPUtils.getInstance().put("username", loginBean.attributes.userNm);
                            SPUtils.getInstance().put("id", loginBean.attributes.id);
                            SPUtils.getInstance().put("token", loginBean.attributes.token);
                            SPUtils.getInstance().put("phn", loginBean.attributes.phn);
//                            LoginActivity2.this.startActivity(new Intent(LoginActivity2.this, HomeActivity2.class));
                            finish();
                        } else {
                            ToastUtils.showShort(loginBean.msg);
                        }
                    }

                    @Override
                    public void onError(Exception e, String desp) {
                        super.onError(e, desp);
                        e.printStackTrace();
                        LogUtils.e(desp);
                    }

                    @Override
                    public void onFailure(int respCode, String desp) {
                        super.onFailure(respCode, desp);
                        LogUtils.e(respCode, desp);
                    }
                })
                .post();
    }

    @OnClick({R.id.forget_tv, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_tv:
                break;
            case R.id.login_btn:
                login();
                break;
        }
    }
}