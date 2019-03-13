package com.example.mymodulesdemo.ui.main.home;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.libbase.utils.ImageUtils;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.FragmentHomeBinding;
import com.example.mymodulesdemo.entity.BannerEntity;
import com.example.mymodulesdemo.ui.web.WebViewActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:24
 * Email：1077503420@qq.com
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeViewModel>{

    private List<BannerEntity.BannerItemEntity> bannerList = new ArrayList<>();

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
        binding.recyclerView.setNestedScrollingEnabled(false);
        // 初始化标题(需要使用标题栏+LoadingView的UI，viewModel需要继承最底层LoadingViewModel)
        viewModel.initView(getString(R.string.action_home));
        // 请求广告数据
        viewModel.requestBannerData();
        //请求列表数据
        viewModel.requestListData();
    }

    /**
     * 页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
     */
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
        //数据回调
        viewModel.uc.dataList.observe(this, this::setListData);
    }

    private void setListData(List<BannerEntity.BannerItemEntity> data) {
        this.bannerList.addAll(data);
        List<String> strings = new ArrayList<>();
        for (BannerEntity.BannerItemEntity bannerInfo : data) {
            strings.add(bannerInfo.getImagePath());
        }
        ImageUtils.loadBanner(binding.banner, strings, position -> {
            //TODO 广告点击
            Bundle bundle = new Bundle();
            bundle.putString(AppConst.IntentParams.URL,bannerList.get(position).getUrl());
            startActivity(WebViewActivity.class,bundle);
        });
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
