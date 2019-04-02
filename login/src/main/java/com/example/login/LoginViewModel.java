package com.example.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.binding.command.BindingConsumer;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.orhanobut.logger.Logger;

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
    /**
     * 是否登录成功
     */
    private boolean isLogin;

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
     * 登录
     */
    public BindingCommand loginClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Logger.e("用户名："+ userName.get());
            Logger.e("密码："+ password.get());
            if (SNStringUtils.isEmpty(userName.get())){
                ToastAlert.show("请输入用户名");
                return;
            }

            if (SNStringUtils.isEmpty(password.get())){
                ToastAlert.show("请输入用密码");
                return;
            }

            if (!userName.get().equals("admin")){
                ToastAlert.show("用户名不正确");
                return;
            }

            if (!password.get().equals("123456")){
                ToastAlert.show("密码不正确");
                return;
            }
            //TODO 待修改↓
            isLogin = true;
           if (isLogin){
               ToastAlert.show("登录成功");
               LoginCenter.getInstance().setLogin(true);
               LoginManager.getLoginService().notifyLoginSuccess();
               //设置个人信息
               uc.isLogin.setValue(isLogin);
               finish();
           }else {
               ToastAlert.show("登录失败");
               LoginCenter.getInstance().setLogin(false);
               uc.isLogin.setValue(isLogin);
               LoginManager.getLoginService().notifyLoginFailure();
           }
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
        SingleLiveEvent<Boolean> isLogin = new SingleLiveEvent<>();
    }
}
