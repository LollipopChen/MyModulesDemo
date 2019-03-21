package com.example.mymodulesdemo.ui.search.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.entity.HotSearchEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.otherview.SearchBarViewModel;
import com.example.mymodulesdemo.ui.search.HotFlagAdapter;
import com.example.mymodulesdemo.ui.search.SearchResultActivity;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;
import java.util.Objects;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 搜索
 * @author ChenQiuE
 * Date：2019/3/19 17:52
 * Email：1077503420@qq.com
 */
public class SearchActivityViewModel extends SearchBarViewModel {

    public ObservableList<HotItemViewModel> observableList = new ObservableArrayList<>();
    public ItemBinding<HotItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_hot_flag);
    /**RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象*/
    public HotFlagAdapter adapter = new HotFlagAdapter();

    public SearchActivityViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onSearch() {
        Bundle bundle = new Bundle();
        bundle.putString(AppConst.IntentParams.KEY_WORD, Objects.requireNonNull(searchKeyWord.get()));
        startActivity(SearchResultActivity.class,bundle);
        adapter.setSelectId("");
    }

    /**
     * 获取热门搜索词
     */
    public void getHotFlagList() {
        HttpObserver observer = new HttpObserver() {
            @Override
            protected void onSuccess(Object response) {
                Logger.e("热门：" + response.toString());
                HotSearchEntity hotEntity = SNGsonHelper.toType(response.toString(), HotSearchEntity.class);
                if (hotEntity == null){
                    return;
                }
                List<HotSearchEntity.ItemEntity> data = hotEntity.getData();
                if (data == null || data.isEmpty()){
                    return;
                }
                for (HotSearchEntity.ItemEntity itemEntity : data){
                    HotItemViewModel hotItemViewModel = new HotItemViewModel(SearchActivityViewModel.this,itemEntity);
                    observableList.add(hotItemViewModel);
                }
            }

            @Override
            protected void onFailure(ApiException exception) {
                showDialog(exception.getMsg());
            }
        };

        HttpObservable.getObservable(ApiCenter.getApi().getHotFlag(),getLifecycleProvider(), ActivityEvent.DESTROY)
                .subscribe(observer);
    }
}
