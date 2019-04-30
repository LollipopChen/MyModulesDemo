package com.example.register;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.example.libbase.base.BaseActivity;
import com.example.libbase.widget.dialog.SNLoadingDialog;
import com.example.login.BR;
import com.example.login.R;
import com.example.login.databinding.ActivityRegisterBinding;

import java.util.Objects;

/**
 * 注册
 * @author ChenQiuE
 * Date：2019/4/30 12:00
 * Email：qiue.chen@supernovachina.com
 */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding,RegisterViewModel> {

    private SNLoadingDialog snLoadingDialog;

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        snLoadingDialog = new SNLoadingDialog(this);
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.cbSwitch.observe(this, isChecked -> {
            if (isChecked != null && isChecked){
                binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            binding.etPassword.setSelection(Objects.requireNonNull(binding.etPassword.getText()).toString().length());
        });

        viewModel.uc.cbReSwitch.observe(this, isChecked -> {
            if (isChecked != null && isChecked){
                binding.etRePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                binding.etRePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            binding.etRePassword.setSelection(Objects.requireNonNull(binding.etRePassword.getText()).toString().length());
        });

        viewModel.uc.isShowDialogEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isShow) {
                if (isShow != null && isShow){
                    snLoadingDialog.showProgress();
                }else {
                    snLoadingDialog.dismissProgress();
                }
            }
        });
    }
}
