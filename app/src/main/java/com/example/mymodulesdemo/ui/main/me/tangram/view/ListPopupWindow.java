package com.example.mymodulesdemo.ui.main.me.tangram.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.libbase.utils.SNStringUtils;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.ui.main.me.tangram.adapter.ListAdapter;

import java.util.List;

/**
 * 综合排序弹窗
 * @author ChenQiuE
 * @date 2019/5/27
 */
public class ListPopupWindow extends PopupWindow {

    private ListAdapter adapter;
    public ItemClickListener itemClickListener;

    public ListPopupWindow(Context context) {
        this(context,null);
    }

    public ListPopupWindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.layout_popup_window, null);
        //设置popupWindow
        this.setContentView(contentView);
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

        initEvent(context,contentView);
    }

    private void initEvent(Context context, View contentView) {
        View spaceView = contentView.findViewById(R.id.space_view);

        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ListAdapter(R.layout.item_text_view);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            String item = ((ListAdapter) adapter).getData().get(position);
            if (!SNStringUtils.isEmpty(item)){
                ((ListAdapter) adapter).setSelectCount(position);
                if (itemClickListener != null){
                    itemClickListener.onItemClick(position);
                }
                this.dismiss();
            }
        });

        spaceView.setOnClickListener(v -> this.dismiss());
    }

    public void setData(List<String> list){
        if (adapter != null){
            adapter.setNewData(list);
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

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
