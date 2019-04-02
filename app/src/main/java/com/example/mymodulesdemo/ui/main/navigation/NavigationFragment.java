package com.example.mymodulesdemo.ui.main.navigation;

import android.arch.lifecycle.Observer;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentNavigationBinding;
import com.example.mymodulesdemo.ui.main.navigation.viewmodel.NavigationViewModel;

/**
 * 体系
 * @author ChenQiuE
 * Date：2019/3/21 11:39
 * Email：1077503420@qq.com
 */
public class NavigationFragment extends BaseFragment<FragmentNavigationBinding, NavigationViewModel> {

    public static NavigationFragment getInstance(){
        return new NavigationFragment();
    }

    @Override
    public int initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_navigation;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.swipeFreshLayout.setEnableLoadMore(false);

        viewModel.initToolBar(getString(R.string.action_system));
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
