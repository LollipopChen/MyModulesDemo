package com.example.login;

import com.example.libbase.preferences.SNPreferenceManager;

/**
 * 登录管理
 * @author ChenQiuE
 * Date：2019/4/2 12:07
 * Email：qiue.chen@supernovachina.com
 */
public class LoginCenter {
    public static final String LOGIN_STATUS = "login_status";

    private static LoginCenter instance = null;

    public static synchronized LoginCenter getInstance() {
        if (instance == null) {
            instance = new LoginCenter();
        }
        return instance;
    }

    private boolean isLogin;

    public boolean isLogin() {
        isLogin = SNPreferenceManager.getDefault().getBoolean(LOGIN_STATUS,false);
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
        SNPreferenceManager.getDefault().put(LOGIN_STATUS,login);
    }
}
