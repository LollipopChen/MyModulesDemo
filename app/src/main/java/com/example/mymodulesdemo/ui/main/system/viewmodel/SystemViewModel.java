package com.example.mymodulesdemo.ui.main.system.viewmodel;

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
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.entity.SystemEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.main.system.adapter.SystemItemAdapter;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 导航
 * @author ChenQiuE
 * Date：2019/3/21 11:44
 * Email：1077503420@qq.com
 */
public class SystemViewModel extends LoadingViewModel {

    public ObservableList<SystemItemViewModel> observableList = new ObservableArrayList<>();
    public ItemBinding<SystemItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_system);
    public SystemItemAdapter adapter = new SystemItemAdapter();
    public UiChangeObservable uc = new UiChangeObservable();

    public SystemViewModel(@NonNull Application application) {
        super(application);
    }

    /**下拉刷新*/
    public BindingCommand onRefreshCommand = new BindingCommand(this::requestData);

    public void initToolbar(String title) {
        setTitleText(title);
    }

    @Override
    protected void onRefreshData() {
        setStatus(LOADING);
        requestData();
    }

    /**
     * 获取导航数据
     */
    public void requestData() {

        observableList.clear();

        HttpObserver observer = new HttpObserver() {

            @Override
            public void onComplete() {
                setStatus(STOP_LOADING);
                uc.finishRefreshing.set(!uc.finishRefreshing.get());
            }

            @Override
            protected void onSuccess(Object response) {
                Logger.e("导航数据：" + response.toString());
                SystemEntity systemEntity = SNGsonHelper.toType(response.toString(),SystemEntity.class);
                if (systemEntity == null){
                    setStatus(NO_DATA);
                    return;
                }
                List<SystemEntity.DataEntity> dataList = systemEntity.getData();
                if (dataList == null || dataList.isEmpty()){
                    setStatus(NO_DATA);
                    return;
                }

                for (SystemEntity.DataEntity dataEntity : dataList){
                    SystemItemViewModel systemItemViewModel = new SystemItemViewModel(SystemViewModel.this,dataEntity);
                    observableList.add(systemItemViewModel);
                }
            }

            @Override
            protected void onFailure(ApiException exception) {
                setErrorMessage(exception.getMessage());
            }
        };

        HttpObservable.getObservable(ApiCenter.getApi().getNavigationData(),getLifecycleProvider(), FragmentEvent.DESTROY)
                .subscribe(observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        observableList = null;
        itemBinding = null;
        adapter = null;
        uc = null;
    }

    /**
     * 列表界面变化观察者
     */
    public class UiChangeObservable{
        /**下拉刷新完成*/
        public ObservableBoolean finishRefreshing = new ObservableBoolean(false);
    }
}
