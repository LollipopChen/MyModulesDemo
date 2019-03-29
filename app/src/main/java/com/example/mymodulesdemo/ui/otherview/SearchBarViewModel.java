package com.example.mymodulesdemo.ui.otherview;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.widget.toast.ToastAlert;

/**
 * 返回 + 搜索 的SearchToolBar
 * 使用SearchToolBar的UI需要继承SearchBarViewModel
 * @author ChenQiuE
 * Date：2019/3/19 17:20
 * Email：1077503420@qq.com
 */
public class SearchBarViewModel extends BaseViewModel {

    /** 左边返回图标的观察者 */
    public ObservableInt leftIconVisibleObservable = new ObservableInt(View.VISIBLE);
    /**输入框*/
    public ObservableField<String> searchKeyWord = new ObservableField<>("");

    public SearchBarViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 设置左边返回按钮是否显示
     * @param visibility 显示状态
     */
    public void setLeftIconVisibleVisible(int visibility){
        leftIconVisibleObservable.set(visibility);
    }

    /**
     * 返回按钮点击
     */
    public BindingCommand backOnClick = new BindingCommand(this::finish);

    /**
     * 搜索
     */
    public BindingCommand searchOnClick = new BindingCommand(this::onSearch);

    protected void onSearch(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        leftIconVisibleObservable = null;
        searchKeyWord = null;
        backOnClick = null;
        searchOnClick = null;
    }
}
