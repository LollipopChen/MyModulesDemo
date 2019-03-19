package com.example.mymodulesdemo.ui.otherview;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.binding.command.BindingCommand;

/**
 * 返回 + 标题类型的Toolbar
 * 对应include标题的ToolbarViewModel
 * @author ChenQiuE
 * Date：2019/3/6 18:07
 * Email：1077503420@qq.com
 */
public class ToolbarViewModel extends BaseViewModel {

    /** 标题文字 */
    public ObservableField<String> titleText = new ObservableField<>("");
    /** 左边返回图标的观察者 */
    public ObservableInt leftIconVisibleObservable = new ObservableInt(View.INVISIBLE);
    /** 右边图标的观察者 */
    public ObservableInt rightIconVisibleObservable = new ObservableInt(View.INVISIBLE);
    /** 右边图标Icon的观察者 */
    public ObservableField<Drawable> rightIcon = new ObservableField<>();

    public ToolbarViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 设置标题文字
     * @param titleText 标题文字
     */
    public void setTitleText(String titleText) {
        this.titleText.set(titleText);
    }

    /**
     * 设置左边返回按钮是否显示
     * @param visibility 显示状态
     */
    public void setLeftIconVisibleVisible(int visibility){
        leftIconVisibleObservable.set(visibility);
    }

    /**
     * 设置右边按钮是否显示
     * @param visibility 显示状态
     */
    public void setRightIconVisibleVisible(int visibility){
        rightIconVisibleObservable.set(visibility);
    }

    /**
     * 设置右边按钮图标
     * @param icon  图标资源
     */
    public void setRightMoreIcon(Drawable icon){
        rightIcon.set(icon);
    }

    /**
     * 返回按钮点击
     */
    public BindingCommand backOnClick = new BindingCommand(this::finish);

    public BindingCommand moreOnClick = new BindingCommand(this::moreOnClick);

    /**
     * 右边更多点击事件，需要扩展则重写该方法
     */
    protected void moreOnClick(){

    }
}
