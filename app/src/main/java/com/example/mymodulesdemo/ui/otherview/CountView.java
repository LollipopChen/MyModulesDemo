package com.example.mymodulesdemo.ui.otherview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.LayoutCountViewBinding;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * 加减按钮
 * @author ChenQiuE
 * @date 2019/5/20
 */
public class CountView extends LinearLayout {

    public static final int MIN_COUNT = 0;
    public static final int INIT_COUNT = 1;
    public static final int MAX_COUNT = 10000;

    private int maxCount = MAX_COUNT;
    private int minCount = MIN_COUNT;

    /**
     * 控件资源
     */
    private int minusCan = R.mipmap.ic_minus;
    private int minusNot = R.mipmap.ic_minus_no;
    private int addCan = R.mipmap.ic_add;
    private int addNot = R.mipmap.ic_add_no;
    /**是否支持如输入 默认不支持*/
    private boolean input = false;

    private Context context;
    private LayoutCountViewBinding dataBinding;

    public CountView(Context context) {
        this(context,null);
    }

    public CountView(Context context,AttributeSet attrs) {
        super(context, attrs);

        init(context);
        initEvent();
    }

    private void init(Context context) {
        this.context = context;
        this.setBackgroundResource(android.R.color.transparent);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_count_view, this, true);
    }

    private void initEvent() {
        dataBinding.ivAdd.setOnClickListener(v -> onAddCalculate());
        dataBinding.ivMinus.setOnClickListener(v -> onMinCalculate());

        judgeTheViews(getCount());
    }

    /**
     * 设置按钮图片
     * @param count 数量
     */
    private void judgeTheViews(int count) {
        if (count <= getMinCount()) {
            dataBinding.ivMinus.setImageResource(minusNot);
        } else {
            dataBinding.ivMinus.setImageResource(minusCan);
        }
        if (count >= getMaxCount()) {
            dataBinding.ivAdd.setImageResource(addNot);
        } else {
            dataBinding.ivAdd.setImageResource(addCan);
        }
    }

    /**
     * 计算:加
     */
    private void onAddCalculate(){
        int count = Integer.valueOf(dataBinding.tvCount.getText().toString());
        if (count < getMaxCount()){
            dataBinding.tvCount.setText(String.valueOf(count++));
        }
        judgeTheViews(count);
    }

    /**
     * 计算：减
     */
    private void onMinCalculate(){
        int count = Integer.valueOf(dataBinding.tvCount.getText().toString());
        if (count > getMinCount()){
            dataBinding.tvCount.setText(String.valueOf(count--));
        }
        judgeTheViews(count);
    }

    /**
     * 设置最大值
     * @param maxCount 最大值
     */
    public void setMaxCount(int maxCount) {
        if (maxCount < getMinCount()){
            maxCount = INIT_COUNT;
        }
        this.maxCount = maxCount;
    }

    /**
     * 设置最小值
     * @param minCount 最小值
     */
    public void setMinCount(int minCount) {
        if (minCount > getMaxCount()){
            minCount = INIT_COUNT;
        }
        this.minCount = minCount;
    }

    private int getMaxCount(){
        return maxCount < MAX_COUNT ? maxCount : MAX_COUNT;
    }

    private int getMinCount() {
        return minCount > MIN_COUNT ? minCount : MIN_COUNT;
    }

    public int getCount() {
        String text = dataBinding.tvCount.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return INIT_COUNT;
        }
        return Integer.valueOf(text);
    }

    /**
     * 是否支持输入
     * @param input 是否
     */
    public void setInput(boolean input) {
        this.input = input;
    }

    /**
     * 设置加减控件的资源文件
     */
    public void setButtonRes(int minusCan, int minusNot, int addCan, int addNot) {
        this.minusCan = minusCan;
        this.minusNot = minusNot;
        this.addCan = addCan;
        this.addNot = addNot;
        //给按钮设置默认值
        dataBinding.ivMinus.setImageResource(minusCan);
        dataBinding.ivAdd.setImageResource(addCan);
    }

    /***
     * 设置中间View一些属性
     * @param bgColor 背景色
     * @param textColor 字体颜色  使用默认颜色 给0 即可
     * @param marginLeft marginLeft
     * @param marginRight marginRight
     */
    public void setCountViewAttr(int bgColor, int textColor, int marginLeft, int marginRight) {
        LinearLayout.LayoutParams layoutParams = (LayoutParams) dataBinding.tvCount.getLayoutParams();
        layoutParams.setMargins(DensityUtil.dp2px( marginLeft), 0, DensityUtil.dp2px(marginRight), 0);
        dataBinding.tvCount.setBackgroundColor(getResources().getColor(bgColor));
        if (textColor != 0) {
            dataBinding.tvCount.setTextColor(getResources().getColor(textColor));
        }
    }
}
