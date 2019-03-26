package com.example.mymodulesdemo.ui.main.navigation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.MotionEvent;
import android.view.View;

import com.example.mymodulesdemo.databinding.ItemNavigationBinding;
import com.example.mymodulesdemo.ui.main.navigation.viewmodel.NavigationItemViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * @author ChenQiuE
 * Date：2019/3/26 10:35
 * Email：qiue.chen@supernovachina.com
 */
public class NavigationItemAdapter extends BindingRecyclerViewAdapter<NavigationItemViewModel> {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, NavigationItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);

        ItemNavigationBinding iBinding = (ItemNavigationBinding)binding;

        Context context = iBinding.recyclerView.getContext();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        iBinding.recyclerView.setNestedScrollingEnabled(false);
        iBinding.recyclerView.setLayoutManager(flexboxLayoutManager);
        NavigationFlagAdapter navigationFlagAdapter = new NavigationFlagAdapter(iBinding.recyclerView.getContext());

        navigationFlagAdapter.getItems().addAll(item.dataList);

        iBinding.recyclerView.setAdapter(navigationFlagAdapter);

        /*
         *  屏蔽item中嵌套的RecycleView的点击事件
         */
        iBinding.recyclerView.setOnTouchListener((v, event) -> ((ItemNavigationBinding) binding).layoutParent.onTouchEvent(event));
    }
}
