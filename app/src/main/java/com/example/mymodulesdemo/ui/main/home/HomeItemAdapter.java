package com.example.mymodulesdemo.ui.main.home;

import android.databinding.ViewDataBinding;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * @author ChenQiuE
 * Date：2019/3/11 16:57
 * Email：1077503420@qq.com
 */
public class HomeItemAdapter<HomeItemViewModel> extends BindingRecyclerViewAdapter{

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, Object item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
    }
}
