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
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";

    private static LoginCenter instance = null;

    public static synchronized LoginCenter getInstance() {
        if (instance == null) {
            instance = new LoginCenter();
        }
        return instance;
    }

    private boolean isLogin;
    private String userName;
    private String password;

    public boolean isLogin() {
        isLogin = SNPreferenceManager.getDefault().getBoolean(LOGIN_STATUS,false);
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
        SNPreferenceManager.getDefault().put(LOGIN_STATUS,login);
    }

    public String getUserName() {
        if (userName == null){
            userName = SNPreferenceManager.getDefault().getString(USER_NAME,"");
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        SNPreferenceManager.getDefault().put(USER_NAME,userName);
    }

    public String getPassword() {
        if (password == null){
            password = SNPreferenceManager.getDefault().getString(PASSWORD,"");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        SNPreferenceManager.getDefault().put(PASSWORD,password);
    }
}
