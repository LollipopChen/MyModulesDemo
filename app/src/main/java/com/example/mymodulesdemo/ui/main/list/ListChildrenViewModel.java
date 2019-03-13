package com.example.mymodulesdemo.ui.main.list;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.bus.RxSubscriptions;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.entity.ListDataEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.main.home.HomeItemViewModel;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @author ChenQiuE
 * Date：2019/3/13 17:00
 * Email：1077503420@qq.com
 */
public class ListChildrenViewModel extends LoadingViewModel {

    /**页数从1开始*/
    private int page = 1;
    private boolean isLoadMore = false;
    private String id;

    /**RecyclerView添加ObservableList*/
    public ObservableList<ListChildrenItemViewModel> observableList = new ObservableArrayList<>();
    /**RecyclerView添加OnItemBind 多层布局*/
    public OnItemBind<ListChildrenItemViewModel> itemBinding = (itemBinding, position, item) -> itemBinding.set(BR.viewModel,position%2==0 ? R.layout.item_list_2 : R.layout.item_list);
    /**RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象*/
    public BindingRecyclerViewAdapter<ListChildrenItemViewModel> adapter = new BindingRecyclerViewAdapter<>();
    /**UI监听*/
    public UiChangeObservable uc = new UiChangeObservable();

    public ListChildrenViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("公众号");
    }

    /**下拉刷新*/
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLoadMore = false;
            page = 1;
            getDataList(id);
        }
    });

    /**上拉加载*/
    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLoadMore = true;
            page++;
            getDataList(id);
        }
    });

    /**
     * 获取列表数据
     */
    public void getDataList(String id){
        this.id = id;
        if (SNStringUtils.isEmpty(id)) {
            ToastAlert.show("id is empty");
            return;
        }

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
                //清除列表
                if (!isLoadMore){
                    observableList.clear();
                }

                Logger.e("公众号历史数据：" + response.toString());
                ListDataEntity listDataEntity = SNGsonHelper.toType(response.toString(),ListDataEntity.class);
                if (listDataEntity == null){
                    return;
                }
                ListDataEntity.DataEntity data = listDataEntity.getData();
                if (data == null){
                    return;
                }
                List<ListDataEntity.ItemsEntity> dataList = data.getDataList();
                if (dataList == null || dataList.isEmpty()){
                    return;
                }
                //将实体赋给LiveData
                for (ListDataEntity.ItemsEntity entity : dataList) {
                    ListChildrenItemViewModel itemViewModel = new ListChildrenItemViewModel(ListChildrenViewModel.this, entity);
                    //双向绑定动态添加Item
                    observableList.add(itemViewModel);
                }
            }

            @Override
            protected void onFailure(ApiException exception) {

            }
        };
        HttpObservable.getObservable(ApiCenter.getApi().getChaptersList(id,String.valueOf(page)),getLifecycleProvider(), FragmentEvent.DESTROY)
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
