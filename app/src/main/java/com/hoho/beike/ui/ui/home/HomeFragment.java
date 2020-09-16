package com.hoho.beike.ui.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Request;
import com.hoho.beike.R;
import com.hoho.beike.bean.ZiZhiBean;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.HomeActivity3;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.ui.ui.activity.CompanyProfileActivity;
import com.hoho.beike.ui.ui.activity.ZiZhiActivity;
import com.hoho.beike.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.c2)
    ConstraintLayout c2;
    private HomeViewModel homeViewModel;
    private int code;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(root);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Button btn1 = root.findViewById(R.id.btn1);
        Button btn2 = root.findViewById(R.id.btn2);
        Button btn3 = root.findViewById(R.id.btn3);
        Button btn4 = root.findViewById(R.id.btn4);
        Button btn5 = root.findViewById(R.id.btn5);
        Button btn6 = root.findViewById(R.id.btn6);
        TextView yunyingTv = root.findViewById(R.id.yunying_tv);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(0);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(1);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(2);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(3);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(4);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(5);

            }
        });

        yunyingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(6);

            }
        });
        return root;
    }

    private void getDetail(int type) {
        String url = "";
        if (type == 0) {
            url = "http://39.101.181.123:8080/bk/rest/bkComdecripController/";
        } else if (type == 1) {
            url = "http://39.101.181.123:8080/bk/rest/bkHonerController/";
        } else if (type == 2) {
            url = "http://39.101.181.123:8080/bk/rest/bkBusController/";
        } else if (type == 3) {
            url = "http://39.101.181.123:8080/bk/rest/bkTecController/";
        } else if (type == 4) {
            url = "http://39.101.181.123:8080/bk/rest/bkAppController/";
        } else if (type == 5) {
            url = "http://39.101.181.123:8080/bk/rest/bkSampController/";
        } else if (type == 6) {
            url = "http://39.101.181.123:8080/bk/rest/bkProgramController";
        }
        LogUtils.e(url);
//        String id = SPUtils.getInstance().getString("id");
        String sToken = SPUtils.getInstance().getString("token");
        Request.Builder.create(url)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(getActivity())
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);
                        ZiZhiBean ziZhiBean = ZiZhiBean.objectFromData(s);
                        if (ziZhiBean.respCode == -1) {
                            ToastUtils.showShort(ziZhiBean.message);
                            code = -1;
                        } else {
                            code = 0;
                            if (type == 0) {
                                Intent intent = new Intent(getActivity(), CompanyProfileActivity.class);
                                intent.putExtra("type", 0);
                                intent.putExtra("url", "http://39.101.181.123:8080/bk/rest/bkComdecripController/");
                                startActivity(intent);
                            } else if (type == 1) {
                                Intent intent = new Intent(getActivity(), ZiZhiActivity.class);
                                intent.putExtra("type", 1);
                                startActivity(intent);
                            } else if (type == 2) {
                                Intent intent = new Intent(getActivity(), ZiZhiActivity.class);
                                intent.putExtra("type", 2);
                                startActivity(intent);
                            } else if (type == 3) {
                                Intent intent = new Intent(getActivity(), ZiZhiActivity.class);
                                intent.putExtra("type", 3);
                                startActivity(intent);
                            } else if (type == 4) {
                                Intent intent = new Intent(getActivity(), ZiZhiActivity.class);
                                intent.putExtra("type", 4);
                                startActivity(intent);
                            } else if (type == 5) {
                                Intent intent = new Intent(getActivity(), ZiZhiActivity.class);
                                intent.putExtra("type", 5);
                                startActivity(intent);
                            } else if (type == 6) {
                                Intent intent = new Intent(getActivity(), ZiZhiActivity.class);
                                intent.putExtra("type", 6);
                                startActivity(intent);
                            }
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
}