package com.example.libbase.base;

import android.support.annotation.NonNull;

/**
 * item viewModel
 * @author ChenQiuE
 * Date：2019/3/11 10:16
 * Email：1077503420@qq.com
 */
public class ItemViewModel<VM extends BaseViewModel>{
    protected VM viewModel;

    public ItemViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
