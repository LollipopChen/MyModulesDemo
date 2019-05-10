package com.example.mymodulesdemo.ui.main.me.widget;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.login.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityWidgetBinding;

/**
 * 控件
 * @author ChenQiuE
 * @date 2019/5/10
 */
public class WidgetActivity extends BaseActivity<ActivityWidgetBinding,WidgetViewModel> {
    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_widget;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("控件");
    }
}
