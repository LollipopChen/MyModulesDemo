package com.example.mymodulesdemo.ui.main.me.widget.selector;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.libbase.base.BaseActivity;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivitySelectorBinding;

import java.util.Date;

/**
 * 多功能选择器
 * @author ChenQiuE
 * @date 2019/5/16
 */
public class SelectorActivity extends BaseActivity<ActivitySelectorBinding,SelectorViewModel> {
    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_selector;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("多功能选择器");
    }

    @Override
    public void initViewObservable() {
        //时间选择器
        viewModel.uc.onDataClickEvent.observe(this, aVoid -> {
            showDateDialog();
        });
        //地址三级联动选择器

    }

    /**
     * 时间选择器
     */
    private void showDateDialog() {
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> ToastAlert.show(SNStringUtils.getStringDate(date)))
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();
        //底部弹出
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }

        pvTime.show();
    }
}
