package com.example.mymodulesdemo.ui.main.navigation;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.mymodulesdemo.entity.NavigationEntity;

/**
 * 体系
 * @author ChenQiuE
 * Date：2019/3/25 15:43
 * Email：qiue.chen@supernovachina.com
 */
public class NavigationItemViewModel extends ItemViewModel<NavigationViewModel> {

    public ObservableField<String> observableField = new ObservableField<>("");

    public NavigationItemViewModel(@NonNull NavigationViewModel viewModel, NavigationEntity.DataEntity entity) {
        super(viewModel);
    }
}
