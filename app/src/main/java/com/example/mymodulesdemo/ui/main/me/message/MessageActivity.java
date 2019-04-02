package com.example.mymodulesdemo.ui.main.me.message;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.login.LoginInterceptor;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.RouterManager;
import com.example.mymodulesdemo.databinding.ActivityMessageBinding;
import com.sankuai.waimai.router.annotation.RouterUri;

/**
 * @author ChenQiuE
 * Date：2019/4/2 14:16
 * Email：qiue.chen@supernovachina.com
 */

@RouterUri(path = RouterManager.UiConstant.MESSAGE_WITH_LOGIN,interceptors = LoginInterceptor.class)
public class MessageActivity extends BaseActivity<ActivityMessageBinding,MessageViewModel> {

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_message;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar();
    }
}
