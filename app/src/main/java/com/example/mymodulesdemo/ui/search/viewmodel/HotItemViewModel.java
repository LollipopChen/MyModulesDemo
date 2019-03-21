package com.example.mymodulesdemo.ui.search.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.HotSearchEntity;
import com.example.mymodulesdemo.ui.search.SearchResultActivity;

import java.util.Objects;

/**
 * @author ChenQiuE
 * Date：2019/3/20 10:02
 * Email：1077503420@qq.com
 */
public class HotItemViewModel extends ItemViewModel<SearchActivityViewModel> {

    public ObservableField<HotSearchEntity.ItemEntity> observableField = new ObservableField<>();

    public HotItemViewModel(@NonNull SearchActivityViewModel viewModel, HotSearchEntity.ItemEntity entity) {
        super(viewModel);
        this.observableField.set(entity);
    }

    /**
     * Item点击
     */
    public BindingCommand onItemClick = new BindingCommand(() -> {
        viewModel.adapter.setSelectId(Objects.requireNonNull(observableField.get()).getId());
        viewModel.searchKeyWord.set(Objects.requireNonNull(observableField.get()).getName());

        Bundle bundle = new Bundle();
        bundle.putString(AppConst.IntentParams.KEY_WORD, Objects.requireNonNull(observableField.get()).getName());
        viewModel.startActivity(SearchResultActivity.class,bundle);
    });
}
