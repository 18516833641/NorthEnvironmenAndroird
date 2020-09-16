package com.hoho.beike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hoho.beike.R;
import com.hoho.beike.ui.fragment.GongYiFragment;
import com.hoho.beike.ui.fragment.GongZhangFragment;
import com.hoho.beike.ui.fragment.JianJieFragment;
import com.hoho.beike.ui.fragment.LiShiActivity;
import com.hoho.beike.ui.ui.dashboard.DashboardFragment;
import com.hoho.beike.ui.ui.home.HomeFragment;
import com.hoho.beike.ui.ui.notifications.NotificationsFragment;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/28
 * Time: 5:15 PM
 */
public class HomeActivity3 extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.linl)
    LinearLayout linl;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;

    private List<String> titles;
    private List<Fragment> fragments;
    private int[] tabIcons = {
            R.mipmap.home,
            R.mipmap.person,
            R.mipmap.setup
    };
    private FragmentManager fManager;//管理Fragment界面
    private Fragment jianjie, gongyi, lishi, guzhang;
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        Intent intent = getIntent();
        url = intent.getExtras().getString("url");

        fManager = getSupportFragmentManager();

        setChoiceItem(0);
    }

    /**
     * Fragment切换
     *
     * @param index
     */
    private void setChoiceItem(int index) {
        FragmentTransaction transaction = fManager.beginTransaction();

        hideFragments(transaction);
        switch (index) {
            case 0:
                if (jianjie == null) {
                    jianjie = new JianJieFragment();
                    transaction.add(R.id.container, jianjie);
                } else {
                    transaction.show(jianjie);
                }

                break;

            case 1:
                if (gongyi == null) {
                    gongyi = new GongYiFragment();
                    transaction.add(R.id.container, gongyi);
                } else {
                    transaction.show(gongyi);
                }
                break;
            case 2:
                if (lishi == null) {
                    lishi = new LiShiActivity();
                    transaction.add(R.id.container, lishi);
                } else {
                    transaction.show(lishi);
                }
                break;
            case 3:
                if (guzhang == null) {
                    guzhang = new GongZhangFragment();
                    transaction.add(R.id.container, guzhang);
                } else {
                    transaction.show(guzhang);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏片段
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (jianjie != null) {
            transaction.hide(jianjie);
        }
        if (gongyi != null) {
            transaction.hide(gongyi);
        }
        if (lishi != null) {
            transaction.hide(lishi);
        }
        if (guzhang != null) {
            transaction.hide(guzhang);
        }
    }


    private void changBackground(int id) {
        if (id == 0) {
            tv1.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
            tv2.setBackground(null);
            tv3.setBackground(null);
            tv4.setBackground(null);
        } else if (id == 1) {
            tv1.setBackground(null);
            tv2.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
            tv3.setBackground(null);
            tv4.setBackground(null);
        } else if (id == 2) {
            tv1.setBackground(null);
            tv2.setBackground(null);
            tv3.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
            tv4.setBackground(null);
        } else if (id == 3) {
            tv1.setBackground(null);
            tv2.setBackground(null);
            tv3.setBackground(null);
            tv4.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
        }
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                changBackground(0);
                setChoiceItem(0);
                break;
            case R.id.tv2:
                changBackground(1);
                setChoiceItem(1);
                break;
            case R.id.tv3:
                changBackground(2);
                setChoiceItem(2);
                break;
            case R.id.tv4:
                changBackground(3);
                setChoiceItem(3);
                break;
        }
    }
}
