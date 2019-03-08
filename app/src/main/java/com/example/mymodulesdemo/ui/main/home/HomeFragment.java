package com.example.mymodulesdemo.ui.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentHomeBinding;
import com.example.mymodulesdemo.entity.BannerEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:24
 * Email：1077503420@qq.com
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeViewModel> {

    private List<BannerEntity> bannerList = new ArrayList<>();

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    public int initContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        // 初始化标题(需要使用标题栏的UI，viewModel需要继承ToolbarViewModel)
        viewModel.initToolBar(getString(R.string.action_home));
        // 服务器url：http://www.wanandroid.com/banner/json
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        binding.banner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止自动翻页
        binding.banner.stopTurning();
    }
}
