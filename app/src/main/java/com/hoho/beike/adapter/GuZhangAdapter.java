package com.hoho.beike.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hoho.beike.R;
import com.hoho.beike.bean.GuZhangBean;

import java.util.List;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/24
 * Time: 8:58 PM
 */
public class GuZhangAdapter extends BaseQuickAdapter<GuZhangBean.DataBean, BaseViewHolder> {
    public GuZhangAdapter(@Nullable List<GuZhangBean.DataBean> data) {
        super(R.layout.item_guzhang, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GuZhangBean.DataBean item) {
        String d = "";
        if (TextUtils.isEmpty(item.update_date)) {
            d = "";
        } else {
            d = item.update_date;
        }
        helper.setText(R.id.tv1, "故障区域：" + item.itemnm)
                .setText(R.id.tv2, "故障开始日期：" + item.create_date)
                .setText(R.id.tv3, "故障截止日期：" + d);
    }
}
