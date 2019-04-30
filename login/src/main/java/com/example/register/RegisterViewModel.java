package com.example.register;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.api.ApiCenter;
import com.example.libbase.base.BaseViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.login.LoginActivity;
import com.example.login.LoginCenter;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册
 * @author ChenQiuE
 * Date：2019/4/30 12:01
 * Email：qiue.chen@supernovachina.com
 */
public class RegisterViewModel extends BaseViewModel {
    /** 用户名*/
    public ObservableField<String> name = new ObservableField<>("");
    /** 密码 */
    public ObservableField<String> password = new ObservableField<>("");
    /** 再次输入密码 */
    public ObservableField<String> rePassword = new ObservableField<>("");

    public UiChangeObservable uc = new UiChangeObservable();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 眼睛
     */
    public BindingCommand<Boolean> passwordShowAndHideOnClick = new BindingCommand<>(isChecked -> uc.cbSwitch.setValue(isChecked));

    /**
     * 眼睛
     */
    public BindingCommand<Boolean> rePasswordShowAndHideOnClick = new BindingCommand<>(isChecked -> uc.cbReSwitch.setValue(isChecked));

    /**
     *  确认
     */
    public BindingCommand onCommitCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String userName = name.get();
            if (SNStringUtils.isEmpty(userName)){
                ToastAlert.show("请输入用户名");
                return;
            }
            String passwordStr = password.get();
            if (SNStringUtils.isEmpty(passwordStr)){
                ToastAlert.show("请输入密码");
                return;
            }
            String rePasswordStr = rePassword.get();
            if (SNStringUtils.isEmpty(rePasswordStr)){
                ToastAlert.show("请再次输入密码");
                return;
            }

            Map<String,Object> params = new HashMap<>();
            params.put("username",userName);
            params.put("password",passwordStr);
            params.put("repassword",rePasswordStr);

            uc.isShowDialogEvent.setValue(true);
            HttpObserver observer = new HttpObserver() {

                @Override
                public void onComplete() {
                    uc.isShowDialogEvent.setValue(false);
                }

                @Override
                protected void onSuccess(Object response) {
                    Logger.e("注册成功：" + response.toString());
                    RegisterLoginEntity entity = SNGsonHelper.toType(response.toString(), RegisterLoginEntity.class);
                    if (entity == null){
                        ToastAlert.show("注册失败!");
                        return;
                    }
                    RegisterLoginEntity.DataEntity data = entity.getData();
                    if (data == null){
                        ToastAlert.show("注册失败!");
                        return;
                    }
                    ToastAlert.show("注册成功!");
                    LoginCenter.getInstance().setUserName(data.getUsername());
                    LoginCenter.getInstance().setPassword(passwordStr);
                    startActivity(LoginActivity.class);
                    finish();
                }

                @Override
                protected void onFailure(ApiException exception) {
                    ToastAlert.show(exception.getMsg());
                }
            };

            HttpObservable.getObservable(ApiCenter.getApi().register(params),getLifecycleProvider(), ActivityEvent.DESTROY).subscribe(observer);
        }
    });

    public class UiChangeObservable {
        /**
         * 密码显示隐藏
         */
        SingleLiveEvent<Boolean> cbSwitch = new SingleLiveEvent<>();
        SingleLiveEvent<Boolean> cbReSwitch = new SingleLiveEvent<>();

        /**
         * loading dialog
         */
        SingleLiveEvent<Boolean> isShowDialogEvent = new SingleLiveEvent<>();
    }
}
