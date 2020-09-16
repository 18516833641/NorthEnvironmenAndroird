package com.hoho.beike.ui.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.hoho.beike.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhaoHaoActivity extends AppCompatActivity {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.mingzi_tv)
    TextView mingziTv;
    @BindView(R.id.shouji_tv)
    TextView shoujiTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhao_hao);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        ButterKnife.bind(this);

        mingziTv.setText(SPUtils.getInstance().getString("username"));
        shoujiTv.setText(SPUtils.getInstance().getString("phn"));
    }

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }
}