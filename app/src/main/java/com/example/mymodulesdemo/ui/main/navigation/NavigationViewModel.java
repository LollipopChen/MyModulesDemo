package com.example.mymodulesdemo.ui.main.navigation;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;

/**
 * 体系
 * @author ChenQiuE
 * Date：2019/3/21 11:43
 * Email：1077503420@qq.com
 */
public class NavigationViewModel extends LoadingViewModel {

    public NavigationViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setTitleText(title);
    }

    public void getNavigationData() {
        HttpObserver observer = new HttpObserver() {
            @Override
            protected void onSuccess(Object response) {

            }

            @Override
            protected void onFailure(ApiException exception) {

            }
        };
    }
}
