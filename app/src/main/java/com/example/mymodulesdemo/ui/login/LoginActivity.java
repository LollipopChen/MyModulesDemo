package com.example.mymodulesdemo.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityLoginBinding;

import java.util.Objects;

/**
 * @author ChenQiuE
 * Date：2019/3/6 13:25
 * Email：1077503420@qq.com
 */
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
    public void initViewObservable() {
        //监听ViewModel中cbSwitch的变化, 当ViewModel中执行【uc.cbSwitch.setValue(!uc.cbSwitch.get());】时会回调该方法
        viewModel.uc.cbSwitch.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isChecked) {
                if (isChecked != null && isChecked){
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                binding.etPassword.setSelection(Objects.requireNonNull(binding.etPassword.getText()).toString().length());
            }
        });
    }
}
