package com.hoho.beike.ui.ui.dashboard;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hoho.beike.R;
import com.hoho.beike.ui.ui.activity.ChangePsdActivity;
import com.hoho.beike.ui.ui.activity.ZhaoHaoActivity;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        ImageView iv = root.findViewById(R.id.avatar_iv);
        TextView textView1 = root.findViewById(R.id.tv1);
        TextView textView2 = root.findViewById(R.id.tv2);
        TextView phoneTv = root.findViewById(R.id.phone_tv);

        phoneTv.setText(SPUtils.getInstance().getString("phn"));

        Bitmap bmp = ImageUtils.toRound(openImage());

        iv.setImageBitmap(bmp);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ZhaoHaoActivity.class));
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePsdActivity.class));
            }
        });
        return root;
    }

    /**
     * 将本地图片转成Bitmap
     * @param
     * @return
     */
    public Bitmap openImage(){
         Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_avatar);

//        Resources r = this.getContext().getResources();
//        InputStream is = r.openRawResource(R.drawable.xxx);
//        BitmapDrawable bmpDraw = new BitmapDrawable(is);
//        Bitmap bmp = bmpDraw.getBitmap();
//
//        Resources r = this.getContext().getResources();
//
//        Bitmap bmp=BitmapFactory.decodeResource(r, R.drawable.icon);
//        Bitmap bmp = Bitmap.createBitmap( 300, 300, Bitmap.Config.ARGB_8888 );
        return bitmap;
    }
}