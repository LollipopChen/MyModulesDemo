package com.example.mymodulesdemo.net;

import com.example.libbase.net.http.retrofit.RetrofitClient;

/**
 * @author ChenQiuE
 * Date：2019/3/8 11:58
 * Email：1077503420@qq.com
 */
public class ApiCenter {

    private static final String BASE_URL = "http://www.wanandroid.com/";

    public static String getServerUrl() {
        return BASE_URL;
    }

    public static Api getApi() {
        return RetrofitClient.get().retrofit().create(Api.class);
    }
}
