package com.example.mymodulesdemo.ui.main.me.widget.selector;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

/**
 * 多功能选择器
 *
 * @author ChenQiuE
 * @date 2019/5/16
 */
public class SelectorViewModel extends ToolbarViewModel {

    public UiChangeObservable uc = new UiChangeObservable();

    public SelectorViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setLeftIconVisible(View.VISIBLE);
        setRightIconVisible(View.INVISIBLE);
        setTitleText(title);
    }

    public BindingCommand onDataClick = new BindingCommand(() -> uc.onDataClickEvent.call());

    public BindingCommand onAddressClick = new BindingCommand(()-> uc.onAddressClickEvent.call());

    public BindingCommand onLunarClick = new BindingCommand(() -> uc.onLunarClickEvent.call());

    public BindingCommand onListClick = new BindingCommand(() -> uc.onListClickEvent.call());

    public BindingCommand onMoreClick = new BindingCommand(() -> uc.onMoreClickEvent.call());

    public class UiChangeObservable{
         SingleLiveEvent<Void> onDataClickEvent = new SingleLiveEvent<>();
         SingleLiveEvent<Void> onAddressClickEvent = new SingleLiveEvent<>();
         SingleLiveEvent<Void> onLunarClickEvent = new SingleLiveEvent<>();
         SingleLiveEvent<Void> onListClickEvent = new SingleLiveEvent<>();
         SingleLiveEvent<Void> onMoreClickEvent = new SingleLiveEvent<>();
    }
}
