package com.example.mymodulesdemo.ui.main.me.info;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

/**
 * @author ChenQiuE
 * Date：2019/4/2 16:04
 * Email：qiue.chen@supernovachina.com
 */
public class UserInfoViewModel extends ToolbarViewModel {

    public UserInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText("个人信息");
    }
}
