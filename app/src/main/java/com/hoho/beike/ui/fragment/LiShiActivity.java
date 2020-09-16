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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hoho.beike.R;
import com.hoho.beike.adapter.GongYiAdatpter;
import com.hoho.beike.bean.GongYiBean;
import com.hoho.beike.bean.GongYiListBean;
import com.hoho.beike.enent.IdEvent;
import com.hoho.beike.ui.QuXianActivity;
import com.hoho.beike.ui.XiTongDetail;
import com.hoho.beike.ui.XiTongDetail2;
import com.hoho.beike.ui.ui.LoginActivity2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 9:40 PM
 */
public class LiShiActivity extends Fragment implements View.OnClickListener {

    TextView tt1;
    TextView tt2;
    TextView tt3;
    TextView tt4;
    TextView tt5;
    TextView tt6;
    TextView tt7;
    TextView tt8;
    TextView tt9;
    private GongYiAdatpter adatpter, adatpter2, adatpter3, adatpter4, adatpter5, adatpter6, adatpter7, adatpter8, adatpter9;

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

    private String[] names2 = {"脱硫塔入口温度",
            "脱硫塔出口温度",
            "脱硫塔入口压力",
            "脱硫塔出口压力",
            "1#喷射器进口压力",
            "2#喷射器进口压力",
            "3#喷射器进口压力"};

    private String[] names3 = {"除尘器出口温度",
            "除尘器出口压力",
            "1#加湿机压力",
            "2#加湿机压力"};

    private String[] names4 = {"高压变频器电流反馈",
            "高压变频器频率反馈",
            "高压变频器变频运行指示",
            "高压变频器工频运行指示",
            "高压变频器远程指示",
            "高压变频器系统就绪指示",
            "高压变频器运行指示",
            "高压变频器变频报警",
            "高压变频器变频故障",
            "主引风机轴承温度1",
            "主引风机轴承温度2",
            "主引风机冷却水压力",
            "主引风机风门执行器开度",
            "主引风机电机轴承温度1",
            "主引风机电机轴承温度2"};

    private String[] names5 = {"脱硫剂喷射器进口压力",
            "原料仓重量"};

    private String[] names6 = {"副产物仓重量"};

    private String[] names7 = {"进水流量计",
            "1#加湿机加水流量",
            "2#加湿机加水流量",
            "工艺水箱液位"};

    private String[] names8 = {"储罐氮气主管压力",
            "喷吹主管压力",
            "空气流量计",
            "氮气流量计"};

    private String[] names9 = {"冷风阀开度反馈",
            "一氧化碳浓度检测1",
            "原烟气挡板门开到位",
            "原烟气挡板门关到位",
            "净烟气挡板门开到位",
            "净烟气挡板门关到位",
            "旁路挡板门开到位",
            "旁路挡板门关到位"};

