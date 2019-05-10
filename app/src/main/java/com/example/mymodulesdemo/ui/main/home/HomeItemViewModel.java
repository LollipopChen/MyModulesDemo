package com.example.mymodulesdemo.ui.main.home;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.widget.ProgressWebView;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.ArticleListEntity;
import com.example.mymodulesdemo.ui.web.WebViewActivity;

import java.util.Objects;

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

    /**
     * recyclerView Item点击
     */
    public BindingCommand itemClick = new BindingCommand(() -> {
        //跳转到指定界面,传入条目的参数
        Bundle mBundle = new Bundle();
        mBundle.putString(AppConst.IntentParams.URL, Objects.requireNonNull(entity.get()).getLink());
        viewModel.startActivity(WebViewActivity.class,mBundle);
    });
}
