package com.example.mymodulesdemo.ui.main.me.widget.count;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityCountBinding;

/**
 * 加减按钮
 * @author ChenQiuE
 * @date 2019/5/20
 */
public class CountActivity extends BaseActivity<ActivityCountBinding,CountActivityViewModel> {
    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_count;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
