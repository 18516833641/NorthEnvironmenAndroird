package com.hoho.beike.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hoho.beike.R;
import com.hoho.beike.bean.GongYiListBean;

import java.util.List;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/20
 * Time: 9:17 PM
 */
public class GongYiAdatpter extends BaseQuickAdapter<GongYiListBean, BaseViewHolder> {
    public GongYiAdatpter(@Nullable List<GongYiListBean> data) {
        super(R.layout.item_gongyi, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GongYiListBean item) {

        String v = "";
        if(item.getValue().contains("O2")){
            v = item.getValue().replace("02","O₂");
        } else if (item.getValue().contains("SO2")){
            v = item.getValue().replace("SO2", "SO₂");
        } else if (item.getValue().contains("m3")){
            v = item.getValue().replace("m3", "m³");
        } else if (item.getValue().contains("NOX")){
            v = item.getValue().replace("NOX", "NOx");
        } else {
            v = item.getValue();
        }
        helper.setText(R.id.tv1, item.getName()).setText(R.id.tv_1, v);
    }
}
