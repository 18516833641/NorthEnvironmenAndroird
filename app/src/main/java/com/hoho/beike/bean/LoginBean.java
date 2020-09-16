package com.hoho.beike.bean;

/**
 * Description:
 * Created by Android Studio.
 * User: houjianjiang
 * Date: 2020/8/18
 * Time: 5:47 PM
 */
public class LoginBean {

    /**
     * success : true
     * jsonStr : {"attributes":{"userNm":"inter","id":"402881fc60a1cbe40160a1f080620011","token":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpbnRlciIsInN1YiI6ImludGVyIiwiaWF0IjoxNTk3NzQzMjU4fQ.xjoD8QB7XsN8atyqoNw-cVJJ4pVEWU7YSMkWKlLbzHY"},"msg":"","success":true}
     * msg :
     * attributes : {"userNm":"inter","id":"402881fc60a1cbe40160a1f080620011","token":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpbnRlciIsInN1YiI6ImludGVyIiwiaWF0IjoxNTk3NzQzMjU4fQ.xjoD8QB7XsN8atyqoNw-cVJJ4pVEWU7YSMkWKlLbzHY"}
     * obj : null
     */

    public boolean success;
    public String jsonStr;
    public String msg;
    public AttributesBean attributes;
    public Object obj;

    public static LoginBean objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, LoginBean.class);
    }

    public static class AttributesBean {
        /**
         * userNm : inter
         * id : 402881fc60a1cbe40160a1f080620011
         * token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpbnRlciIsInN1YiI6ImludGVyIiwiaWF0IjoxNTk3NzQzMjU4fQ.xjoD8QB7XsN8atyqoNw-cVJJ4pVEWU7YSMkWKlLbzHY
         */

        public String userNm;
        public String id;
        public String token;
        public String phn;

        public static AttributesBean objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, AttributesBean.class);
        }
    }
}
