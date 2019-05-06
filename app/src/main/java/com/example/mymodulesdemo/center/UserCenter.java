package com.example.mymodulesdemo.center;

import com.example.libbase.preferences.SNPreferenceManager;
import com.example.mymodulesdemo.console.AppConst;

/**
 * 用户管理
 * @author ChenQiuE
 * Date：2019/4/2 11:40
 * Email：qiue.chen@supernovachina.com
 */
public class UserCenter {
    private static UserCenter instance = null;

    public static synchronized UserCenter getInstance() {
        if (instance == null) {
            instance = new UserCenter();
        }
        return instance;
    }

    private String userName;
    private String description;
    private String userAvatar;

    public String getUserName() {
        if (userName == null){
            userName = SNPreferenceManager.getDefault().getString(AppConst.SharePreferenceParams.USER_NAME,"");
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        SNPreferenceManager.getDefault().put(AppConst.SharePreferenceParams.USER_NAME,userName);
    }

    public String getDescription() {
        if (description == null){
            description = SNPreferenceManager.getDefault().getString(AppConst.SharePreferenceParams.DESCRIPTION,"");
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        SNPreferenceManager.getDefault().put(AppConst.SharePreferenceParams.DESCRIPTION,description);
    }

    public String getUserAvatar() {
        if (userAvatar == null){
            userAvatar = SNPreferenceManager.getDefault().getString(AppConst.SharePreferenceParams.USER_AVATAR,"");
        }
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        SNPreferenceManager.getDefault().put(AppConst.SharePreferenceParams.USER_AVATAR,userAvatar);
    }

    public void clear(){
        setUserAvatar(null);
        setDescription(null);
        setUserName(null);
    }
}
