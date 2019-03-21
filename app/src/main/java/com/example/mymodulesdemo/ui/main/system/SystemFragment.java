package com.example.mymodulesdemo.ui.main.system;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentSystemBinding;
import com.example.mymodulesdemo.ui.main.system.viewmodel.SystemViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * 导航
 * @author ChenQiuE
 * Date：2019/3/21 11:39
 * Email：1077503420@qq.com
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemViewModel> {

    public static SystemFragment getInstance(){
        return new SystemFragment();
    }

    @Override
    public int initContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_system;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.swipeFreshLayout.setEnableLoadMore(false);
        viewModel.initToolbar(getString(R.string.action_navigation));

        viewModel.requestData();
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.finishRefreshing.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.swipeFreshLayout.finishRefresh();
            }
        });
    }
}
