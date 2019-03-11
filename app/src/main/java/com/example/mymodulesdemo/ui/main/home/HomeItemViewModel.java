package com.example.mymodulesdemo.ui.main.home;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.mymodulesdemo.entity.ArticleListEntity;

import java.util.ListIterator;

/**
 * @author ChenQiuE
 * Date：2019/3/11 10:19
 * Email：1077503420@qq.com
 */
public class HomeItemViewModel extends ItemViewModel<HomeViewModel> {

    public ObservableField<ArticleListEntity.ItemEntity> entity = new ObservableField<>();

    public HomeItemViewModel(@NonNull HomeViewModel viewModel, ArticleListEntity.ItemEntity entity) {
        super(viewModel);
        this.entity.set(entity);
    }

    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
}
