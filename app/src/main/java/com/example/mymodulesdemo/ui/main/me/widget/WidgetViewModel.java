package com.example.mymodulesdemo.ui.main.me.widget;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.binding.command.BindingCommand;
import com.example.mymodulesdemo.ui.main.me.widget.count.CountActivity;
import com.example.mymodulesdemo.ui.main.me.widget.selector.SelectorActivity;
import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

/**
 * 控件
 * @author ChenQiuE
 * @date 2019/5/10
 */
public class WidgetViewModel extends ToolbarViewModel {

    public WidgetViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title) {
        setLeftIconVisible(View.VISIBLE);
        setRightIconVisible(View.INVISIBLE);
        setTitleText(title);
    }

    /**
     * 多功能选择器
     */
    public BindingCommand onSelectorClick = new BindingCommand(() -> startActivity(SelectorActivity.class));

    /**
     * 数字控件
     */
    public BindingCommand onCountViewClick = new BindingCommand(() -> startActivity(CountActivity.class));
}
