package com.example.mymodulesdemo.ui.main.list;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ApiException;
import com.example.libbase.net.http.observer.HttpObservable;
import com.example.libbase.net.http.observer.HttpObserver;
import com.example.mymodulesdemo.entity.ListTabLayoutEntity;
import com.example.mymodulesdemo.net.ApiCenter;
import com.example.mymodulesdemo.ui.otherview.LoadingViewModel;
import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:29
 * Email：1077503420@qq.com
 */
public class ListViewModel extends ToolbarViewModel {

    public UiChangeObservable uc = new UiChangeObservable();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 获取标题数据
     */
    public void getTabLayoutTitle() {
        HttpObserver observer = new HttpObserver() {
            @Override
            protected void onSuccess(Object response) {
                Logger.e("公众号数据：" + response.toString());
                ListTabLayoutEntity listTabLayoutEntity = SNGsonHelper.toType(response.toString(),ListTabLayoutEntity.class);
                if (listTabLayoutEntity == null){
                    return;
                }
                List<ListTabLayoutEntity.ItemsEntity> data = listTabLayoutEntity.getData();
                if (data == null || data.isEmpty()){
                    return;
                }
                uc.titles.setValue(data);
            }

            @Override
            protected void onFailure(ApiException exception) {

            }
        };

        HttpObservable.getObservable(ApiCenter.getApi().getChapters(),getLifecycleProvider(), FragmentEvent.DESTROY)
                .subscribe(observer);
    }

    public class UiChangeObservable{
        public SingleLiveEvent<List<ListTabLayoutEntity.ItemsEntity>> titles = new SingleLiveEvent<>();
    }

}
