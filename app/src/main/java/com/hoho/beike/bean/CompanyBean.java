package com.hoho.beike.bean;

import com.google.gson.Gson;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/19
 * Time: 9:43 AM
 */
public class CompanyBean {


    /**
     * respCode : 0
     * ok : true
     * message : 成功
     * data : {"updateName":"管理员","createDate":"2020-07-07 11:18:30","updateBy":"admin","thmb":"upload\\files\\20200816\\499e3e54a1ae4405e27fc6_1597553693396.jpg","updateDate":"2020-08-16 12:54:58","createName":"管理员","ord":"1","stus":"1","createBy":"admin","id":"4028808e732746810173274836370001","content":"<p><span style=\"color: rgb(85, 85, 85); font-family: &quot;Microsoft YaHei&quot;; font-size: 12px; background-color: rgb(255, 255, 255);\"><strong>欧盟CE认证是全球广泛认可的一种安全认证，即便不出口欧盟市场，也是值得中国制造商及代理商申请的一项产品增值服务。产品属于CE认证的指令范围<\/strong><\/span><\/p><p><span style=\"color: rgb(85, 85, 85); font-family: &quot;Microsoft YaHei&quot;; font-size: 12px; background-color: rgb(255, 255, 255);\"><\/span><\/p><p style=\"text-align: center\"><br/><\/p><p><span style=\"color: rgb(85, 85, 85); font-family: &quot;Microsoft YaHei&quot;; font-size: 12px; background-color: rgb(255, 255, 255);\"><\/span><\/p><p style=\"text-align: center\"><img src=\"http://39.101.181.123:8080/bk/plug-in/ueditor/jsp/upload1/20200816/31361597548825510.jpg\" title=\"7211b78ac16fe15ee6651e.jpg\"/><\/p><p><br/><\/p>","tile":"科技之光1"}
     */

    public String respCode;
    public boolean ok;
    public String message;
    public DataBean data;

    public static CompanyBean objectFromData(String str) {

        return new Gson().fromJson(str, CompanyBean.class);
    }

    public static class DataBean {
        /**
         * updateName : 管理员
         * createDate : 2020-07-07 11:18:30
         * updateBy : admin
         * thmb : upload\files\20200816\499e3e54a1ae4405e27fc6_1597553693396.jpg
         * updateDate : 2020-08-16 12:54:58
         * createName : 管理员
         * ord : 1
         * stus : 1
         * createBy : admin
         * id : 4028808e732746810173274836370001
         * content : <p><span style="color: rgb(85, 85, 85); font-family: &quot;Microsoft YaHei&quot;; font-size: 12px; background-color: rgb(255, 255, 255);"><strong>欧盟CE认证是全球广泛认可的一种安全认证，即便不出口欧盟市场，也是值得中国制造商及代理商申请的一项产品增值服务。产品属于CE认证的指令范围</strong></span></p><p><span style="color: rgb(85, 85, 85); font-family: &quot;Microsoft YaHei&quot;; font-size: 12px; background-color: rgb(255, 255, 255);"></span></p><p style="text-align: center"><br/></p><p><span style="color: rgb(85, 85, 85); font-family: &quot;Microsoft YaHei&quot;; font-size: 12px; background-color: rgb(255, 255, 255);"></span></p><p style="text-align: center"><img src="http://39.101.181.123:8080/bk/plug-in/ueditor/jsp/upload1/20200816/31361597548825510.jpg" title="7211b78ac16fe15ee6651e.jpg"/></p><p><br/></p>
         * tile : 科技之光1
         */

        public String updateName;
        public String createDate;
        public String updateBy;
        public String thmb;
        public String updateDate;
        public String createName;
        public String ord;
        public String stus;
        public String createBy;
        public String id;
        public String content;
        public String tile;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
