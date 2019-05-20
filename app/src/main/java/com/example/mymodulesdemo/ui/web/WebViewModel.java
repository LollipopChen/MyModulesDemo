package com.example.mymodulesdemo.ui.web;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

/**
 * @author ChenQiuE
 * Date：2019/3/12 10:38
 * Email：1077503420@qq.com
 */
public class WebViewModel extends ToolbarViewModel {

    public ObservableField<String> urlObservable = new ObservableField<>("");

    public WebViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolbar() {
        setLeftIconVisible(View.VISIBLE);
        setRightIconVisible(View.INVISIBLE);
    }

    public void setLink(String link) {
        urlObservable.set(link);
    }
}
