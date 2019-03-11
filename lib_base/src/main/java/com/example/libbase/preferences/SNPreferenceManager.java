package com.example.libbase.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.libbase.base.SnBaseApplication;

/**
 * SharedPreferences封装
 * 与PreferencesOperateObject配合封装SharedPreferences, 简化SharedPreferences存取操作
 */
public class SNPreferenceManager {
    private SNPreferenceManager() {
    }

    /**
     * 获取默认的SharedPreferences
     * 默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
     */
    public static SNPreferenceOperator getDefault(Context context) {
        SharedPreferences sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return new SNPreferenceOperator(sharedPreferences);
    }

    /**
     * 这里用到的反射，非不得尽量不用
     *
     * @return SNPreferenceOperator 对象
     */
    public static SNPreferenceOperator getDefault() {
        return getDefault(SnBaseApplication.getAppContext());
    }

    /**
     * 获取私有的SharedPreferences
     * Private模式代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
     */
    @Nullable
    public static SNPreferenceOperator getPrivate(Context context, String name) {
        return getPreferencesByMode(context, name, Context.MODE_PRIVATE);
    }

    public static SNPreferenceOperator getPrivate(String name) {
        return getPreferencesByMode(SnBaseApplication.getAppContext(), name, Context.MODE_PRIVATE);
    }

    /**
     * 获取Append模式的SharedPreferences
     * Append模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
     */
    @Nullable
    public static SNPreferenceOperator getAppend(Context context, String name) {
        return getPreferencesByMode(context, name, Context.MODE_APPEND);
    }

    public static SNPreferenceOperator getAppend(String name) {
        return getPreferencesByMode(SnBaseApplication.getAppContext(), name, Context.MODE_APPEND);
    }

    @Nullable
    private static SNPreferenceOperator getPreferencesByMode(Context context, String name, int mode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        if (sharedPreferences != null) {
            return new SNPreferenceOperator(sharedPreferences);
        }
        return null;
    }
}