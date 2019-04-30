package com.example.api;

import com.example.register.RegisterLoginEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author ChenQiuE
 * Date：2019/4/30 14:44
 * Email：qiue.chen@supernovachina.com
 */
public interface Api {

    /**
     * 注册
     * @param params 参数
     * @return 用户数据
     */
    @POST("user/register")
    Observable<RegisterLoginEntity> register(@QueryMap Map<String,Object> params);

    /**
     * 登录
     * @param params 参数
     * @return 用户数据
     */
    @POST("user/login")
    Observable<RegisterLoginEntity> login(@QueryMap Map<String,Object> params);
}
