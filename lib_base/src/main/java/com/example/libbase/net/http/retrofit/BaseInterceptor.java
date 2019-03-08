/*
 * SuperNovaFramework
 * BaseInterceptor
 * Created by leo on 2018-06-15.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.retrofit;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * BaseInterceptor
 *
 * @author leo
 * @date 2018/6/15
 */
public class BaseInterceptor implements Interceptor {
    private Map<String, String> headers;

    public BaseInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(@Nullable Chain chain) throws IOException {
        if (chain == null) {
            return null;
        }
        Request.Builder builder = chain.request().newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                String header = headers.get(headerKey);
                builder.addHeader(headerKey, header == null ? "" : header).build();
            }
        }
        return chain.proceed(builder.build());
    }
}
