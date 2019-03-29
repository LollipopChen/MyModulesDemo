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
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.entity.NavigationEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.main.navigation.adapter.NavigationItemAdapter;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 体系
 * @author ChenQiuE
 * Date：2019/3/21 11:43
 * Email：1077503420@qq.com
 */
public class NavigationViewModel extends LoadingViewModel {

    public UiChangeObservable uc = new UiChangeObservable();
    public ObservableList<NavigationItemViewModel> observableList = new ObservableArrayList<>();
    public ItemBinding<NavigationItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_navigation);
    public NavigationItemAdapter adapter = new NavigationItemAdapter();

    public NavigationViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setTitleText(title);
    }

    public BindingCommand onRefreshCommand = new BindingCommand(this::requestData);

    @Override
    protected void onRefreshData() {
        requestData();
    }

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
                Logger.e("体系数据：" + response.toString());
                NavigationEntity navigationEntity = SNGsonHelper.toType(response.toString(),NavigationEntity.class);
                if (navigationEntity == null){
                    setStatus(NO_DATA);
                    return;
                }
                List<NavigationEntity.DataEntity> data = navigationEntity.getData();
                if (data == null || data.isEmpty()){
                    setStatus(NO_DATA);
                    return;
                }
                for (NavigationEntity.DataEntity dataEntity : data){
                    NavigationItemViewModel itemViewModel = new NavigationItemViewModel(NavigationViewModel.this,dataEntity);
                    observableList.add(itemViewModel);
                }
            }

            @Override
            protected void onFailure(ApiException exception) {
                setErrorMessage(exception.getMessage());
            }
        };

        HttpObservable.getObservable(ApiCenter.getApi().getSystemData(),getLifecycleProvider(),
                FragmentEvent.DESTROY).subscribe(observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        observableList =  null;
        itemBinding =  null;
        adapter =  null;
        onRefreshCommand =  null;
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
