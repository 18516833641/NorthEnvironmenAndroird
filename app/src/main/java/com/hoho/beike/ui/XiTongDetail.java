package com.hoho.beike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.hoho.beike.adapter.GongYiAdatpter;
import com.hoho.beike.bean.CompanyBean;
import com.hoho.beike.bean.GongYiBean;
import com.hoho.beike.bean.GongYiListBean;
import com.hoho.beike.bean.ZiZhiBean;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/23
 * Time: 3:24 PM
 */
public class XiTongDetail extends AppCompatActivity {
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.xitong_iv)
    ImageView xitongIv;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private GongYiAdatpter adatpter;

    int type = 0;
    String id;

    GongYiBean bean;

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

    private int pos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xitong_detail);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        type = getIntent().getExtras().getInt("type");
        bean = (GongYiBean) getIntent().getSerializableExtra("bean");
        pos = getIntent().getExtras().getInt("pos");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adatpter = new GongYiAdatpter(new ArrayList<>());
        recyclerview.setAdapter(adatpter);


        if (type == 1) {
            xitongIv.setImageResource(R.mipmap.ylxt);
            title.setText("原料系统");

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
                list.add(listBean);
            }
            adatpter.setNewData(list);
            id = "";
        } else if (type == 2) {
            xitongIv.setImageResource(R.mipmap.qxt);
            title.setText("气系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);
        } else if (type == 3) {
            xitongIv.setImageResource(R.mipmap.tlxt);
            title.setText("脱硫系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);
        } else if (type == 4) {
            xitongIv.setImageResource(R.mipmap.ccxt);
            title.setText("除尘系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);
        } else if (type == 5) {
            xitongIv.setImageResource(R.mipmap.fjxt);
            title.setText("风机系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);
        } else if (type == 6) {
            xitongIv.setImageResource(R.mipmap.yqxt);
            title.setText("烟气系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);

        } else if (type == 7) {
            xitongIv.setImageResource(R.mipmap.sxt);
            title.setText("水系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);
        } else if (type == 8) {
            xitongIv.setImageResource(R.mipmap.fcwxt);
            title.setText("副产物系统");
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
                list.add(listBean);
            }
            adatpter.setNewData(list);
        }

        adatpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void getDetail(String id) {
//
//        if (TextUtils.isEmpty(url)) {
//            url = "";
//        }
        String sToken = SPUtils.getInstance().getString("token");
        Request.Builder.create("http://39.101.181.123:8080/bk/rest/bkProgramController?itmeid=" + id)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(this)
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);

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
                        startActivity(new Intent(XiTongDetail.this, LoginActivity2.class));
                    }
                })
                .get();
    }

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }
}
