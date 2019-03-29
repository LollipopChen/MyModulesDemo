package com.example.mymodulesdemo.ui.main.me;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.entity.UserInfoEntity;
import com.example.mymodulesdemo.ui.main.me.about.AboutActivity;
import com.example.mymodulesdemo.ui.main.me.video.VideoActivity;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:32
 * Email：1077503420@qq.com
 */
public class MeViewModel extends BaseViewModel {

    public ObservableField<UserInfoEntity> entity = new ObservableField<>();

    public MeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 信息点击
     */
    public BindingCommand onMessageClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastAlert.show("信息点击");
        }
    });

    /**
     * 设置点击
     */
    public BindingCommand onSettingClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastAlert.show("设置点击");
        }
    });

    /**
     * 登录或点击用户信息
     */
    public BindingCommand onLoginClickOrInfo = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastAlert.show("登录或点击用户信息");
        }
    });

    /**
     * 跳转关于
     */
    public BindingCommand onAboutClick = new BindingCommand(() -> startActivity(AboutActivity.class));

    /**
     * 视频
     */
    public BindingCommand onVideoClick = new BindingCommand(() -> startActivity(VideoActivity.class));

    @Override
    public void onDestroy() {
        super.onDestroy();
        entity = null;
    }

    /**
     * 数据体
     * @param userInfoEntity 数据
     */
    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.entity.set(userInfoEntity);
    }
}
