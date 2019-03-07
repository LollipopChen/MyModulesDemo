package com.example.libbase.base;

/**
 * @author ChenQiuE
 * Date：2019/3/1 15:00
 * Email：1077503420@qq.com
 */
public interface IBaseListener {

    /**
     * 初始化界面传递的参数
     */
    void initParams();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者监听
     */
    void initViewObservable();
}
