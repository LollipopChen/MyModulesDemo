package com.example.mymodulesdemo.ui.main.navigation.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.utils.SNStringUtils;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.NavigationItemEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author ChenQiuE
 * Date：2019/3/28 11:04
 * Email：qiue.chen@supernovachina.com
 */
public class SingleNavigationChildViewModel extends LoadingViewModel {

    private int page = 0;
    private boolean isLoadMore = false;
    private String id;
    public UiChangeObservable uc = new UiChangeObservable();

    /**
     * 能否加载更多
     */
    public ObservableBoolean canLoadMode = new ObservableBoolean(false);
    /**RecyclerView添加ObservableList*/
    public ObservableList<SingleNavigationChildItemViewModel> observableList = new ObservableArrayList<>();
    public ItemBinding<SingleNavigationChildItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_navigation_child);
    public BindingRecyclerViewAdapter<SingleNavigationChildItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    public SingleNavigationChildViewModel(@NonNull Application application) {
        super(application);
    }

    /**下拉刷新*/
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLoadMore = false;
            page = 0;
            requestListData(id);
        }
    });

    /**上拉加载*/
    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLoadMore = true;
            page++;
            requestListData(id);
        }
    });

    @Override
    protected void onRefreshData() {
        setStatus(LOADING);
        requestListData(id);
    }

    public void requestListData(String id){
        this.id = id;

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
                Logger.e("体系文章列表数据：" +  response.toString());
                NavigationItemEntity navigationEntity = SNGsonHelper.toType(response.toString(),NavigationItemEntity.class);
                if (navigationEntity == null){
                    setStatus(LoadingViewModel.NO_DATA);
                    return;
                }
                NavigationItemEntity.DataEntity data = navigationEntity.getData();
                if (data == null){
                    setStatus(LoadingViewModel.NO_DATA);
                    return;
                }

                canLoadMode.set(data.getOver().equals(AppConst.StatusParams.LOAD_OVER));

                List<NavigationItemEntity.ItemEntity> dataList = data.getDatas();
                if (dataList == null || dataList.isEmpty()){
                    setStatus(LoadingViewModel.NO_DATA);
                    return;
                }

                //将实体赋给LiveData
                for (NavigationItemEntity.ItemEntity entity : dataList) {
                    SingleNavigationChildItemViewModel itemViewModel = new SingleNavigationChildItemViewModel(SingleNavigationChildViewModel.this, entity);
                    //双向绑定动态添加Item
                    observableList.add(itemViewModel);
                }
            }

            @Override
            protected void onFailure(ApiException exception) {
                setErrorMessage(exception.getMessage());
            }
        };

        if (SNStringUtils.isEmpty(id)){
            return;
        }

        Map<String,Object> params = new HashMap<>();
        params.put("cid",id);

        HttpObservable.getObservable(ApiCenter.getApi().getNavigationChildData(String.valueOf(page),params),getLifecycleProvider(), FragmentEvent.DESTROY)
                .subscribe(observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        canLoadMode = null;
        observableList = null;
        itemBinding = null;
        adapter = null;
        onRefreshCommand = null;
        onLoadMoreCommand = null;
        uc = null;
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
