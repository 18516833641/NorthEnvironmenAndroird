package com.hoho.beike.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;
import com.hoho.beike.R;
import com.hoho.beike.enent.LogoutEvent;
import com.hoho.beike.enent.UpEvent;
import com.hoho.beike.utils.Const;
import com.umeng.commonsdk.debug.E;

import org.greenrobot.eventbus.EventBus;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 1:49 PM
 */
public class BottomDialog extends BottomDialogBase {

    int type;
    Context context;

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BottomDialog(Context context, int type) {
        super(context);
        this.context = context;
        this.type = type;
    }

    @Override
    protected void onCreate() {
        setContentView(R.layout.dialog_bottom_demo);

        TextView textView = (TextView)findViewById(R.id.tt);
        findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1){
                    textView.setText(getTitle());
                    EventBus.getDefault().post(new UpEvent());
                } else if (type == 2) {//退出登录
                    textView.setText(getTitle());
                    SPUtils.getInstance().clear();
                    EventBus.getDefault().post(new LogoutEvent());
                }
                dismiss();
            }
        });

        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
