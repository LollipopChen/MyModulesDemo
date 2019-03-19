package com.example.mymodulesdemo.ui.main.home;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.base.BaseDataInterface;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.entity.ArticleListEntity;
import com.example.mymodulesdemo.entity.BannerEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.example.mymodulesdemo.ui.search.SearchActivity;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:25
 * Email：1077503420@qq.com
 */
public class HomeViewModel extends LoadingViewModel {
    /**界面变化监听，用于viewModel和UI交互*/
    public UiChangeObservable uc = new UiChangeObservable();
    /**RecyclerView添加ObservableList*/
    public ObservableList<HomeItemViewModel> observableList = new ObservableArrayList<>();
    /**RecyclerView添加ItemBinding*/
    public ItemBinding<HomeItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_home_recycler_view);
    /**RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象*/
    public BindingRecyclerViewAdapter<HomeItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    /**页数从0开始*/
    private int page = 0;
    private boolean isLoadMore = false;

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

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 初始化标题
     */
    public void initView(String title, Drawable icon){
        setTitleText(title);
        setRightIconVisibleVisible(View.VISIBLE);
        setRightMoreIcon(icon);
        setStatus(LoadingViewModel.LOADING);
    }

    /**
     * 广告数据
     */
    public void requestBannerData() {
        HttpObserver observer = new HttpObserver() {
            @Override
            protected void onSuccess(Object response) {
                Logger.e("广告：" + response.toString());
                BannerEntity bannerEntity = SNGsonHelper.toType(response.toString(),BannerEntity.class);
                if (bannerEntity != null){
                    // 设置列表数据
                    uc.dataList.setValue(bannerEntity.getData());
                }else{
                    showDialog("暂无数据");
                }
            }

            @Override
            protected void onFailure(ApiException exception) {
                showDialog(exception.getMsg());
            }
        };
        HttpObservable.getObservable(ApiCenter.getApi().getBanner(),getLifecycleProvider(), FragmentEvent.DESTROY)
                .subscribe(observer);
    }

    @Override
    protected void onRefreshData() {
        requestListData();
    }

    /**
     * 请求列表数据
     */
    public void requestListData() {
        HttpObserver observer = new HttpObserver() {

            @Override
            public void onComplete() {
                //刷新完成收回
               if (isLoadMore){
                   uc.finishLoadMore.set(!uc.finishLoadMore.get());
               }else {
                   uc.finishRefreshing.set(!uc.finishRefreshing.get());
               }
            }

            @Override
            protected void onSuccess(Object response) {
                setStatus(LoadingViewModel.STOP_LOADING);
                //清除列表
                if (!isLoadMore){
                    observableList.clear();
                }
                Logger.e("列表数据：" +  response.toString());
                //数据转换
                ArticleListEntity articleListEntity = SNGsonHelper.toType(response.toString(),ArticleListEntity.class);

                if (articleListEntity == null){
                   return;
                }
                if (articleListEntity.getData() == null){
                    return;
                }
                //将实体赋给LiveData
                for (ArticleListEntity.ItemEntity entity : articleListEntity.getData().getDataList()) {
                    HomeItemViewModel itemViewModel = new HomeItemViewModel(HomeViewModel.this, entity);
                    //双向绑定动态添加Item
                    observableList.add(itemViewModel);
                }
            }

            @Override
            protected void onFailure(ApiException exception) {
                ToastAlert.show(exception.getMsg());
            }
        };
        HttpObservable.getObservable(ApiCenter.getApi().getHomeList(String.valueOf(page)),getLifecycleProvider(), FragmentEvent.DESTROY)
                .subscribe(observer);
    }

    @Override
    protected void moreOnClick() {
        //搜索点击
        startActivity(SearchActivity.class);
    }

    /**
     * 列表界面变化观察者
     */
    public class UiChangeObservable{
        /**下拉刷新完成*/
        public ObservableBoolean finishRefreshing = new ObservableBoolean(false);
        /**上拉加载完成*/
        public ObservableBoolean finishLoadMore = new ObservableBoolean(false);
        /**列表数据*/
        public SingleLiveEvent<List<BannerEntity.BannerItemEntity>> dataList = new SingleLiveEvent<>();
    }
}
