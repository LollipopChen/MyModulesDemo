package com.example.mymodulesdemo.ui.main.navigation.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

/**
 * 一个体系
 * @author ChenQiuE
 * Date：2019/3/28 10:27
 * Email：qiue.chen@supernovachina.com
 */
public class SingleNavigationViewModel extends ToolbarViewModel {

    public SingleNavigationViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText(title);
    }
}
