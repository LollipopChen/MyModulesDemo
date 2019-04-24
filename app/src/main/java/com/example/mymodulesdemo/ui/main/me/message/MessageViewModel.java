package com.example.mymodulesdemo.ui.main.me.message;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.widget.toast.ToastAlert;
import com.example.login.LoginManager;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

/**
 * 消息
 * @author ChenQiuE
 * Date：2019/4/2 14:22
 * Email：qiue.chen@supernovachina.com
 */
public class MessageViewModel extends ToolbarViewModel {

    public MessageViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText("我的消息");
    }

    @Override
    public void onBackPressed() {
        ToastAlert.show("取消登录");
        LoginManager.getLoginService().notifyLoginCancel();
        super.onBackPressed();
    }
}
