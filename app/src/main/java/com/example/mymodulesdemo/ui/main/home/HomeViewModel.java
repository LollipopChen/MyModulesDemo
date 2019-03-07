package com.example.mymodulesdemo.ui.main.home;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.mymodulesdemo.ui.toolbar.ToolbarViewModel;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:25
 * Email：1077503420@qq.com
 */
public class HomeViewModel extends ToolbarViewModel {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 初始化标题
     */
    public void initToolBar(String title){
        setTitleText(title);
    }
}
