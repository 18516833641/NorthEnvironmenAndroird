package com.hoho.beike.ui.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.hoho.beike.R;
import com.hoho.beike.bean.CompanyBean;
import com.hoho.beike.bean.CompanyBean1;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import java.net.URL;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyProfileActivity extends AppCompatActivity {


    //    @BindView(R.id.content_web)
//    WebView contentWeb;
    @BindView(R.id.back_iv)
    ImageView backIv;

    String url;
    @BindView(R.id.title)
    TextView title;

    int type;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.tv_note_content)
    TextView tvNoteContent;

    CharSequence charSequence;
    CompanyBean1 bean1;
    CompanyBean companyBean;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getExtras().getString("url");
        type = intent.getExtras().getInt("type");

        if (type == 1) {
            title.setText("资质荣誉");
        } else if (type == 2) {
            title.setText("主营业务");
        } else if (type == 3) {
            title.setText("主导技术");
        } else if (type == 4) {
            title.setText("行业应用");
        } else if (type == 5) {
            title.setText("工程案例");
        } else if (type == 6) {
            title.setText("运营项目");
        }

        getDetail();
    }

    private void getDetail() {

        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        String sToken = SPUtils.getInstance().getString("token");
        com.egbert.rconcise.internal.http.Request.Builder.create(url)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(this)
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);
                        if (type == 0) {
                           bean1 = CompanyBean1.objectFromData(s);
                            if (bean1.ok) {
                                new Thread(networkTask).start();
                            }
                        } else {
                           companyBean = CompanyBean.objectFromData(s);
                            if (companyBean.ok) {
                                new Thread(networkTask2).start();
                            }
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
                        startActivity(new Intent(CompanyProfileActivity.this, LoginActivity2.class));
                    }
                })
                .get();
    }


    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            charSequence = Html.fromHtml(bean1.data.get(0).content, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    LogUtils.e(source);
                    Drawable d = null;
                    try {
//                                            Bitmap bm = getPicIo(source);
//                                            d = new BitmapDrawable(bm);
//                                            d.setBounds(0, 0, 200, 300);

                        URL url = new URL(source);
                        d = Drawable.createFromStream(url.openStream(), "");
                        d.setBounds(0, 0, tvNoteContent.getWidth(), tvNoteContent.getWidth());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return d    ;
                }
            }, null);

            handler.sendEmptyMessage(0);
        }
    };

    Runnable networkTask2 = new Runnable() {

        @Override
        public void run() {
            charSequence = Html.fromHtml(companyBean.data.content, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    LogUtils.e(source);
                    Drawable d = null;
                    try {

                        URL url = new URL(source);
                        d = Drawable.createFromStream(url.openStream(), "");
                        d.setBounds(0, 0, tvNoteContent.getWidth(), tvNoteContent.getWidth());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return d    ;
                }
            }, null);

            handler.sendEmptyMessage(0);
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:



                    tvNoteContent.setText(charSequence);
                    //该语句在设置后必加，不然没有任何效果
                    tvNoteContent.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
            }
        }
    };

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }


}


