package com.hoho.beike.ui;

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
import com.hoho.beike.R;
import com.hoho.beike.adapter.GongYiAdatpter;
import com.hoho.beike.bean.GongYiBean;
import com.hoho.beike.bean.GongYiListBean;

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
 * Date: 2020/8/25
 * Time: 1:13 PM
 */
public class DataRecordActivity extends AppCompatActivity {

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
    GongYiBean bean;

    private String[] names1 = {"脱硫入口烟气O2浓度",
            "脱硫入口烟气SO2浓度",
            "脱硫入口烟气NOX浓度",
            "脱硫入口烟气粉尘含量",
            "脱硫入口烟气流量",
            "脱硫出口烟气SO2浓度",
            "脱硫塔压差",
            "除尘器压差",
            "本小时出口SO2小时均值",
            "上一小时出口SO2小时均值",
            "上一小时入口SO2小时均值", "脱硫塔入口温度",
            "脱硫塔出口温度",
            "脱硫塔入口压力",
            "脱硫塔出口压力",
            "1#喷射器进口压力",
            "2#喷射器进口压力",
            "3#喷射器进口压力", "除尘器出口温度",
            "除尘器出口压力",
            "1#加湿机压力",
            "2#加湿机压力", "高压变频器电流反馈",
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
            "主引风机电机轴承温度2", "脱硫剂喷射器进口压力",
            "原料仓重量", "副产物仓重量", "进水流量计",
            "1#加湿机加水流量",
            "2#加湿机加水流量",
            "工艺水箱液位", "储罐氮气主管压力",
            "喷吹主管压力",
            "空气流量计",
            "氮气流量计", "冷风阀开度反馈",
            "一氧化碳浓度检测1",
            "原烟气挡板门开到位",
            "原烟气挡板门关到位",
            "净烟气挡板门开到位",
            "净烟气挡板门关到位",
            "旁路挡板门开到位",
            "旁路挡板门关到位"};
    GongYiAdatpter adatpter;

    List<GongYiListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record);
        ButterKnife.bind(this);
        //去除标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();


        bean = (GongYiBean) getIntent().getSerializableExtra("bean");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adatpter = new GongYiAdatpter(new ArrayList<>());
        recyclerview.setAdapter(adatpter);

        LogUtils.e(names1.length);
        for (int i = 0; i < names1.length; i++) {
            GongYiListBean listBean = null;

            if (i == 0) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_24);
            } else if (i == 1) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_25);
            } else if (i == 2) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_26);
            } else if (i == 3) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_27);
            } else if (i == 4) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_28);
            } else if (i == 5) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_29);
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

            else if (i == 11) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_02);
            } else if (i == 12) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_03);
            } else if (i == 13) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_04);
            } else if (i == 14) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_05);
            } else if (i == 15) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_06);
            } else if (i == 16) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_07);
            } else if (i == 17) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_08);
            }

            else if (i == 18) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_09);
            } else if (i == 19) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_10);
            } else if (i == 20) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_11);
            } else if (i == 21) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_12);
            }

            else if (i == 22) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_13);
            } else if (i ==23) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_14);
            } else if (i == 24) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_15);
            } else if (i == 25) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_16);
            } else if (i == 26) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_17);
            } else if (i == 27) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_18);
            } else if (i == 28) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_19);
            } else if (i == 29) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_20);
            } else if (i == 30) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_21);
            } else if (i == 31) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_22);
            } else if (i == 32) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_23);
            } else if (i == 33) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_24);
            } else if (i == 34) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_25);
            } else if (i == 35) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_26);
            } else if (i == 36) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_27);
            }

            else if (i == 37) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_28);
            } else if (i == 38) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_29);
            }


            else if (i == 39) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_30);
            }


            else if (i == 40) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_31);
            } else if (i == 41) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_32);
            } else if (i == 42) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_33);
            } else if (i == 43) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_34);
            }

            else if (i == 44) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_35);
            } else if (i == 45) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_36);
            } else if (i == 46) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_37);
            } else if (i == 47) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_38);
            }

            else if (i == 48) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_39);
            } else if (i == 49) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_40);
            } else if (i == 50) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_41);
            } else if (i == 51) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_42);
            } else if (i == 52) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_43);
            } else if (i == 53) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_44);
            } else if (i == 54) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_45);
            } else if (i == 55) {
                listBean = new GongYiListBean(names1[i], bean.data.TJRC04_46);
            }
            list.add(listBean);
        }

        adatpter.setNewData(list);

    }

    @OnClick(R.id.back_iv)
    public void onClick() {

        finish();
    }
}
