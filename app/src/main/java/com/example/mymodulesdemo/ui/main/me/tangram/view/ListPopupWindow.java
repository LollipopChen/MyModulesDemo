package com.example.mymodulesdemo.ui.main.me.tangram.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.libbase.utils.SNStringUtils;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.LayoutPopupWindowBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.adapter.ListAdapter;

import java.util.List;

/**
 * 综合排序弹窗--列表类型
 * @author ChenQiuE
 * @date 2019/5/27
 */
public class ListPopupWindow extends PopupWindow {

    private ListAdapter adapter;
    public ItemClickListener itemClickListener;
    private LayoutPopupWindowBinding binding;
    private View targetView;

    public ListPopupWindow(Context context) {
        this(context,null);
    }

    public ListPopupWindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initWindow(context);
    }

    public void setItemClickListener(View targetView, ItemClickListener itemClickListener) {
        this.targetView = targetView;
        this.itemClickListener = itemClickListener;
    }

    private void initWindow(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater,R.layout.layout_popup_window,null,false);
        //设置popupWindow
        this.setContentView(binding.getRoot());
        //设置宽高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置焦点
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //刷新状态
        this.update();
        //设置背景
        ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.color_black_transparent));
        this.setBackgroundDrawable(colorDrawable);
        this.setAnimationStyle(R.style.popupWindowAnim);

        initEvent(context);
    }

    private void initEvent(Context context) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ListAdapter(R.layout.item_text_view);
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            String item = ((ListAdapter) adapter).getData().get(position);
            if (!SNStringUtils.isEmpty(item)){
                ((ListAdapter) adapter).setSelectCount(position);
                if (itemClickListener != null){
                    itemClickListener.onItemClick(targetView,position);
                }
                this.dismiss();
            }
        });

        binding.spaceView.setOnClickListener(v -> this.dismiss());
    }

    public void setData(List<String> list){
        if (adapter != null){
            adapter.setNewData(list);
        }
    }

    public void setSelectCount(int position){
        if (adapter != null){
            adapter.setSelectCount(position);
        }
    }

    /**
     * 显示popupWindow
     *
     * @param view dependence view
     */
    public void showPopupWindow(View view) {
        if (this.isShowing()) {
            this.dismiss();
        } else {
            this.showAsDropDown(view, 0, 0);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (itemClickListener != null){
            itemClickListener.onItemDismiss(targetView);
        }
    }

    public interface ItemClickListener{
        void onItemClick(View targetView,int position);

        void onItemDismiss(View targetView);
    }
}
