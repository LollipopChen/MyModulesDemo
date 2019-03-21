package com.example.mymodulesdemo.ui.search.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.SearchResultEntity;
import com.example.mymodulesdemo.ui.web.WebViewActivity;

import java.util.Objects;

/**
 * 搜索结果
 * @author ChenQiuE
 * Date：2019/3/20 17:15
 * Email：1077503420@qq.com
 */
public class SearchResultItemViewModel extends ItemViewModel<SearchResultViewModel> {

    public ObservableField<SearchResultEntity.ItemEntity> observableField = new ObservableField<>();

    public SearchResultItemViewModel(@NonNull SearchResultViewModel viewModel, SearchResultEntity.ItemEntity entity) {
        super(viewModel);
        observableField.set(entity);
    }

    /**
     * recyclerView Item点击
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //跳转到指定界面,传入条目的参数
            Bundle mBundle = new Bundle();
            mBundle.putString(AppConst.IntentParams.URL, Objects.requireNonNull(observableField.get()).getLink());
            viewModel.startActivity(WebViewActivity.class,mBundle);
        }
    });
}
