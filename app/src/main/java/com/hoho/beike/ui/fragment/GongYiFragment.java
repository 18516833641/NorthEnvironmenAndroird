package com.hoho.beike.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hoho.beike.R;
import com.hoho.beike.adapter.GongYiAdatpter;
import com.hoho.beike.bean.GongYiBean;
import com.hoho.beike.bean.GongYiListBean;
import com.hoho.beike.enent.IdEvent;
import com.hoho.beike.ui.QuXianActivity;
import com.hoho.beike.ui.XiTongDetail;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 9:40 PM
 */
public class GongYiFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerview;

    private GongYiAdatpter adatpter;
    String url;
    private int pos;
    private String[] names1 = {"脱硫入口烟气O₂浓度",
            "脱硫入口烟气SO₂浓度",
            "脱硫入口烟气NOx浓度",
            "脱硫入口烟气粉尘含量",
            "脱硫入口烟气流量",
            "脱硫出口烟气SO₂浓度",
            "脱硫塔压差",
            "除尘器压差",
            "本小时出口SO₂小时均值",
            "上一小时出口SO₂小时均值",
            "上一小时入口SO₂小时均值"};

    List<GongYiListBean> list = new ArrayList<>();
    GongYiBean bean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gongyi, container, false);
        ButterKnife.bind(root);

        TextView tv2 = root.findViewById(R.id.tv2);
        TextView tv3 = root.findViewById(R.id.tv3);
        TextView tv4 = root.findViewById(R.id.tv4);
        TextView tv5 = root.findViewById(R.id.tv5);
        TextView tv6 = root.findViewById(R.id.tv6);
        TextView tv7 = root.findViewById(R.id.tv7);
        TextView tv8 = root.findViewById(R.id.tv8);
        TextView tv9 = root.findViewById(R.id.tv9);

        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        recyclerview = root.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adatpter = new GongYiAdatpter(new ArrayList<>());
        recyclerview.setAdapter(adatpter);

        adatpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        getDetail(pos);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), XiTongDetail.class);
        intent.putExtra("bean", bean);
        intent.putExtra("pos", pos);
        int type = 0;
        switch (v.getId()) {
            case R.id.tv2://原料
                type = 1;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv3:
                type = 2;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv4://原料
                type = 3;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv5://原料
                type = 4;
                intent.putExtra("type", type);
                startActivity(intent);
                break;

            case R.id.tv6://原料
                type = 5;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv7://原料
                type = 6;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv8://原料
                type = 7;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case R.id.tv9://原料
                type = 8;
                intent.putExtra("type", type);
                startActivity(intent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAddrEvent(IdEvent event) {
        String id = event.id;
        pos = event.pos;
        LogUtils.e(pos);

    }

    private void getDetail(int pos) {
        LogUtils.e(pos);
        String url = "http://39.101.181.123:8080/bk/rest/bkProgramController?itmeid=" + pos;
        String sToken = SPUtils.getInstance().getString("token");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .writeTimeout(6000, TimeUnit.SECONDS).build();
        FormBody body = new FormBody.Builder()
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
                startActivity(new Intent(getActivity(), LoginActivity2.class));
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {


                String result = response.body().string();
                LogUtils.e(result);

                bean = GongYiBean.objectFromData(result);

                GongYiListBean listBean = null;
                for (int i = 0; i < names1.length; i++) {

                    if(pos == 2){
                        if (i == 0) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_01);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_47);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_48);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_49);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_50);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_51);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_52);
                        } else if (i == 7) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_53);
                        } else if (i == 8) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_54);
                        } else if (i == 9) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_55);
                        } else if (i == 10) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC05_56);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_01);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_47);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_48);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_49);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_50);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_51);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_52);
                        } else if (i == 7) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_53);
                        } else if (i == 8) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_54);
                        } else if (i == 9) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_55);
                        } else if (i == 10) {
                            listBean = new GongYiListBean(names1[i], bean.data.TJRC04_56);
                        }
                    }
                    list.add(listBean);
                }

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        handler.sendEmptyMessage(1);
                    }
                });
            }


        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    adatpter.setNewData(list);
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
