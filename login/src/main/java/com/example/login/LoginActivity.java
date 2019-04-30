package com.example.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.example.libbase.base.BaseActivity;
import com.example.libbase.event.BaseEntityEvent;
import com.example.libbase.event.BaseRefreshDataEvent;
import com.example.libbase.event.RefreshDataTypeConst;
import com.example.login.databinding.ActivityLoginBinding;
import com.example.register.RegisterLoginEntity;
import com.sankuai.waimai.router.annotation.RouterUri;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

/**
 * 登录
 * @author ChenQiuE
 * Date：2019/3/6 13:25
 * Email：1077503420@qq.com
 */

@RouterUri(path = LoginConstant.UiConstant.LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected LoginViewModel initViewModel() {
        return ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void initData() {
        binding.tvForget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.tvForget.getPaint().setAntiAlias(true);
        binding.tvRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.tvRegister.getPaint().setAntiAlias(true);

        viewModel.userName.set(LoginCenter.getInstance().getUserName());
        viewModel.password.set(LoginCenter.getInstance().getPassword());
    }

    @Override
    public void initViewObservable() {
        //监听ViewModel中cbSwitch的变化, 当ViewModel中执行【uc.cbSwitch.setValue(!uc.cbSwitch.get());】时会回调该方法
        viewModel.uc.cbSwitch.observe(this, isChecked -> {
            if (isChecked != null && isChecked){
                binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            binding.etPassword.setSelection(Objects.requireNonNull(binding.etPassword.getText()).toString().length());
        });

        viewModel.uc.entity.observe(this, dataEntity -> EventBus.getDefault().post(new BaseEntityEvent<>(dataEntity)));
    }
}
