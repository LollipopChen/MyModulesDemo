package com.example.mymodulesdemo.ui.main.list;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.libbase.utils.SNStringUtils;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.FragmentListChildrenBinding;
import com.example.mymodulesdemo.ui.otherview.viewmodel.LoadingViewModel;

/**
 * @author ChenQiuE
 * Date：2019/3/13 16:59
 * Email：1077503420@qq.com
 */
public class ListChildrenFragment extends BaseFragment<FragmentListChildrenBinding,ListChildrenViewModel> {

    private String id;

    @Override
    public void initParams() {
        Bundle bundle = getArguments();
        if (bundle != null){
            id = bundle.getString(AppConst.IntentParams.ID);
        }
    }

    @Override
    public int initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_list_children;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        if (!SNStringUtils.isEmpty(id)){
            viewModel.setStatus(LoadingViewModel.LOADING);
            viewModel.getDataList(id);
        }
    }

    @Override
    public void initViewObservable() {
        //监听下拉刷新完成
        viewModel.uc.finishRefreshing.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                //结束刷新
                binding.swipeFreshLayout.finishRefresh();
            }
        });
        //监听上拉加载完成
        viewModel.uc.finishLoadMore.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                //结束刷新
                binding.swipeFreshLayout.finishLoadMore();
            }
        });
    }
}
