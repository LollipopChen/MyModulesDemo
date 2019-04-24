package com.example.mymodulesdemo.ui.main.me.setting;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

/**
 * 设置
 * @author ChenQiuE
 * Date：2019/4/10 11:13
 * Email：qiue.chen@supernovachina.com
 */
public class SettingViewModel extends ToolbarViewModel {

    public UiChangeObservable uc = new UiChangeObservable();

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title){
        setLeftIconVisibleVisible(View.VISIBLE);
        setTitleText(title);
    }

    /**
     * 语言设置
     */
    public BindingCommand languageChange = new BindingCommand(() -> uc.languageEvent.call());

    public class UiChangeObservable{
        public SingleLiveEvent languageEvent = new SingleLiveEvent();
    }
}
