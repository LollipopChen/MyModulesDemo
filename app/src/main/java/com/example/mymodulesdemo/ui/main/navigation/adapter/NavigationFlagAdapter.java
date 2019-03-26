package com.example.mymodulesdemo.ui.main.navigation.adapter;

import android.content.Context;

import com.example.libbase.base.BaseBindingAdapter;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ItemNavigationFlagBinding;
import com.example.mymodulesdemo.entity.NavigationEntity;

/**
 * @author ChenQiuE
 * Date：2019/3/26 10:37
 * Email：qiue.chen@supernovachina.com
 */
public class NavigationFlagAdapter extends BaseBindingAdapter<NavigationEntity.ItemEntity, ItemNavigationFlagBinding> {

    public NavigationFlagAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_navigation_flag;
    }

    @Override
    protected void onBindItem(ItemNavigationFlagBinding binding, NavigationEntity.ItemEntity item) {
        binding.setEntity(item);
    }
}
