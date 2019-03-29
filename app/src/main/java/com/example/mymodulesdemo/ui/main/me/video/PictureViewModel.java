package com.example.mymodulesdemo.ui.main.me.video;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.libbase.base.BaseViewModel;

/**
 * @author ChenQiuE
 * Date：2019/3/29 15:25
 * Email：qiue.chen@supernovachina.com
 */
public class PictureViewModel extends BaseViewModel {

    public ObservableField<String> observableUrl = new ObservableField<>();

    public PictureViewModel(@NonNull Application application) {
        super(application);
    }

    public void setUrl(String url) {
        observableUrl.set(url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        observableUrl = null;
    }
}
