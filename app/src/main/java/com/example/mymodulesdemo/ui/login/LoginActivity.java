package com.example.mymodulesdemo.ui.login;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityLoginBinding;

/**
 * @author ChenQiuE
 * Date：2019/3/6 13:25
 * Email：1077503420@qq.com
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {

    /**
     * 初始化根布局
     *
     * @param savedInstanceState
     * @return 布局layout的id
     */
    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    /**
     * 初始化ViewModel的id
     *
     * @return ViewModel的id
     */
    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
