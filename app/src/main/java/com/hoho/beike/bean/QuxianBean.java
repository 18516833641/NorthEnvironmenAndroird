package com.hoho.beike.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/22
 * Time: 3:58 PM
 */
public class QuxianBean {


    /**
     * respCode : 0
     * ok : true
     * message : 成功
     * data : [{"dt":"2020-08-22","TJRC04_24":7.3},{"dt":"2020-08-22 01:00","TJRC04_24":6.86},{"dt":"2020-08-22 02:00","TJRC04_24":7.64},{"dt":"2020-08-22 03:00","TJRC04_24":6.77},{"dt":"2020-08-22 04:00","TJRC04_24":7.5},{"dt":"2020-08-22 05:00","TJRC04_24":6.8100000000000005},{"dt":"2020-08-22 06:00","TJRC04_24":7.57},{"dt":"2020-08-22 07:00","TJRC04_24":7.12},{"dt":"2020-08-22 08:00","TJRC04_24":5.03},{"dt":"2020-08-22 09:00","TJRC04_24":6.15},{"dt":"2020-08-22 10:00","TJRC04_24":6.5600000000000005},{"dt":"2020-08-22 11:00","TJRC04_24":6.37},{"dt":"2020-08-22 12:00","TJRC04_24":6.95},{"dt":"2020-08-22 13:00","TJRC04_24":4.84},{"dt":"2020-08-22 14:00","TJRC04_24":7.47},{"dt":"2020-08-22 15:00","TJRC04_24":7.05},{"dt":"2020-08-22 16:00","TJRC04_24":4.95}]
     */

    public String respCode;
    public boolean ok;
    public String message;
    public List<DataBean> data;

    public static QuxianBean objectFromData(String str) {

        return new Gson().fromJson(str, QuxianBean.class);
    }

    public static class DataBean {
        /**
         * dt : 2020-08-22
         * TJRC04_24 : 7.3
         */

        public String dt;
        public float tjrc;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
