package com.example.mymodulesdemo.ui.main.system.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.example.mymodulesdemo.databinding.ItemSystemBinding;
import com.example.mymodulesdemo.ui.main.system.viewmodel.SystemItemViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * @author ChenQiuE
 * Date：2019/3/21 16:05
 * Email：1077503420@qq.com
 */
public class SystemItemAdapter extends BindingRecyclerViewAdapter<SystemItemViewModel> {

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, SystemItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);

        ItemSystemBinding iBinding = ((ItemSystemBinding) binding);

        Context context = iBinding.recyclerView.getContext();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        iBinding.recyclerView.setNestedScrollingEnabled(false);
        iBinding.recyclerView.setLayoutManager(flexboxLayoutManager);
        SystemFlagAdapter systemFlagAdapter = new SystemFlagAdapter(iBinding.recyclerView.getContext());

        systemFlagAdapter.getItems().addAll(item.dataList);

        iBinding.recyclerView.setAdapter(systemFlagAdapter);
    }
}
