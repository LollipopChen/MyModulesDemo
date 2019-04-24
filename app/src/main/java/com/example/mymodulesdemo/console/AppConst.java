package com.example.mymodulesdemo.console;

/**
 * app基本参数
 * @author ChenQiuE
 * Date：2019/3/12 10:11
 * Email：1077503420@qq.com
 */
public interface AppConst {
    interface IntentParams{
        String URL = "url";
        String ID = "id";
        String KEY_WORD = "key_word";
        String NAVIGATION = "navigation";
        String NAVIGATION_TAB = "navigation_tab";
    }

    interface StatusParams{
        String LOAD_OVER = "false";
    }

    interface SharePreferenceParams{
        String USER_NAME = "user_name";
        String DESCRIPTION = "description";
        String USER_AVATAR = "user_avatar";
    }

}
