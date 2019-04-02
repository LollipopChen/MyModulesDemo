package com.example.login;

import android.content.Context;

/**
 * 登录
 * @author ChenQiuE
 * Date：2019/4/1 14:33
 * Email：qiue.chen@supernovachina.com
 */
public interface ILoginService {

    /**
     * 是否已登录
     * @return boolean
     */
    boolean isLogin();

    /**
     * 开始登录
     * @param context context
     */
    void startLogin(Context context);

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    /**
     * 登录成功
     */
    void notifyLoginSuccess();

    /**
     * 取消登录
     */
    void notifyLoginCancel();

    /**
     * 登录失败
     */
    void notifyLoginFailure();

    /**
     * 退出登录
     */
    void notifyLogout();

    interface Observer {
        /**
         * 登录成功
         */
        void onLoginSuccess();
        /**
         * 取消登录
         */
        void onLoginCancel();
        /**
         * 登录失败
         */
        void onLoginFailure();
        /**
         * 退出登录
         */
        void onLogout();
    }
}
