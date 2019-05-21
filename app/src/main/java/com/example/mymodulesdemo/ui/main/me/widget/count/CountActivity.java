package com.example.mymodulesdemo.ui.main.me.widget.count;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityCountBinding;
import com.example.mymodulesdemo.ui.otherview.CountView;

/**
 * 加减按钮
 * @author ChenQiuE
 * @date 2019/5/20
 */
public class CountActivity extends BaseActivity<ActivityCountBinding,CountActivityViewModel> implements CountView.OnClickLister {
    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_count;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initBar("加减控件");

        binding.countView.setOnClickLister(this);
        binding.countView.setMaxCount(10);
    }

    @Override
    public void onAfterClick(int value) {
        ToastAlert.show(String.valueOf(value));
    }

    @Override
    public void onMin() {

    }
}
