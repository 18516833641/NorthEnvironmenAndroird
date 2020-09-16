package com.hoho.beike.listener;

import com.blankj.utilcode.util.ToastUtils;
import com.egbert.rconcise.listener.IRespListener;
import com.hoho.beike.R;

/**
 * @author Egbert
 * @date 8/15/2019
 */
public class RespListener implements IRespListener<String> {

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onError(Exception e, String desp) {
        ToastUtils.showShort(R.string.prompt_error_req);
    }

    @Override
    public void onFailure(int respCode, String desp) {
        ToastUtils.showShort(R.string.prompt_fail_req);
    }
}
