package com.hoho.beike.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hoho.beike.R;
import com.hoho.beike.bean.ZiZhiBean;

import java.util.List;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 10:27 AM
 */
public class ZiZhiAdapter extends BaseQuickAdapter<ZiZhiBean.DataBean, BaseViewHolder> {
    public ZiZhiAdapter(@Nullable List<ZiZhiBean.DataBean> data) {
        super(R.layout.item_zizhi, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ZiZhiBean.DataBean item) {

        helper.setText(R.id.title_tv, item.tile);
        ImageView imageView = helper.getView(R.id.suolue_iv);

        String t = item.thmb.replace("\\", "/");

        Glide.with(mContext)
                .load("http://39.101.181.123:8080/bk/"+t)
                .into(imageView);

        helper.addOnClickListener(R.id.xq_tv);
    }
}
