/*
 * SuperNovaFramework
 * NullStringToEmptyAdapterFactory
 * Created by Leo.Mok on 2018-03-29.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * NullStringToEmptyAdapterFactory
 *
 * @author leo
 * @date 2018/1/30
 */

public class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType != String.class) {
            return null;
        }
        return (TypeAdapter<T>) new StringNullAdapter();
    }
}