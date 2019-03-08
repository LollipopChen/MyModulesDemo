/*
 * SuperNovaFramework
 * HttpObservable
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.observer;

import com.supernova.snlibrary.net.http.rxfunction.HttpResultFunction;
import com.supernova.snlibrary.net.http.rxfunction.ServerResultFunction;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * HttpObservable
 *
 * @author leo
 * @date 2017/12/22
 */

@SuppressWarnings("unchecked")
public class HttpObservable {
    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 无管理生命周期，容易导致内存溢出
     *
     * @param apiObservable apiObservable
     * @return observable
     */
    public static Observable getObservable(Observable apiObservable) {
        return apiObservable
                .map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction<>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 传入LifecycleProvider自动管理生命周期，避免内存溢出
     * 备注：需要继承RxActivity/RxFragment
     *
     * @param apiObservable apiObservable
     * @param lifecycle     lifecycle
     * @return observable
     */
    public static Observable getObservable(Observable apiObservable,
                                           LifecycleProvider lifecycle) {
        Observable observable;
        if (lifecycle != null) {
            //随生命周期自动管理.eg:onCreate(start)->onStop(end)
            observable = apiObservable
                    .map(new ServerResultFunction())
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .compose(lifecycle.bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 传入LifecycleProvider<ActivityEvent>手动管理生命周期，避免内存溢出
     * 备注：需要继承RxActivity/RxAppCompatActivity/RxFragmentActivity
     *
     * @param apiObservable apiObservable
     * @param lifecycle     lifecycle
     * @param event         event
     * @return observable
     */
    public static Observable getObservable(Observable apiObservable,
                                           LifecycleProvider<ActivityEvent> lifecycle,
                                           ActivityEvent event) {
        Observable observable;
        if (lifecycle != null) {
            //手动管理移除监听生命周期.eg:ActivityEvent.STOP
            observable = apiObservable
                    .map(new ServerResultFunction())
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .compose(lifecycle.bindUntilEvent(event))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 传入LifecycleProvider<FragmentEvent>手动管理生命周期，避免内存溢出
     * 备注：需要继承RxFragment/RxDialogFragment
     *
     * @param apiObservable apiObservable
     * @param lifecycle     lifecycle
     * @param event         event
     * @return observable
     */
    public static Observable getObservable(Observable apiObservable,
                                           LifecycleProvider<FragmentEvent> lifecycle,
                                           FragmentEvent event) {
        Observable observable;
        if (lifecycle != null) {
            //手动管理移除监听生命周期.eg:FragmentEvent.STOP
            observable = apiObservable
                    .map(new ServerResultFunction())
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .compose(lifecycle.bindUntilEvent(event))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }

        return observable;
    }
}
