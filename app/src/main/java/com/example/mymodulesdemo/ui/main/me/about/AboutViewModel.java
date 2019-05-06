package com.example.mymodulesdemo.ui.main.me.about;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

/**
 * 关于
 * @author ChenQiuE
 * Date：2019/3/7 16:02
 * Email：1077503420@qq.com
 */
public class AboutViewModel extends ToolbarViewModel {

    public ObservableField<String> appName = new ObservableField<>();
    public ObservableField<String> appVersion = new ObservableField<>();

    public AboutViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setTitleText(title);
        setLeftIconVisibleVisible(View.VISIBLE);
        setRightIconVisibleVisible(View.INVISIBLE);
    }

    public void setAppName(String applicationName) {
        this.appName.set(applicationName);
    }

    public void setAppVersion(String applicationVersionCode) {
        this.appVersion.set(applicationVersionCode);
    }
}
