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

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.libbase.base.BaseActivity;
import com.example.libbase.utils.SNStringUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivitySelectorBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 多功能选择器
 * @author ChenQiuE
 * @date 2019/5/16
 */
public class SelectorActivity extends BaseActivity<ActivitySelectorBinding,SelectorViewModel> {
    private int selectOption = 0;
    private int selectFirst = 0;
    private int selectSecond = 0;
    private int selectThird = 0;

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
        viewModel.uc.onAddressClickEvent.observe(this, aVoid -> ToastAlert.show("暂未开放"));
        //农历日期
        viewModel.uc.onLunarClickEvent.observe(this,aVoid -> ToastAlert.show("暂未开放"));
        //单行列表
        viewModel.uc.onListClickEvent.observe(this,aVoid -> showListDialog());
        //多级不联动
        viewModel.uc.onMoreClickEvent.observe(this,aVoid -> showMoreDialog());
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

    /**
     * 单行列表
     * 条件选择器初始化，自定义布局
     */
    private void showListDialog(){
        OptionsPickerView<String> optionsPickerView = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            selectOption = options1;
            ToastAlert.show(getList().get(options1));
        }).setSelectOptions(selectOption).build();
        optionsPickerView.setPicker(getList());
        optionsPickerView.show();
    }

    /**
     * 多个不联动
     */
    private void showMoreDialog(){
        OptionsPickerView<String> optionsPickerView = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            selectFirst = options1;
            selectSecond = options2;
            selectThird = options3;
            ToastAlert.show(getFirstList().get(options1)+ "：" + getSecondList().get(options2) + " is " + getThirdList().get(options3));
        }).build();
        optionsPickerView.setNPicker(getFirstList(),getSecondList(),getThirdList());
        optionsPickerView.setSelectOptions(selectFirst,selectSecond,selectThird);
        optionsPickerView.show();
    }

    private List<String> getList(){
        List<String> mList = new ArrayList<>();
        mList.add("广州");
        mList.add("深圳");
        mList.add("珠海");
        mList.add("汕头");
        mList.add("江门");
        mList.add("惠州");
        return mList;
    }

    private List<String> getFirstList(){
        List<String> list = new ArrayList<>();
        list.add("水果1");
        list.add("水果2");
        list.add("水果3");
        list.add("水果4");
        list.add("水果5");
        return list;
    }

    private List<String> getSecondList(){
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pear");
        list.add("peach");
        list.add("mangle");
        list.add("watermelon");
        return list;
    }

    private List<String> getThirdList(){
        List<String> list = new ArrayList<>();
        list.add("fresh");
        list.add("sweet");
        list.add("sour");
        list.add("good");
        list.add("tasteless");
        return list;
    }
}
