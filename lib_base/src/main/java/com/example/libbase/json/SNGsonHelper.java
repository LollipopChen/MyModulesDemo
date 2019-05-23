/*
 * SuperNovaFramework
 * SNGsonHelper
 * Created by Leo.Mok on 2018-03-29.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.json;

import android.support.annotation.NonNull;

import com.example.libbase.utils.SNStringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

/**
 * json公共解析库
 *
 * @author xiewenyong
 * @date 2019-2-11 17:13:59
 */
public class SNGsonHelper {
    private static Gson gson;

    private static void initGson() {
        JsonSerializer<Double> doubleJsonSerializer = (src, typeOfSrc, context) -> {
            if (src == src.longValue()) {
                return new JsonPrimitive(src.longValue());
            }
            return new JsonPrimitive(src);
        };

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(Double.class, doubleJsonSerializer)
                .create();
    }

    /**
     * 把json String 转化成类对象
     */
    public static <T> T toType(String str, Class<T> t) {
        if (gson == null) {
            initGson();
        }
        try {
            if (!SNStringUtils.isEmpty(str)) {
                return gson.fromJson(str.trim(), t);
            }
        } catch (Exception e) {
            Logger.e("exception:" + e.getMessage());

        }
        return null;
    }

    /**
     * 把类对象转化成json string
     */
    public static <T> String toJson(T t) {
        if (gson == null) {
            initGson();
        }
        return gson.toJson(t);
    }

    public static <T> Map<String, T> toMap(@NonNull String json) {
        if (gson == null) {
            initGson();
        }
        try {
            if (!SNStringUtils.isEmpty(json)) {
                return gson.fromJson(json, new TypeToken<Map<String, T>>() {
                }.getType());
            }
        } catch (Exception e) {
            Logger.e("exception:" + e.getMessage());
        }
        return null;
    }

    public static <T> List<T> toList(@NonNull String json, @NonNull TypeToken typeToken) {
        if (gson == null) {
            initGson();
        }
        try {
            if (!SNStringUtils.isEmpty(json)) {
                return gson.fromJson(json, typeToken.getType());
            }
        } catch (Exception e) {
            Logger.e("exception:" + e.getMessage());
        }
        return null;
    }
}
