package com.hoho.beike.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/23
 * Time: 6:00 PM
 */
public class GongYiBean implements Serializable {


    /**
     * ok : true
     * respCode : 0
     * message : 成功
     * data : {"ID":184724,"DT":"2020-08-23 17:59:52","TJRC04_01":481.94,"TJRC04_02":135.5,"TJRC04_03":130.9,"TJRC04_04":119.5,"TJRC04_05":19.39,"TJRC04_06":36.37,"TJRC04_07":34.01,"TJRC04_08":-2.3,"TJRC04_09":-2.63,"TJRC04_10":-2.5,"TJRC04_11":-1.97,"TJRC04_12":-2.22,"TJRC04_13":27.31,"TJRC04_14":29.34,"TJRC04_15":5.99,"TJRC04_16":0,"TJRC04_17":0,"TJRC04_18":0.29,"TJRC04_19":0.01,"TJRC04_20":0.13,"TJRC04_21":13.96,"TJRC04_22":0.52,"TJRC04_23":0.3,"TJRC04_24":4.85,"TJRC04_25":76.66,"TJRC04_26":8.14,"TJRC04_27":3.39,"TJRC04_28":17.92,"TJRC04_29":0.6,"TJRC04_30":10000,"TJRC04_31":483.31,"TJRC04_32":48.5,"TJRC04_33":34.5,"TJRC04_34":175.69,"TJRC04_35":98.12,"TJRC04_36":43.1,"TJRC04_37":46.9,"TJRC04_38 ":1.02,"TJRC04_39":true,"TJRC04_40":false,"TJRC04_41":true,"TJRC04_42":true,"TJRC04_43":false,"TJRC04_44":false,"TJRC04_45":false,"TJRC04_46":true,"TJRC04_47":false,"TJRC04_48":true,"TJRC04_49":false,"TJRC04_50":false,"TJRC04_51":true,"TJRC04_52":0.33,"TJRC04_53":-0.13,"TJRC04_54":0.62,"TJRC04_55":0.61,"TJRC04_56":132.01}
     */

    public boolean ok;
    public String respCode;
    public String message;
    public DataBean data;

    public static GongYiBean objectFromData(String str) {

        return new Gson().fromJson(str, GongYiBean.class);
    }

    public static class DataBean implements Serializable{
        /**
         * ID : 184724
         * DT : 2020-08-23 17:59:52
         * TJRC04_01 : 481.94
         * TJRC04_02 : 135.5
         * TJRC04_03 : 130.9
         * TJRC04_04 : 119.5
         * TJRC04_05 : 19.39
         * TJRC04_06 : 36.37
         * TJRC04_07 : 34.01
         * TJRC04_08 : -2.3
         * TJRC04_09 : -2.63
         * TJRC04_10 : -2.5
         * TJRC04_11 : -1.97
         * TJRC04_12 : -2.22
         * TJRC04_13 : 27.31
         * TJRC04_14 : 29.34
         * TJRC04_15 : 5.99
         * TJRC04_16 : 0.0
         * TJRC04_17 : 0.0
         * TJRC04_18 : 0.29
         * TJRC04_19 : 0.01
         * TJRC04_20 : 0.13
         * TJRC04_21 : 13.96
         * TJRC04_22 : 0.52
         * TJRC04_23 : 0.3
         * TJRC04_24 : 4.85
         * TJRC04_25 : 76.66
         * TJRC04_26 : 8.14
         * TJRC04_27 : 3.39
         * TJRC04_28 : 17.92
         * TJRC04_29 : 0.6
         * TJRC04_30 : 10000.0
         * TJRC04_31 : 483.31
         * TJRC04_32 : 48.5
         * TJRC04_33 : 34.5
         * TJRC04_34 : 175.69
         * TJRC04_35 : 98.12
         * TJRC04_36 : 43.1
         * TJRC04_37 : 46.9
         * TJRC04_38  : 1.02
         * TJRC04_39 : true
         * TJRC04_40 : false
         * TJRC04_41 : true
         * TJRC04_42 : true
         * TJRC04_43 : false
         * TJRC04_44 : false
         * TJRC04_45 : false
         * TJRC04_46 : true
         * TJRC04_47 : false
         * TJRC04_48 : true
         * TJRC04_49 : false
         * TJRC04_50 : false
         * TJRC04_51 : true
         * TJRC04_52 : 0.33
         * TJRC04_53 : -0.13
         * TJRC04_54 : 0.62
         * TJRC04_55 : 0.61
         * TJRC04_56 : 132.01
         */

        public int ID;
        public String DT;
        public String TJRC04_01;
        public String TJRC04_02;
        public String TJRC04_03;
        public String TJRC04_04;
        public String TJRC04_05;
        public String TJRC04_06;
        public String TJRC04_07;
        public String TJRC04_08;
        public String TJRC04_09;
        public String TJRC04_10;
        public String TJRC04_11;
        public String TJRC04_12;
        public String TJRC04_13;
        public String TJRC04_14;
        public String TJRC04_15;
        public String TJRC04_16;
        public String TJRC04_17;
        public String TJRC04_18;
        public String TJRC04_19;
        public String TJRC04_20;
        public String TJRC04_21;
        public String TJRC04_22;
        public String TJRC04_23;
        public String TJRC04_24;
        public String TJRC04_25;
        public String TJRC04_26;
        public String TJRC04_27;
        public String TJRC04_28;
        public String TJRC04_29;
        public String TJRC04_30;
        public String TJRC04_31;
        public String TJRC04_32;
        public String TJRC04_33;
        public String TJRC04_34;
        public String TJRC04_35;
        public String TJRC04_36;
        public String TJRC04_37;
        public String TJRC04_38;
        public String TJRC04_39;
        public String TJRC04_40;
        public String TJRC04_41;
        public String TJRC04_42;
        public String TJRC04_43;
        public String TJRC04_44;
        public String TJRC04_45;
        public String TJRC04_46;
        public String TJRC04_47;
        public String TJRC04_48;
        public String TJRC04_49;
        public String TJRC04_50;
        public String TJRC04_51;
        public String TJRC04_52;
        public String TJRC04_53;
        public String TJRC04_54;
        public String TJRC04_55;
        public String TJRC04_56;

        public String TJRC05_01;
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
        public String TJRC05_47;
        public String TJRC05_48;
        public String TJRC05_49;
        public String TJRC05_50;
        public String TJRC05_51;
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
