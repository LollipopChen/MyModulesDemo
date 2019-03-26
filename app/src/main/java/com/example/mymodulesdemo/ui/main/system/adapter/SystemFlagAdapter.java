package com.example.mymodulesdemo.ui.main.system.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.libbase.base.BaseBindingAdapter;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.ItemSystemFlagBinding;
import com.example.mymodulesdemo.entity.SystemEntity;
import com.example.mymodulesdemo.ui.web.WebViewActivity;

import java.util.Objects;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * @author ChenQiuE
 * Date：2019/3/21 16:21
 * Email：1077503420@qq.com
 */
public class SystemFlagAdapter extends BaseBindingAdapter<SystemEntity.DataItemEntity, ItemSystemFlagBinding> {

    public SystemFlagAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_system_flag;
    }

    @Override
    protected void onBindItem(ItemSystemFlagBinding binding, SystemEntity.DataItemEntity item) {
        binding.setEntity(item);

        binding.textView.setOnClickListener(v -> {
            //跳转到指定界面,传入条目的参数
            Intent intent = new Intent(binding.textView.getContext(),WebViewActivity.class);
            intent.putExtra(AppConst.IntentParams.URL, item.getLink());
            binding.textView.getContext().startActivity(intent);
        });
    }
}
