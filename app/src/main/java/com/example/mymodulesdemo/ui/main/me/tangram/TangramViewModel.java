package com.example.mymodulesdemo.ui.main.me.tangram;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

/**
 * 阿里Tangram
 * @author ChenQiuE
 * @date 2019/5/21
 */
public class TangramViewModel extends ToolbarViewModel {

    public TangramViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setLeftIconVisible(View.VISIBLE);
        setTitleText(title);
        setRightIconVisible(View.INVISIBLE);
    }
}
