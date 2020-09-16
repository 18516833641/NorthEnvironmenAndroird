package com.hoho.beike.ui.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.hoho.beike.R;
import com.hoho.beike.ui.progress.CustomHorizontalProgresWithNum;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/24
 * Time: 1:12 PM
 */
public class UpDataActivity extends AppCompatActivity {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.horizontalProgress2)
    CustomHorizontalProgresWithNum horizontalProgress2;
    private Timer timer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        ButterKnife.bind(this);
//去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        horizontalProgress2.setProgress(0);
        horizontalProgress2.setMax(100);

        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                //实时更新进度
                if (horizontalProgress2.getProgress() >= 100) {//指定时间取消
                    timer2.cancel();
                }
                horizontalProgress2.setProgress(horizontalProgress2.getProgress() + 1);

            }
        }, 40, 40);

        if (horizontalProgress2.getProgress() == 100) {
            ToastUtils.showShort("更新完成");
            finish();
        }
    }

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }
}
