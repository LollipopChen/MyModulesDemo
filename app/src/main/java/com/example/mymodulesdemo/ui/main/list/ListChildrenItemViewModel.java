package com.example.mymodulesdemo.ui.main.list;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.ListDataEntity;
import com.example.mymodulesdemo.ui.web.WebViewActivity;

import java.util.Objects;

/**
 * @author ChenQiuE
 * Date：2019/3/13 17:25
 * Email：1077503420@qq.com
 */
public class ListChildrenItemViewModel extends ItemViewModel<ListChildrenViewModel> {

    public ObservableField<ListDataEntity.ItemsEntity> observableField = new ObservableField<>();

    public ListChildrenItemViewModel(@NonNull ListChildrenViewModel viewModel, ListDataEntity.ItemsEntity itemsEntity) {
        super(viewModel);
        observableField.set(itemsEntity);
    }

    /**
     * recyclerView Item点击
     */
    public BindingCommand itemClick = new BindingCommand(() -> {
        //跳转到指定界面,传入条目的参数
        Bundle mBundle = new Bundle();
        mBundle.putString(AppConst.IntentParams.URL, Objects.requireNonNull(observableField.get()).getLink());
        viewModel.startActivity(WebViewActivity.class,mBundle);
    });

}
