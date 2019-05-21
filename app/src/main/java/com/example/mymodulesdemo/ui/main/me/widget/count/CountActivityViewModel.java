package com.example.mymodulesdemo.ui.main.me.widget.count;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

/**
 * 加减控件
 * @author ChenQiuE
 * @date 2019/5/20
 */
public class CountActivityViewModel extends ToolbarViewModel {

    public CountActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void initBar(String title) {
        setLeftIconVisible(View.VISIBLE);
        setRightIconVisible(View.INVISIBLE);
        setTitleText(title);
    }
}
