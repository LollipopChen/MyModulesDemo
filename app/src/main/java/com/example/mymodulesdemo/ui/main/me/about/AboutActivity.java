package com.example.mymodulesdemo.ui.main.me.about;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.libbase.base.SnBaseApplication;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityAboutBinding;

/**
 * 关于
 * @author ChenQiuE
 * Date：2019/3/7 15:51
 * Email：1077503420@qq.com
 */
public class AboutActivity extends BaseActivity<ActivityAboutBinding,AboutViewModel> {

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_about;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("关于");
        viewModel.setAppIcon(SnBaseApplication.getApplicationIcon());
        viewModel.setAppName(SnBaseApplication.getApplicationName());
        viewModel.setAppVersion("v" + SnBaseApplication.getApplicationVersionName());
    }
}