    List<GongYiListBean> list = new ArrayList<>();
    List<GongYiListBean> list2 = new ArrayList<>();
    List<GongYiListBean> list3 = new ArrayList<>();
    List<GongYiListBean> list4 = new ArrayList<>();
    List<GongYiListBean> list5 = new ArrayList<>();
    List<GongYiListBean> list6 = new ArrayList<>();
    List<GongYiListBean> list7 = new ArrayList<>();
    List<GongYiListBean> list8 = new ArrayList<>();
    List<GongYiListBean> list9 = new ArrayList<>();
    GongYiBean bean;
    int pos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lishi, container, false);
        RecyclerView rv1 = root.findViewById(R.id.recyclerview1);
        RecyclerView rv2 = root.findViewById(R.id.recyclerview2);
        RecyclerView rv3 = root.findViewById(R.id.recyclerview3);
        RecyclerView rv4 = root.findViewById(R.id.recyclerview4);
        RecyclerView rv5 = root.findViewById(R.id.recyclerview5);
        RecyclerView rv6 = root.findViewById(R.id.recyclerview6);
        RecyclerView rv7 = root.findViewById(R.id.recyclerview7);
        RecyclerView rv8 = root.findViewById(R.id.recyclerview8);
        RecyclerView rv9 = root.findViewById(R.id.recyclerview9);
        tt1 = root.findViewById(R.id.tt1);
        tt2 = root.findViewById(R.id.tt2);
        tt3 = root.findViewById(R.id.tt3);
        tt4 = root.findViewById(R.id.tt4);
        tt5 = root.findViewById(R.id.tt5);
        tt6 = root.findViewById(R.id.tt6);
        tt7 = root.findViewById(R.id.tt7);
        tt8 = root.findViewById(R.id.tt8);
        tt9 = root.findViewById(R.id.tt9);

        tt1.setOnClickListener(this);
        tt2.setOnClickListener(this);
        tt3.setOnClickListener(this);
        tt4.setOnClickListener(this);
        tt5.setOnClickListener(this);
        tt6.setOnClickListener(this);
        tt7.setOnClickListener(this);
        tt8.setOnClickListener(this);
        tt9.setOnClickListener(this);

        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv3.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv4.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv5.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv6.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv7.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv8.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv9.setLayoutManager(new LinearLayoutManager(getActivity()));
        adatpter = new GongYiAdatpter(new ArrayList<>());
        adatpter2 = new GongYiAdatpter(new ArrayList<>());
        adatpter3 = new GongYiAdatpter(new ArrayList<>());
        adatpter4 = new GongYiAdatpter(new ArrayList<>());
        adatpter5 = new GongYiAdatpter(new ArrayList<>());
        adatpter6 = new GongYiAdatpter(new ArrayList<>());
        adatpter7 = new GongYiAdatpter(new ArrayList<>());
        adatpter8 = new GongYiAdatpter(new ArrayList<>());
        adatpter9 = new GongYiAdatpter(new ArrayList<>());
        rv1.setAdapter(adatpter);
        rv2.setAdapter(adatpter2);
        rv3.setAdapter(adatpter3);
        rv4.setAdapter(adatpter4);
        rv5.setAdapter(adatpter5);
        rv6.setAdapter(adatpter6);
        rv7.setAdapter(adatpter7);
        rv8.setAdapter(adatpter8);
        rv9.setAdapter(adatpter9);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LogUtils.e("--=-=-=-=-=-=-=");
        getDetail(pos);

        adatpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2) {
                    if (position == 0) {
                        s = "TJRC05_01";
                    } else if (position == 1) {
                        s = "TJRC05_47";
                    } else if (position == 2) {
                        s = "TJRC05_48";
                    } else if (position == 3) {
                        s = "TJRC05_49";
                    } else if (position == 4) {
                        s = "TJRC05_50";
                    } else if (position == 5) {
                        s = "TJRC05_51";
                    } else if (position == 6) {
                        s = "TJRC05_52";
                    } else if (position == 7) {
                        s = "TJRC05_53";
                    } else if (position == 8) {
                        s = "TJRC05_54";
                    } else if (position == 9) {
                        s = "TJRC05_55";
                    } else if (position == 10) {
                        s = "TJRC05_56";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_01";
                    } else if (position == 1) {
                        s = "TJRC04_47";
                    } else if (position == 2) {
                        s = "TJRC04_48";
                    } else if (position == 3) {
                        s = "TJRC04_49";
                    } else if (position == 4) {
                        s = "TJRC04_50";
                    } else if (position == 5) {
                        s = "TJRC04_51";
                    } else if (position == 6) {
                        s = "TJRC04_52";
                    } else if (position == 7) {
                        s = "TJRC04_53";
                    } else if (position == 8) {
                        s = "TJRC04_54";
                    } else if (position == 9) {
                        s = "TJRC04_55";
                    } else if (position == 10) {
                        s = "TJRC04_56";
                    }
                }

                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names1[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2) {
                    if (position == 0) {
                        s = "TJRC05_02";
                    } else if (position == 1) {
                        s = "TJRC05_03";
                    } else if (position == 2) {
                        s = "TJRC05_04";
                    } else if (position == 3) {
                        s = "TJRC05_05";
                    } else if (position == 4) {
                        s = "TJRC05_06";
                    } else if (position == 5) {
                        s = "TJRC05_07";
                    } else if (position == 6) {
                        s = "TJRC05_08";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_02";
                    } else if (position == 1) {
                        s = "TJRC04_03";
                    } else if (position == 2) {
                        s = "TJRC04_04";
                    } else if (position == 3) {
                        s = "TJRC04_05";
                    } else if (position == 4) {
                        s = "TJRC04_06";
                    } else if (position == 5) {
                        s = "TJRC04_07";
                    } else if (position == 6) {
                        s = "TJRC04_08";
                    }
                }
                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names2[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2) {
                    if (position == 0) {
                        s = "TJRC05_09";
                    } else if (position == 1) {
                        s = "TJRC05_10";
                    } else if (position == 2) {
                        s = "TJRC05_11";
                    } else if (position == 3) {
                        s = "TJRC05_12";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_09";
                    } else if (position == 1) {
                        s = "TJRC04_10";
                    } else if (position == 2) {
                        s = "TJRC04_11";
                    } else if (position == 3) {
                        s = "TJRC04_12";
                    }
                }
                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names3[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }

        });

        adatpter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2) {
                    if (position == 0) {
                        s = "TJRC05_13";
                    } else if (position == 1) {
                        s = "TJRC05_14";
                    } else if (position == 2) {
                        s = "TJRC05_15";
                    } else if (position == 3) {
                        s = "TJRC05_16";
                    } else if (position == 4) {
                        s = "TJRC05_17";
                    } else if (position == 5) {
                        s = "TJRC05_18";
                    } else if (position == 6) {
                        s = "TJRC05_19";
                    } else if (position == 7) {
                        s = "TJRC05_20";
                    } else if (position == 8) {
                        s = "TJRC05_21";
                    } else if (position == 9) {
                        s = "TJRC05_22";
                    } else if (position == 10) {
                        s = "TJRC05_23";
                    } else if (position == 11) {
                        s = "TJRC05_24";
                    } else if (position == 12) {
                        s = "TJRC05_25";
                    } else if (position == 13) {
                        s = "TJRC05_26";
                    } else if (position == 14) {
                        s = "TJRC05_27";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_13";
                    } else if (position == 1) {
                        s = "TJRC04_14";
                    } else if (position == 2) {
                        s = "TJRC04_15";
                    } else if (position == 3) {
                        s = "TJRC04_16";
                    } else if (position == 4) {
                        s = "TJRC04_17";
                    } else if (position == 5) {
                        s = "TJRC04_18";
                    } else if (position == 6) {
                        s = "TJRC04_19";
                    } else if (position == 7) {
                        s = "TJRC04_20";
                    } else if (position == 8) {
                        s = "TJRC04_21";
                    } else if (position == 9) {
                        s = "TJRC04_22";
                    } else if (position == 10) {
                        s = "TJRC04_23";
                    } else if (position == 11) {
                        s = "TJRC04_24";
                    } else if (position == 12) {
                        s = "TJRC04_25";
                    } else if (position == 13) {
                        s = "TJRC04_26";
                    } else if (position == 14) {
                        s = "TJRC04_27";
                    }
                }


                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names4[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter5.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2) {
                    if (position == 0) {
                        s = "TJRC05_28";
                    } else if (position == 1) {
                        s = "TJRC05_29";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_28";
                    } else if (position == 1) {
                        s = "TJRC04_29";
                    }
                }
                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names5[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter6.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2) {
                    if (position == 0) {
                        s = "TJRC05_30";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_30";
                    }
                }

                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names6[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter7.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2){
                    if (position == 0) {
                        s = "TJRC05_31";
                    } else if (position == 1) {
                        s = "TJRC05_32";
                    } else if (position == 2) {
                        s = "TJRC05_33";
                    } else if (position == 3) {
                        s = "TJRC05_34";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_31";
                    } else if (position == 1) {
                        s = "TJRC04_32";
                    } else if (position == 2) {
                        s = "TJRC04_33";
                    } else if (position == 3) {
                        s = "TJRC04_34";
                    }
                }

                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names7[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter8.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2){
                    if (position == 0) {
                        s = "TJRC05_35";
                    } else if (position == 1) {
                        s = "TJRC05_36";
                    } else if (position == 2) {
                        s = "TJRC05_37";
                    } else if (position == 3) {
                        s = "TJRC05_38";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_35";
                    } else if (position == 1) {
                        s = "TJRC04_36";
                    } else if (position == 2) {
                        s = "TJRC04_37";
                    } else if (position == 3) {
                        s = "TJRC04_38";
                    }
                }


                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names8[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        adatpter9.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = "";
                if (pos == 2){
                    if (position == 0) {
                        s = "TJRC05_39";
                    } else if (position == 1) {
                        s = "TJRC05_40";
                    } else if (position == 2) {
                        s = "TJRC05_41";
                    } else if (position == 3) {
                        s = "TJRC05_42";
                    } else if (position == 4) {
                        s = "TJRC05_43";
                    } else if (position == 5) {
                        s = "TJRC05_44";
                    } else if (position == 6) {
                        s = "TJRC05_45";
                    } else if (position == 7) {
                        s = "TJRC05_46";
                    }
                } else {
                    if (position == 0) {
                        s = "TJRC04_39";
                    } else if (position == 1) {
                        s = "TJRC04_40";
                    } else if (position == 2) {
                        s = "TJRC04_41";
                    } else if (position == 3) {
                        s = "TJRC04_42";
                    } else if (position == 4) {
                        s = "TJRC04_43";
                    } else if (position == 5) {
                        s = "TJRC04_44";
                    } else if (position == 6) {
                        s = "TJRC04_45";
                    } else if (position == 7) {
                        s = "TJRC04_46";
                    }
                }


                Intent intent = new Intent(getActivity(), QuXianActivity.class);
                intent.putExtra("name", s);
                intent.putExtra("title", names9[position]);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAddrEvent(IdEvent event) {
        String id = event.id;
        pos = event.pos;
        LogUtils.e(pos);
    }

    private void getDetail(int pos) {

        String url = "http://39.101.181.123:8080/bk/rest/bkProgramController?itmeid=" + pos;
        String sToken = SPUtils.getInstance().getString("token");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .writeTimeout(6000, TimeUnit.SECONDS).build();
        FormBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .addHeader("X-AUTH-TOKEN", sToken)
                .url(url)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                startActivity(new Intent(getActivity(), LoginActivity2.class));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                LogUtils.e(result);

                bean = GongYiBean.objectFromData(result);
                for (int i = 0; i < names1.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
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


                for (int i = 0; i < names2.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_02);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_03);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_04);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_05);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_06);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_07);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC05_08);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_02);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_03);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_04);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_05);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_06);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_07);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names2[i], bean.data.TJRC04_08);
                        }
                    }
                    list2.add(listBean);
                }

                for (int i = 0; i < names3.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC05_09);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC05_10);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC05_11);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC05_12);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC04_09);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC04_10);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC04_11);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names3[i], bean.data.TJRC04_12);
                        }
                    }

                    list3.add(listBean);
                }

                for (int i = 0; i < names4.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_13);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_14);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_15);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_16);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_17);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_18);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_19);
                        } else if (i == 7) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_20);
                        } else if (i == 8) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_21);
                        } else if (i == 9) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_22);
                        } else if (i == 10) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_23);
                        } else if (i == 11) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_24);
                        } else if (i == 12) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_25);
                        } else if (i == 13) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_26);
                        } else if (i == 14) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC05_27);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_13);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_14);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_15);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_16);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_17);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_18);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_19);
                        } else if (i == 7) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_20);
                        } else if (i == 8) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_21);
                        } else if (i == 9) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_22);
                        } else if (i == 10) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_23);
                        } else if (i == 11) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_24);
                        } else if (i == 12) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_25);
                        } else if (i == 13) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_26);
                        } else if (i == 14) {
                            listBean = new GongYiListBean(names4[i], bean.data.TJRC04_27);
                        }
                    }
                    list4.add(listBean);
                }

                for (int i = 0; i < names5.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names5[i], bean.data.TJRC05_28);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names5[i], bean.data.TJRC05_29);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names5[i], bean.data.TJRC04_28);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names5[i], bean.data.TJRC04_29);
                        }
                    }

                    list5.add(listBean);
                }

                for (int i = 0; i < names6.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names6[i], bean.data.TJRC05_30);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names6[i], bean.data.TJRC04_30);
                        }
                    }

                    list6.add(listBean);
                }

                for (int i = 0; i < names7.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC05_31);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC05_32);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC05_33);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC05_34);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC04_31);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC04_32);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC04_33);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names7[i], bean.data.TJRC04_34);
                        }
                    }

                    list7.add(listBean);
                }

                for (int i = 0; i < names8.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC05_35);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC05_36);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC05_37);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC05_38);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC04_35);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC04_36);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC04_37);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names8[i], bean.data.TJRC04_38);
                        }
                    }

                    list8.add(listBean);
                }

                for (int i = 0; i < names9.length; i++) {
                    GongYiListBean listBean = null;
                    if (pos == 2) {
                        if (i == 0) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_39);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_40);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_41);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_42);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_43);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_44);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_45);
                        } else if (i == 7) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC05_46);
                        }
                    } else {
                        if (i == 0) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_39);
                        } else if (i == 1) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_40);
                        } else if (i == 2) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_41);
                        } else if (i == 3) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_42);
                        } else if (i == 4) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_43);
                        } else if (i == 5) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_44);
                        } else if (i == 6) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_45);
                        } else if (i == 7) {
                            listBean = new GongYiListBean(names9[i], bean.data.TJRC04_46);
                        }
                    }

                    list9.add(listBean);
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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    adatpter.setNewData(list);
                    adatpter2.setNewData(list2);
                    adatpter3.setNewData(list3);
                    adatpter4.setNewData(list4);
                    adatpter5.setNewData(list5);
                    adatpter6.setNewData(list6);
                    adatpter7.setNewData(list7);
                    adatpter8.setNewData(list8);
                    adatpter9.setNewData(list9);
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), XiTongDetail2.class);
        intent.putExtra("bean", bean);
        intent.putExtra("pos", pos);
        switch (v.getId()) {
            case R.id.tt1:
                intent.putExtra("type", 1);

                startActivity(intent);
                break;
            case R.id.tt2:
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.tt3:
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.tt4:
                intent.putExtra("type", 4);
                startActivity(intent);
                break;
            case R.id.tt5:
                intent.putExtra("type", 5);
                startActivity(intent);
                break;
            case R.id.tt6:
                intent.putExtra("type", 6);
                startActivity(intent);
                break;
            case R.id.tt7:
                intent.putExtra("type", 7);
                startActivity(intent);
                break;
            case R.id.tt8:
                intent.putExtra("type", 8);
                startActivity(intent);
                break;
            case R.id.tt9:
                intent.putExtra("type", 9);
                startActivity(intent);
                break;
        }
    }

//    @Override
//    public void onClick(View view) {
//
//    }
}
