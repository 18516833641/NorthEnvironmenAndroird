package com.hoho.beike.bean;

import com.google.gson.Gson;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/23
 * Time: 5:36 PM
 */
public class ChangePsdBean {


    /**
     * success : true
     * jsonStr : {"msg":"修改成功","success":true}
     * msg : 修改成功
     * attributes : null
     * obj : null
     */

    public boolean success;
    public String jsonStr;
    public String msg;
    public Object attributes;
    public Object obj;

    public static ChangePsdBean objectFromData(String str) {

        return new Gson().fromJson(str, ChangePsdBean.class);
    }
}
