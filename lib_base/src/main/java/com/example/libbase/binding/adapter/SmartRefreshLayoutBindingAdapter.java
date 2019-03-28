package com.example.libbase.binding.adapter;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;

import com.example.libbase.binding.command.BindingCommand;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * 下拉刷新，上拉加载
 * @author ChenQiuE
 * Date：2019/3/12 09:37
 * Email：1077503420@qq.com
 */
public class SmartRefreshLayoutBindingAdapter {

    @BindingAdapter(value = {"onRefreshCommand","onLoadMoreCommand"},requireAll = false)
    public static void onRefreshAndLoadMoreCommand(SmartRefreshLayout smartRefreshLayout, final BindingCommand onRefreshCommand, final BindingCommand onLoadMoreCommand){
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                onLoadMoreCommand.execute();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                onRefreshCommand.execute();
            }
        });
    }

    @BindingAdapter(value = {"canLoadMore"},requireAll = false)
    public static void setOnLoadMore(SmartRefreshLayout smartRefreshLayout,boolean canLoadMore){
        smartRefreshLayout.setEnableLoadMore(canLoadMore);
    }
}
