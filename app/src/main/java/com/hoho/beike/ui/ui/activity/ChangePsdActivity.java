package com.hoho.beike.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Response;
import com.hoho.beike.R;
import com.hoho.beike.bean.ChangePsdBean;
import com.hoho.beike.bean.ZiZhiBean;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ChangePsdActivity extends AppCompatActivity {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.psd_et)
    EditText psdEt;
    @BindView(R.id.psd2_et)
    EditText psd2Et;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psd2);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        ButterKnife.bind(this);
    }

    private void getDetail() {
        String oldpsd = nameEt.getText().toString().trim();
        String newpsd = psdEt.getText().toString().trim();
        String newpsd2 = psd2Et.getText().toString().trim();
        LogUtils.e(isLetterDigit(newpsd));
        if (newpsd.length() < 8 || newpsd.length() > 32) {
            ToastUtils.showShort("密码长度应为8～32位");
            return;
        }
        if (!isLetterDigit(newpsd)){
            ToastUtils.showShort("密码应为字母加数字组合");
            return;
        }
        if (!newpsd.equals(newpsd2)) {
            ToastUtils.showShort("请确认密码是否相同");
            return;
        }
        String id = SPUtils.getInstance().getString("id");
        LogUtils.e(id);
        String url = "http://39.101.181.123:8080/bk/rest/bkUserController?userid=" + id + "&password=" + oldpsd + "&newpassword=" + newpsd;
        String sToken = SPUtils.getInstance().getString("token");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .writeTimeout(6000, TimeUnit.SECONDS).build();
        FormBody body = new FormBody.Builder()
                .add("userid", id)
                .add("password", oldpsd)
                .add("newpassword", newpsd)
                .build();
        Request request = new Request.Builder()
                .addHeader("X-AUTH-TOKEN", sToken)
                .url(url)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                startActivity(new Intent(ChangePsdActivity.this, LoginActivity2.class));
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String result = response.body().string();
                LogUtils.e(result);
                ChangePsdBean bean = ChangePsdBean.objectFromData(result);

                if (bean.success) {
                    ToastUtils.showShort("修改密码成功");
                    startActivity(new Intent(ChangePsdActivity.this, LoginActivity2.class));
                    finish();
                } else {
                    ToastUtils.showShort(bean.msg);
                }
            }

        });
    }

    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }

        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }


    @OnClick({R.id.back_iv, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.login_btn:
                getDetail();
                break;
        }
    }
}