package com.example.libbase.base;

/**
 * 传递无法绑定数据的控件
 * @author ChenQiuE
 * Date：2019/3/11 13:39
 * Email：1077503420@qq.com
 */
public interface BaseDataInterface<T> {
    void setData(T data);
}
