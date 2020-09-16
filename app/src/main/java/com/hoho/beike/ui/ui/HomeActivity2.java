package com.hoho.beike.ui.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hoho.beike.R;
import com.hoho.beike.ui.ui.dashboard.DashboardFragment;
import com.hoho.beike.ui.ui.home.HomeFragment;
import com.hoho.beike.ui.ui.notifications.NotificationsFragment;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/28
 * Time: 5:15 PM
 */
public class HomeActivity2 extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.linl1)
    LinearLayout linl1;
    @BindView(R.id.linl2)
    LinearLayout linl2;
    @BindView(R.id.linl3)
    LinearLayout linl3;
    @BindView(R.id.linl)
    LinearLayout linl;

    private List<String> titles;
    private List<Fragment> fragments;
    private int[] tabIcons = {
            R.mipmap.home,
            R.mipmap.person,
            R.mipmap.setup
    };
    private FragmentManager fManager;//管理Fragment界面
    private Fragment home, person, setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();

        fManager = getSupportFragmentManager();

        setChoiceItem(0);

        getDetail(SPUtils.getInstance().getString("UMtoken"));
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

                if (home == null) {
                    home = new HomeFragment();
                    transaction.add(R.id.container, home);
                } else {
                    transaction.show(home);
                }

                break;

            case 1:

                if (person == null) {
                    person = new DashboardFragment();
                    transaction.add(R.id.container, person);
                } else {
                    transaction.show(person);
                }
                break;
            case 2:

                if (setting == null) {
                    setting = new NotificationsFragment();
                    transaction.add(R.id.container, setting);
                } else {
                    transaction.show(setting);
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
        if (home != null) {
            transaction.hide(home);
        }
        if (person != null) {
            transaction.hide(person);
        }
        if (setting != null) {
            transaction.hide(setting);
        }
    }


    @OnClick({R.id.linl1, R.id.linl2, R.id.linl3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linl1:
                changBackground(0);
                setChoiceItem(0);
                break;
            case R.id.linl2:
                changBackground(1);
                setChoiceItem(1);
                break;
            case R.id.linl3:
                changBackground(2);
                setChoiceItem(2);
                break;
        }
    }

    private void changBackground(int id) {
        if (id == 0) {
            linl1.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
            linl2.setBackground(null);
            linl3.setBackground(null);
        } else if (id == 1) {
            linl1.setBackground(null);
            linl2.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
            linl3.setBackground(null);
        } else if (id == 2) {
            linl1.setBackground(null);
            linl2.setBackground(null);
            linl3.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_tab_back));
        }
    }

    private void getDetail(String uid) {
        String sToken = SPUtils.getInstance().getString("token");
        String url = "http://39.101.181.123:8080/bk/rest/bkSmsController?mid=" + uid;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .writeTimeout(6000, TimeUnit.SECONDS).build();
        FormBody body = new FormBody.Builder()
                .add("mid", uid)
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("X-AUTH-TOKEN", sToken)
                .url(url)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                String result = response.body().string();
                LogUtils.e(result);
//                ChangePsdBean bean = ChangePsdBean.objectFromData(result);

//                if (bean.success) {
//                    ToastUtils.showShort("修改密码成功");
//                } else {
//                    ToastUtils.showShort(bean.msg);
//                }
            }

        });
    }
}
