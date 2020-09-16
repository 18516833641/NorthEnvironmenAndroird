package com.hoho.beike.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/24
 * Time: 8:50 PM
 */
public class GuZhangBean implements Serializable {


    /**
     * ok : true
     * respCode : 0
     * message : 成功
     * data : [{"itemid":"TJRC04_02","itemnm":"脱硫塔入口温度","alertvalue":"156.5","create_date":"2020-08-22 23:51:36.0000000","normlvalue":"140","update_date":"2020-08-23 00:00:46.0000000"}]
     */

    public boolean ok;
    public String respCode;
    public String message;
    public List<DataBean> data;

    public static GuZhangBean objectFromData(String str) {

        return new Gson().fromJson(str, GuZhangBean.class);
    }

    public static class DataBean implements Serializable {
        /**
         * itemid : TJRC04_02
         * itemnm : 脱硫塔入口温度
         * alertvalue : 156.5
         * create_date : 2020-08-22 23:51:36.0000000
         * normlvalue : 140
         * update_date : 2020-08-23 00:00:46.0000000
         */

        public String itemid;
        public String itemnm;
        public String alertvalue;
        public String create_date;
        public String normlvalue;
        public String update_date;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
