package com.hoho.beike.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Request;
import com.hoho.beike.R;
import com.hoho.beike.bean.CompanyBean;
import com.hoho.beike.bean.GuZhangBean;
import com.hoho.beike.enent.IdEvent;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.GuZhangDetailActivity;
import com.hoho.beike.ui.QuXianActivity;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 9:41 PM
 */
public class GongZhangFragment extends Fragment {
    int pos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_guzhang, container, false);
        TextView tv1 = root.findViewById(R.id.tv1);
        TextView tv2 = root.findViewById(R.id.tv2);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(pos, 1);
            }


        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(pos, 0);
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getDetail(pos, 0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAddrEvent(IdEvent event) {
        String id = event.id;
        pos = event.pos;
        LogUtils.e(pos, 0);

    }

    private void getDetail(int pos, int id) {
        String sToken = SPUtils.getInstance().getString("token");
        Request.Builder.create("http://39.101.181.123:8080/bk/rest/bkAlertController/" + pos + "&" + id)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(getActivity())
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);
                        GuZhangBean bean = GuZhangBean.objectFromData(s);
                        if (bean.ok) {
                            Intent intent = new Intent(getActivity(), GuZhangDetailActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("bean", bean);
                            getActivity().startActivity(intent);
                        }
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
                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                    }
                })
                .get();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
