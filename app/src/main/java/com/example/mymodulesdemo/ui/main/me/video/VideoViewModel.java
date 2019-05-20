package com.example.mymodulesdemo.ui.main.me.video;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

/**
 * @author ChenQiuE
 * Date：2019/3/29 13:15
 * Email：qiue.chen@supernovachina.com
 */
public class VideoViewModel extends ToolbarViewModel {

    public VideoViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setLeftIconVisible(View.VISIBLE);
        setTitleText(title);
    }
}
