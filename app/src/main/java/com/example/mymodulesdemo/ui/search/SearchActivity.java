package com.example.mymodulesdemo.ui.search;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivitySearchBinding;

/**
 * 搜索
 * @author ChenQiuE
 * Date：2019/3/19 17:52
 * Email：1077503420@qq.com
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding,SearchActivityViewModel> {

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_search;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
