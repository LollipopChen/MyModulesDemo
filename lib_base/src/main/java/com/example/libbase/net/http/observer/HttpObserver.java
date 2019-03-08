/*
 * SuperNovaFramework
 * HttpObserver
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.observer;

import com.orhanobut.logger.Logger;
import com.supernova.snlibrary.console.SNHttpConstants;
import com.supernova.snlibrary.net.http.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * AbstractHttpObserver
 *
 * @author leo
 * @date 2017/12/22
 */

@SuppressWarnings("unchecked")
public abstract class HttpObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onComplete();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            Logger.wtf("Http request onFailure:" + exception.getMsg() + ",code=" + exception.getCode());
            onFailure(exception);
        } else {
            onFailure(new ApiException(e, SNHttpConstants.HTTP_UN_KNOWN_ERROR));
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 在子类中实现，请求成功后执行的方法。
     *
     * @param response response
     */
    protected abstract void onSuccess(T response);

    /**
     * 在子类中实现，请求失败后执行的方案。
     *
     * @param exception exception
     */
    protected abstract void onFailure(ApiException exception);
}
