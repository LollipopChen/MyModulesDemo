package com.example.libbase.event;

/**
 * 刷新数据使用
 * @author ChenQiuE
 * Date：2019/4/2 10:47
 * Email：qiue.chen@supernovachina.com
 */
public class BaseRefreshDataEvent {
    /**
     * 刷新的类型
     */
    private int type;

    public BaseRefreshDataEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
