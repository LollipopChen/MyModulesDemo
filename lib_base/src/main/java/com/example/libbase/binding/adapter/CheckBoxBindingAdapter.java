package com.example.libbase.binding.adapter;

import android.databinding.BindingAdapter;
import android.widget.CheckBox;

import com.example.libbase.binding.command.BindingCommand;

/**
 * CheckBox 绑定监听
 * @author ChenQiuE
 * Date：2019/3/5 16:11
 * Email：1077503420@qq.com
 */
public class CheckBoxBindingAdapter {

    /**
     * requireAll 属性为false，表示在XML中，属性可以不用全部赋值，若是true，则aonCheckedChanged属性都要赋值，如果不设置，默认是true。
     * @param checkBox  checkBox
     * @param bindingCommand bindingCommand
     */
    @BindingAdapter(value = {"onCheckedChanged"},requireAll = false)
    public static void setOnCheckedChanged(CheckBox checkBox, BindingCommand<Boolean> bindingCommand){
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> bindingCommand.execute(isChecked));
    }
}
