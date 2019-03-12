package com.example.mymodulesdemo.ui.web;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.ActivityWebViewBinding;

/**
 * WebView
 * @author ChenQiuE
 * Date：2019/3/12 10:16
 * Email：1077503420@qq.com
 */
public class WebViewActivity extends BaseActivity<ActivityWebViewBinding,WebViewModel> {

    private String link;

    @Override
    public void initParams() {
        link = getIntent().getStringExtra(AppConst.IntentParams.URL);
    }

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolbar();
        viewModel.setLink(link);
    }

}
