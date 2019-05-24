package com.example.mymodulesdemo.ui.main.me.tangram.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.LayoutStickyBarBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.structure.view.ITangramViewLifeCycle;

/**
 * @author ChenQiuE
 * @date 2019/5/22
 */
public class StickyBarView extends LinearLayout implements ITangramViewLifeCycle , SampleScrollSupport.IScrollListener {

    private LayoutStickyBarBinding dataBinding;
    private BaseCell cell;

    public StickyBarView(Context context) {
        this(context,null);
    }

    public StickyBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_sticky_bar, this, true);

        Drawable drawable = ContextCompat.getDrawable(context,R.mipmap.ic_down_gray);
        dataBinding.tvSort.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
        dataBinding.tvSort.setCompoundDrawablePadding(4);

        Drawable drawableFiltrate = ContextCompat.getDrawable(context,R.mipmap.ic_filtrate);
        dataBinding.tvSelector.setCompoundDrawablesWithIntrinsicBounds(null,null,drawableFiltrate,null);
        dataBinding.tvSelector.setCompoundDrawablePadding(4);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
        this.cell = cell;
        if (cell.serviceManager != null){
            SampleScrollSupport scrollSupport = cell.serviceManager.getService(SampleScrollSupport.class);
            scrollSupport.register(this);
        }
    }

    @Override
    public void postBindView(BaseCell cell) {
        dataBinding.tvSort.setOnClickListener(v -> ToastAlert.show("综合排序"));
        dataBinding.tvSell.setOnClickListener(v -> ToastAlert.show("销量"));
        dataBinding.tvDistance.setOnClickListener(v -> ToastAlert.show("距离"));
        dataBinding.tvSelector.setOnClickListener(v -> ToastAlert.show("筛选"));
    }

    @Override
    public void postUnBindView(BaseCell cell) {
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }
}
