package com.example.mymodulesdemo.ui.main.system.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.mymodulesdemo.entity.SystemEntity;

/**
 * 导航
 * @author ChenQiuE
 * Date：2019/3/21 13:56
 * Email：1077503420@qq.com
 */
public class SystemItemViewModel extends ItemViewModel<SystemViewModel> {

    public ObservableList<SystemEntity.DataItemEntity> dataList = new ObservableArrayList<>();
    public ObservableField<String> observableField = new ObservableField<>("");

    public SystemItemViewModel(@NonNull SystemViewModel viewModel, SystemEntity.DataEntity entity) {
        super(viewModel);
        this.observableField.set(entity.getName());
        dataList.addAll(entity.getArticles());
    }
}
