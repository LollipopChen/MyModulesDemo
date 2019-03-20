package com.example.mymodulesdemo.ui.search.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author ChenQiuE
 * Date：2019/3/20 16:38
 * Email：1077503420@qq.com
 */
public class SearchResultViewModel extends LoadingViewModel {

    /**界面变化监听，用于viewModel和UI交互*/
    public UiChangeObservable uc = new UiChangeObservable();
    /**RecyclerView添加ObservableList*/
    public ObservableList<SearchResultItemViewModel> observableList = new ObservableArrayList<>();
    /**RecyclerView添加ItemBinding*/
    public ItemBinding<SearchResultItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_home_recycler_view);
    /**RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象*/
    public BindingRecyclerViewAdapter<SearchResultItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    /**页数从0开始*/
    private int page = 0;
    private boolean isLoadMore = false;
    private String keyWord;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void initTooBar(String keyWord) {
        this.keyWord = keyWord;
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText(keyWord);
    }

    /**下拉刷新*/
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLoadMore = false;
            page = 0;
            requestListData();
        }
    });

    /**上拉加载*/
    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLoadMore = true;
            page++;
            requestListData();
        }
    });

    public void requestListData() {
        HttpObserver observer = new HttpObserver() {
            @Override
            protected void onSuccess(Object response) {
                Logger.e("搜索结果：" + response.toString());
            }

            @Override
            protected void onFailure(ApiException exception) {

            }
        };
        Map<String,Object> params = new HashMap<>();
        params.put("k",keyWord);
        HttpObservable.getObservable(ApiCenter.getApi().getSearchList(String.valueOf(page),params),getLifecycleProvider(), ActivityEvent.DESTROY)
                .subscribe(observer);
    }

    /**
     * 列表界面变化观察者
     */
    public class UiChangeObservable{
        /**下拉刷新完成*/
        public ObservableBoolean finishRefreshing = new ObservableBoolean(false);
        /**上拉加载完成*/
        public ObservableBoolean finishLoadMore = new ObservableBoolean(false);
    }
}
