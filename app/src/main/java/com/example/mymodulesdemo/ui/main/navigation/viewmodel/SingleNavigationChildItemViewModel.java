package com.example.mymodulesdemo.ui.main.navigation.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.NavigationItemEntity;
import com.example.mymodulesdemo.ui.web.WebViewActivity;

import java.util.Objects;

/**
 * @author ChenQiuE
 * Date：2019/3/28 11:23
 * Email：qiue.chen@supernovachina.com
 */
public class SingleNavigationChildItemViewModel extends ItemViewModel {

    public ObservableField<NavigationItemEntity.ItemEntity> observableField = new ObservableField<>();

    public SingleNavigationChildItemViewModel(@NonNull BaseViewModel viewModel, NavigationItemEntity.ItemEntity entity) {
        super(viewModel);
        observableField.set(entity);
    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //跳转到指定界面,传入条目的参数
            Bundle mBundle = new Bundle();
            mBundle.putString(AppConst.IntentParams.URL, Objects.requireNonNull(observableField.get()).getLink());
            viewModel.startActivity(WebViewActivity.class,mBundle);
        }
    });
}
