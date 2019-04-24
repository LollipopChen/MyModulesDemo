package com.example.libbase.preferences;

import com.example.libbase.console.LanguageConst;

/**
 * 语言
 * @author ChenQiuE
 * Date：2019/4/24 14:10
 * Email：qiue.chen@supernovachina.com
 */
public class LanguageManager {

    private static LanguageManager instance = null;

    public static synchronized LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    private String languageId;

    public String getLanguageId() {
        if (languageId == null){
            languageId = SNPreferenceManager.getDefault().getString(LanguageConst.SharePreferenceParam.LANGUAGE_ID,"");
        }
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
        SNPreferenceManager.getDefault().put(LanguageConst.SharePreferenceParam.LANGUAGE_ID,languageId);
    }

}
