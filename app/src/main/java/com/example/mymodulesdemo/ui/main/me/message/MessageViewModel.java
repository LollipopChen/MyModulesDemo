package com.example.mymodulesdemo.ui.main.me.message;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;

/**
 * 消息
 * @author ChenQiuE
 * Date：2019/4/2 14:22
 * Email：qiue.chen@supernovachina.com
 */
public class MessageViewModel extends LoadingViewModel {

    public MessageViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText("我的消息");
    }
}
