package com.example.mymodulesdemo.ui.main.me.info;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.console.SNResponseEntity;
import com.example.libbase.event.BaseRefreshDataEvent;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.login.LoginCenter;
import com.example.mymodulesdemo.center.UserCenter;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author ChenQiuE
 * Date：2019/4/2 16:04
 * Email：qiue.chen@supernovachina.com
 */
public class UserInfoViewModel extends ToolbarViewModel {

    public ObservableField<String> userName = new ObservableField<>("");

    public UserInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText("个人信息");
    }

    public void setUserName(String name){
        userName.set(name);
    }

    /**
     * 退出登录
     */
    public BindingCommand onLogoutCommand = new BindingCommand(() -> {
        HttpObserver httpObserver = new HttpObserver() {

            @Override
            public void onComplete() {

            }

            @Override
            protected void onSuccess(Object response) {
                UserCenter.getInstance().clear();
                LoginCenter.getInstance().clear();
                EventBus.getDefault().post(new BaseRefreshDataEvent(AppConst.RefreshDataParams.LOGOUT));
                finish();
            }

            @Override
            protected void onFailure(ApiException exception) {
                ToastAlert.show(exception.getMsg());
            }
        };
        HttpObservable.getObservable(ApiCenter.getApi().logout(),getLifecycleProvider(), ActivityEvent.DESTROY).subscribe(httpObserver);
    });
}
