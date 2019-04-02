package com.example.mymodulesdemo.ui.main.me.info;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.login.LoginInterceptor;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.RouterManager;
import com.example.mymodulesdemo.databinding.ActivityUserInfoBinding;
import com.sankuai.waimai.router.annotation.RouterUri;

/**
 * 个人信息
 * @author ChenQiuE
 * Date：2019/4/2 16:03
 * Email：qiue.chen@supernovachina.com
 */

@RouterUri(path = RouterManager.UiConstant.USER_INFO_WITH_LOGIN,interceptors = LoginInterceptor.class)
public class UserInfoActivity extends BaseActivity<ActivityUserInfoBinding,UserInfoViewModel> {

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_user_info;
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
