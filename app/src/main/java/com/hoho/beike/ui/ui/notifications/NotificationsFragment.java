package com.hoho.beike.ui.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hoho.beike.R;
import com.hoho.beike.dialog.BottomDialog;
import com.hoho.beike.enent.IdEvent;
import com.hoho.beike.enent.LogoutEvent;
import com.hoho.beike.enent.UpEvent;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.activity.AbountActivity;
import com.hoho.beike.ui.ui.activity.VersionActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        TextView tv1 = root.findViewById(R.id.tv1);
        TextView tv2 = root.findViewById(R.id.tv2);
        TextView tv3 = root.findViewById(R.id.tv3);
        TextView tv4 = root.findViewById(R.id.tv4);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialog bottomDialog = new BottomDialog(getActivity(), 1);
                bottomDialog.setTitle("清除缓存");
                bottomDialog.show();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AbountActivity.class));
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VersionActivity.class));
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialog bottomDialog = new BottomDialog(getActivity(), 2);
                bottomDialog.setTitle("退出登录");
                bottomDialog.show();
            }
        });

        return root;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAddrEvent(LogoutEvent event) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        requireActivity().finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAddrEvent(UpEvent event) {
        ToastUtils.showShort("清除缓存成功");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}