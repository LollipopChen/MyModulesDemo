package com.example.api;

import com.example.libbase.net.http.retrofit.RetrofitClient;

/**
 * @author ChenQiuE
 * Date：2019/4/30 14:43
 * Email：qiue.chen@supernovachina.com
 */
public class ApiCenter {
    private static final String BASE_URL = "https://www.wanandroid.com/";

    public static String getServerUrl() {
        return BASE_URL;
    }

    public static Api getApi() {
        return RetrofitClient.get().retrofit().create(Api.class);
    }
}
