package com.hoho.beike.bean;

import com.google.gson.Gson;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/29
 * Time: 7:12 PM
 */
public class Asas {


    /**
     * ok : true
     * respCode : 0
     * message : 成功
     * data : {"TJRC05_31":"0.82 m3/h","TJRC05_02":"131.42 ℃","TJRC05_03":"125.84 ℃","TJRC05_09":"121.37 ℃","TJRC05_39":"84.61","TJRC05_13":"39.95 A","TJRC05_14":"34.3 Hz","TJRC05_04":"-0.51 kPa","TJRC05_05":"-0.9 kPa","TJRC05_10":"-1.52 kPa","TJRC05_11":"-0.21 kPa","TJRC05_12":"-0.29 kPa","TJRC05_06":"31.02 kPa","TJRC05_07":"16.32 kPa","TJRC05_08":"5.03 kPa","TJRC05_28":"205440 mg/Nm3","TJRC05_35":"530.96 kPa","TJRC05_36":"269.68 kPa","TJRC05_32":"0 m3/h","TJRC05_33":"0 m3/h","TJRC05_29":"3.8 mg/Nm3","TJRC05_30":"0.01 t","TJRC05_40":"810.19","TJRC05_24":"480.9 kPa","TJRC05_25":"99.02","TJRC05_26":"43.09 ℃","TJRC05_27":"44.53 ℃","TJRC05_37":"17.36 m3/h","TJRC05_38":"73.78 m3/h","TJRC05_22":"42.89 ℃","TJRC05_23":"40.6 ℃","TJRC05_34":"0.98 m","TJRC05_15":"1","TJRC05_16":"0","TJRC05_17":"1","TJRC05_18":"1","TJRC05_19":"0","TJRC05_20":"0","TJRC05_21":"0","TJRC05_41":"1","TJRC05_42":"0","TJRC05_43":"1","TJRC05_44":"0","TJRC05_45":"0","TJRC05_46":"1","TJRC05_52":"0.39 kPa","TJRC05_53":"0.63 kPa","TJRC05_54":"4.58 mg/Nm3","TJRC05_55":"5.23 mg/Nm3","TJRC05_56":"29.34 mg/Nm3"}
     */

    public boolean ok;
    public String respCode;
    public String message;
    public DataBean data;

    public static Asas objectFromData(String str) {

        return new Gson().fromJson(str, Asas.class);
    }

    public static class DataBean {
        /**
         * TJRC05_31 : 0.82 m3/h
         * TJRC05_02 : 131.42 ℃
         * TJRC05_03 : 125.84 ℃
         * TJRC05_09 : 121.37 ℃
         * TJRC05_39 : 84.61
         * TJRC05_13 : 39.95 A
         * TJRC05_14 : 34.3 Hz
         * TJRC05_04 : -0.51 kPa
         * TJRC05_05 : -0.9 kPa
         * TJRC05_10 : -1.52 kPa
         * TJRC05_11 : -0.21 kPa
         * TJRC05_12 : -0.29 kPa
         * TJRC05_06 : 31.02 kPa
         * TJRC05_07 : 16.32 kPa
         * TJRC05_08 : 5.03 kPa
         * TJRC05_28 : 205440 mg/Nm3
         * TJRC05_35 : 530.96 kPa
         * TJRC05_36 : 269.68 kPa
         * TJRC05_32 : 0 m3/h
         * TJRC05_33 : 0 m3/h
         * TJRC05_29 : 3.8 mg/Nm3
         * TJRC05_30 : 0.01 t
         * TJRC05_40 : 810.19
         * TJRC05_24 : 480.9 kPa
         * TJRC05_25 : 99.02
         * TJRC05_26 : 43.09 ℃
         * TJRC05_27 : 44.53 ℃
         * TJRC05_37 : 17.36 m3/h
         * TJRC05_38 : 73.78 m3/h
         * TJRC05_22 : 42.89 ℃
         * TJRC05_23 : 40.6 ℃
         * TJRC05_34 : 0.98 m
         * TJRC05_15 : 1
         * TJRC05_16 : 0
         * TJRC05_17 : 1
         * TJRC05_18 : 1
         * TJRC05_19 : 0
         * TJRC05_20 : 0
         * TJRC05_21 : 0
         * TJRC05_41 : 1
         * TJRC05_42 : 0
         * TJRC05_43 : 1
         * TJRC05_44 : 0
         * TJRC05_45 : 0
         * TJRC05_46 : 1
         * TJRC05_52 : 0.39 kPa
         * TJRC05_53 : 0.63 kPa
         * TJRC05_54 : 4.58 mg/Nm3
         * TJRC05_55 : 5.23 mg/Nm3
         * TJRC05_56 : 29.34 mg/Nm3
         */

        public String TJRC05_31;
        public String TJRC05_02;
        public String TJRC05_03;
        public String TJRC05_09;
        public String TJRC05_39;
        public String TJRC05_13;
        public String TJRC05_14;
        public String TJRC05_04;
        public String TJRC05_05;
        public String TJRC05_10;
        public String TJRC05_11;
        public String TJRC05_12;
        public String TJRC05_06;
        public String TJRC05_07;
        public String TJRC05_08;
        public String TJRC05_28;
        public String TJRC05_35;
        public String TJRC05_36;
        public String TJRC05_32;
        public String TJRC05_33;
        public String TJRC05_29;
        public String TJRC05_30;
        public String TJRC05_40;
        public String TJRC05_24;
        public String TJRC05_25;
        public String TJRC05_26;
        public String TJRC05_27;
        public String TJRC05_37;
        public String TJRC05_38;
        public String TJRC05_22;
        public String TJRC05_23;
        public String TJRC05_34;
        public String TJRC05_15;
        public String TJRC05_16;
        public String TJRC05_17;
        public String TJRC05_18;
        public String TJRC05_19;
        public String TJRC05_20;
        public String TJRC05_21;
        public String TJRC05_41;
        public String TJRC05_42;
        public String TJRC05_43;
        public String TJRC05_44;
        public String TJRC05_45;
        public String TJRC05_46;
        public String TJRC05_52;
        public String TJRC05_53;
        public String TJRC05_54;
        public String TJRC05_55;
        public String TJRC05_56;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
