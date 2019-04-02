package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.annotation.RouterProvider;
import com.sankuai.waimai.router.annotation.RouterService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/4/1 17:08
 * Email：qiue.chen@supernovachina.com
 */

@RouterService(interfaces = ILoginService.class, key = LoginConstant.ServiceConstant.SERVICE_LOGIN, singleton = true)
public class FakeLoginServiceImpl implements ILoginService {

    private final List<Observer> mObservers = new ArrayList<>();

    @RouterProvider
    public static FakeLoginServiceImpl getInstance() {
        return new FakeLoginServiceImpl(Router.getService(Context.class, "/application"));
    }

    private FakeLoginServiceImpl(Context context) {
        // ...
    }

    @Override
    public boolean isLogin() {
        return LoginCenter.getInstance().isLogin();
    }

    @Override
    public void startLogin(Context context) {
        Router.startUri(context, LoginConstant.UiConstant.LOGIN);
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !mObservers.contains(observer)){
            mObservers.add(observer);
        }
    }

    @Override
    public void unregisterObserver(Observer observer) {
        if (observer != null){
            mObservers.remove(observer);
        }
    }

    @Override
    public void notifyLoginSuccess() {
        Observer[] observers = getObservers();
        for (int i = observers.length - 1; i >= 0; --i) {
            observers[i].onLoginSuccess();
        }
    }

    @Override
    public void notifyLoginCancel() {
        Observer[] observers = getObservers();
        for (int i = observers.length - 1; i >= 0; --i) {
            observers[i].onLoginCancel();
        }
    }

    @Override
    public void notifyLoginFailure() {
        Observer[] observers = getObservers();
        for (int i = observers.length - 1; i >= 0; --i) {
            observers[i].onLoginFailure();
        }
    }

    @Override
    public void notifyLogout() {
        Observer[] observers = getObservers();
        for (int i = observers.length - 1; i >= 0; --i) {
            observers[i].onLogout();
        }
    }

    @NonNull
    private Observer[] getObservers() {
        return mObservers.toArray(new Observer[mObservers.size()]);
    }
}
