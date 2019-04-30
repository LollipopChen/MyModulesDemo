package com.example.libbase.event;

/**
 * 传递数据Event
 * @author ChenQiuE
 * Date：2019/4/30 16:48
 * Email：qiue.chen@supernovachina.com
 */
public class BaseEntityEvent<T> {

    private T entity;

    public BaseEntityEvent(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }
}
