package com.example.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.api.ApiCenter;
import com.example.libbase.base.BaseViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.binding.command.BindingConsumer;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.register.RegisterActivity;
import com.example.register.RegisterLoginEntity;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * 注意：当控件需要获取值（例如：获取EditText的Text）和赋值时,绑定“android：text”需要在xml中使用双向绑定“@={xxxxx}”，否则get()时获取不到值
 *
 * @author ChenQiuE
 * Date：2019/3/6 13:25
 * Email：1077503420@qq.com
 */
public class LoginViewModel extends BaseViewModel {
    /**用户名*/
    public ObservableField<String> userName = new ObservableField<>("");
    /**密码*/
    public ObservableField<String> password = new ObservableField<>("");
    /**封装一个界面发生改变的观察者,用于跟UI交互*/
    public UiChangeObservable uc = new UiChangeObservable();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 眼睛
     */
    public BindingCommand<Boolean> passwordShowAndHideOnClick = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean isChecked) {
            uc.cbSwitch.setValue(isChecked);
        }
    });

    /**
     * 忘记密码
     */
    public BindingCommand forgetPasswordClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    /**
     * 注册
     */
    public BindingCommand registerClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(RegisterActivity.class);
            finish();
        }
    });

    /**
     * 登录
     */
    public BindingCommand loginClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Logger.e("用户名："+ userName.get());
            Logger.e("密码："+ password.get());
            String userNameStr = userName.get();
            if (SNStringUtils.isEmpty(userNameStr)){
                ToastAlert.show("请输入用户名");
                return;
            }
            String passwordStr = password.get();
            if (SNStringUtils.isEmpty(passwordStr)){
                ToastAlert.show("请输入用密码");
                return;
            }

            HttpObserver observer = new HttpObserver() {
                @Override
                protected void onSuccess(Object response) {
                    Logger.e("登录成功：" + response.toString());

                    RegisterLoginEntity entity = SNGsonHelper.toType(response.toString(),RegisterLoginEntity.class);
                    if (entity == null){
                        ToastAlert.show("登录失败");
                        return;
                    }
                    RegisterLoginEntity.DataEntity data = entity.getData();
                    if (data == null){
                        ToastAlert.show("登录失败");
                        return;
                    }

                    ToastAlert.show("登录成功");
                    LoginCenter.getInstance().setLogin(true);
                    LoginManager.getLoginService().notifyLoginSuccess();

                    //设置个人信息
                    uc.entity.setValue(data);
                    finish();
                }

                @Override
                protected void onFailure(ApiException exception) {
                    ToastAlert.show(exception.getMsg());
                    LoginCenter.getInstance().setLogin(false);
                    uc.entity.setValue(null);
                    LoginManager.getLoginService().notifyLoginFailure();
                }
            };

            Map<String,Object> params = new HashMap<>(2);
            params.put("username",userNameStr);
            params.put("password",passwordStr);

            HttpObservable.getObservable(ApiCenter.getApi().login(params),getLifecycleProvider(), ActivityEvent.DESTROY).subscribe(observer);

        }
    });

    @Override
    public void onBackPressed() {
        ToastAlert.show("取消登录");
        LoginManager.getLoginService().notifyLoginCancel();
        super.onBackPressed();
    }

    public class UiChangeObservable {
        /**密码显示隐藏*/
        SingleLiveEvent<Boolean> cbSwitch = new SingleLiveEvent<>();

        /**
         * 回传数据
         */
        SingleLiveEvent<RegisterLoginEntity.DataEntity> entity = new SingleLiveEvent<>();
    }
}
