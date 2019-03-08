/*
 * SuperNovaFramework
 * HttpResultFunction
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.rxfunction;

import com.orhanobut.logger.Logger;
import com.supernova.snlibrary.json.SNGsonHelper;
import com.supernova.snlibrary.net.http.exception.ExceptionEngine;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * http错误结果处理函数
 *
 * @author leo
 * @date 2018/01/31
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        Logger.wtf("HttpResultFunction:" + SNGsonHelper.toJson(throwable));
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
