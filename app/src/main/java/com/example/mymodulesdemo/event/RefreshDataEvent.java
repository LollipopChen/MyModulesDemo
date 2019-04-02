package com.example.mymodulesdemo.event;

/**
 * 刷新数据使用
 * @author ChenQiuE
 * Date：2019/4/2 10:47
 * Email：qiue.chen@supernovachina.com
 */
public class RefreshDataEvent {
    /**
     * 刷新的类型
     */
    private int type;

    public RefreshDataEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
