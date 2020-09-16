package com.hoho.beike.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.egbert.rconcise.RConcise;
import com.egbert.rconcise.internal.ContentType;
import com.egbert.rconcise.internal.HeaderField;
import com.egbert.rconcise.internal.http.Request;
import com.hoho.beike.R;
import com.hoho.beike.bean.CompanyBean;
import com.hoho.beike.enent.IdEvent;
import com.hoho.beike.listener.RespListener;
import com.hoho.beike.ui.ui.LoginActivity;
import com.hoho.beike.ui.ui.LoginActivity2;
import com.hoho.beike.utils.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URL;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 9:39 PM
 */
public class JianJieFragment extends Fragment {
    //    WebView webView;
    TextView tvNoteContent;
    CharSequence charSequence;
    CompanyBean companyBean;
    String id;

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jianjie, container, false);

//        webView = root.findViewById(R.id.content_web);

        tvNoteContent = root.findViewById(R.id.tv_note_content);
        ImageView imageView = root.findViewById(R.id.back_iv);

        getDetail(id);
        return root;
    }

    private void getDetail(String id) {

        LogUtils.e(id);
        String sToken = SPUtils.getInstance().getString("token");
        Request.Builder.create("http://39.101.181.123:8080/bk/rest/bkProgramController/" + id)
                .client(RConcise.inst().rClient(Const.REQ_CLIENT_KEY))
                .addHeader(HeaderField.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .addHeader("X-AUTH-TOKEN", sToken)
                .setActivity(getActivity())
                .respStrListener(new RespListener() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e(s);
                        companyBean = CompanyBean.objectFromData(s);
//                        contentTv.setText(companyBean.);
                        if (companyBean.ok) {
                            new Thread(networkTask2).start();
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

    Runnable networkTask2 = new Runnable() {

        @Override
        public void run() {
            charSequence = Html.fromHtml(companyBean.data.content, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    LogUtils.e(source);
                    Drawable d = null;
                    try {

                        URL url = new URL(source);
                        d = Drawable.createFromStream(url.openStream(), "");
                        d.setBounds(0, 0, tvNoteContent.getWidth(), tvNoteContent.getWidth());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return d;
                }
            }, null);

            handler.sendEmptyMessage(0);
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    tvNoteContent.setText(charSequence);
                    //该语句在设置后必加，不然没有任何效果
                    tvNoteContent.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
            }
        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAddrEvent(IdEvent event) {
        id = event.id;
        LogUtils.e(id);
        getDetail(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
