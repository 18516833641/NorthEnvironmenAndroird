package com.hoho.beike.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Request;
import com.hoho.beike.R;
import com.hoho.beike.adapter.ZiZhiAdapter;
import com.hoho.beike.bean.ZiZhiBean;
import com.hoho.beike.enent.IdEvent;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.HomeActivity3;
import com.hoho.beike.ui.ProjectActivity;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZiZhiActivity extends AppCompatActivity {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.title)
    TextView title;

    private ZiZhiAdapter ziZhiAdapter;

    private int type;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_zhi);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        ButterKnife.bind(this);

        type = getIntent().getExtras().getInt("type");

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

        LogUtils.e(type);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        ziZhiAdapter = new ZiZhiAdapter(new ArrayList<>());
        recyclerview.setAdapter(ziZhiAdapter);

        ziZhiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZiZhiBean.DataBean bean = (ZiZhiBean.DataBean) adapter.getItem(position);
                if (type == 6) {

                    Intent intent = new Intent(ZiZhiActivity.this, HomeActivity3.class);
                    intent.putExtra("url", url + bean.id);
                    if (position == 0) {
                        EventBus.getDefault().postSticky(new IdEvent(bean.id, position + 1));
                    } else if (position == 1) {
                        EventBus.getDefault().postSticky(new IdEvent(bean.id, position + 1));
                    }


                    intent.putExtra("type", type);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ZiZhiActivity.this, CompanyProfileActivity.class);
                    intent.putExtra("url", url + bean.id);
                    intent.putExtra("type", type);
                    startActivity(intent);
                }
            }
        });

    }


    private void getDetail() {
        if (type == 1) {
            url = "http://39.101.181.123:8080/bk/rest/bkHonerController/";
        } else if (type == 2) {
            url = "http://39.101.181.123:8080/bk/rest/bkBusController/";
        } else if (type == 3) {
            url = "http://39.101.181.123:8080/bk/rest/bkTecController/";
        } else if (type == 4) {
            url = "http://39.101.181.123:8080/bk/rest/bkAppController/";
        } else if (type == 5) {
            url = "http://39.101.181.123:8080/bk/rest/bkSampController/";
        } else if (type == 6) {
            url = "http://39.101.181.123:8080/bk/rest/bkProgramController";
        }
        LogUtils.e(url);
//        String id = SPUtils.getInstance().getString("id");
        String sToken = SPUtils.getInstance().getString("token");
        Request.Builder.create(url)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(this)
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);
                        ZiZhiBean ziZhiBean = ZiZhiBean.objectFromData(s);
                        ziZhiAdapter.setNewData(ziZhiBean.data);
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
                        startActivity(new Intent(ZiZhiActivity.this, LoginActivity2.class));
                    }
                })
                .get();
    }

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }
}