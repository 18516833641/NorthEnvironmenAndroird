package com.hoho.beike.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoho.beike.R;
import com.hoho.beike.adapter.GuZhangAdapter;
import com.hoho.beike.bean.GuZhangBean;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/21
 * Time: 3:50 PM
 */
public class GuZhangDetailActivity extends AppCompatActivity {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    GuZhangAdapter adatpter;
    GuZhangBean bean;

    @BindView(R.id.tv1)
    TextView tv1;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guzhang_detail);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();

        bean = (GuZhangBean) getIntent().getExtras().getSerializable("bean");
        id = getIntent().getExtras().getInt("id");
        if (id == 0) {
            tv1.setCompoundDrawables(ContextCompat.getDrawable(this, R.mipmap.ic_jiejue), null, null, null);
            tv1.setTextColor(ContextCompat.getColor(this, R.color.tt));
            tv1.setText("已解除");

        } else {
            tv1.setCompoundDrawables(ContextCompat.getDrawable(this, R.mipmap.ic_guzhang), null, null, null);
            tv1.setText("故障中");
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adatpter = new GuZhangAdapter(new ArrayList<>());
        recyclerview.setAdapter(adatpter);

        adatpter.setNewData(bean.data);
    }

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }
}
