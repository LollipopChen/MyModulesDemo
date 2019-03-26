package com.example.mymodulesdemo.ui.main.navigation.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.entity.NavigationEntity;

/**
 * 体系
 * @author ChenQiuE
 * Date：2019/3/25 15:43
 * Email：qiue.chen@supernovachina.com
 */
public class NavigationItemViewModel extends ItemViewModel<NavigationViewModel> {

    public ObservableField<String> observableField = new ObservableField<>("");
    public ObservableList<NavigationEntity.ItemEntity> dataList = new ObservableArrayList<>();

    public NavigationItemViewModel(@NonNull NavigationViewModel viewModel, NavigationEntity.DataEntity entity) {
        super(viewModel);
        observableField.set(entity.getName());
        dataList.addAll(entity.getChildren());
    }

    public BindingCommand onItemClick = new BindingCommand(() -> ToastAlert.show(observableField.get()));
}
