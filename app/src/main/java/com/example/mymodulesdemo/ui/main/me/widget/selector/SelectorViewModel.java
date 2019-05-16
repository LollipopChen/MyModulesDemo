package com.example.mymodulesdemo.ui.main.me.widget.selector;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.bus.event.SingleLiveEvent;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

import java.util.Date;

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

    public class UiChangeObservable{
         SingleLiveEvent<Void> onDataClickEvent = new SingleLiveEvent<>();
         SingleLiveEvent<Void> onAddressClickEvent = new SingleLiveEvent<>();
    }
}
