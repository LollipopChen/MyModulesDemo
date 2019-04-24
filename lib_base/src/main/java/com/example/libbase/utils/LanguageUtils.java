package com.example.libbase.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import com.example.libbase.console.LanguageConst;
import com.example.libbase.preferences.LanguageManager;
import com.orhanobut.logger.Logger;

import java.util.Locale;

/**
 * 语言切换工具类
 * @author ChenQiuE
 * Date：2019/4/24 13:44
 * Email：qiue.chen@supernovachina.com
 */
public class LanguageUtils {

    public static void changeLanguage(Context context) {
        Configuration config = context.getResources().getConfiguration();
        switch (LanguageManager.getInstance().getLanguageId()) {
            case LanguageConst.ZH_RCN:
                config.setLocale(Locale.SIMPLIFIED_CHINESE);
                break;
            case LanguageConst.ENGLISH:
                config.setLocale(Locale.ENGLISH);
                break;
            case LanguageConst.ZH_RTW:
                config.setLocale(Locale.TAIWAN);
                break;
            default:
                //默认设置为跟随系统语言
                //获取当前系统语言
                String curLocaleLanguage = Locale.getDefault().getLanguage();
                String curLocaleCountry = Locale.getDefault().getCountry();

                Logger.e("语言：" + curLocaleLanguage);
                Logger.e("语言地区：" + curLocaleCountry);

                if (LanguageConst.LOCAL_LANGUAGE_EN.equals(curLocaleLanguage)) {
                    config.setLocale(Locale.ENGLISH);
                    LanguageManager.getInstance().setLanguageId(LanguageConst.ENGLISH);
                } else if (LanguageConst.LOCAL_LANGUAGE_ZH.equals(curLocaleLanguage) && LanguageConst.LOCAL_COUNTRY_CN.equals(curLocaleCountry)) {
                    config.setLocale(Locale.SIMPLIFIED_CHINESE);
                    LanguageManager.getInstance().setLanguageId(LanguageConst.ZH_RCN);
                } else {
                    config.setLocale(Locale.TAIWAN);
                    LanguageManager.getInstance().setLanguageId(LanguageConst.ZH_RTW);
                }
                break;
        }
        // 8.0及以上使用createConfigurationContext设置configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.createConfigurationContext(config);
        } else {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(config, dm);
        }
    }

    public static Locale getLocale() {
        Locale locale;
        switch (LanguageManager.getInstance().getLanguageId()) {
            case LanguageConst.ZH_RCN:
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case LanguageConst.ENGLISH:
                locale = Locale.ENGLISH;
                break;
            case LanguageConst.ZH_RTW:
                locale = Locale.TAIWAN;
                break;
            default:
                //默认设置为跟随系统语言
                //获取当前系统语言
                String curLocaleLanguage = Locale.getDefault().getLanguage();
                String curLocaleCountry = Locale.getDefault().getCountry();

                Logger.e("语言：" + curLocaleLanguage);
                Logger.e("语言地区：" + curLocaleCountry);

                if (LanguageConst.LOCAL_LANGUAGE_EN.equals(curLocaleLanguage)) {
                    locale = Locale.ENGLISH;
                } else if (LanguageConst.LOCAL_LANGUAGE_ZH.equals(curLocaleLanguage) && LanguageConst.LOCAL_COUNTRY_CN.equals(curLocaleCountry)) {
                    locale = Locale.SIMPLIFIED_CHINESE;
                } else {
                    locale = Locale.TAIWAN;
                }
                break;
        }
        return locale;
    }

}
