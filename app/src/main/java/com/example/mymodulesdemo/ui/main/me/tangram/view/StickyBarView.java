package com.example.mymodulesdemo.ui.main.me.tangram.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ItemMenuViewBinding;
import com.example.mymodulesdemo.databinding.LayoutStickyBarBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.SampleScrollSupport;
import com.orhanobut.logger.Logger;
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
        dataBinding.tvSort.setOnClickListener(v -> ToastAlert.show((String)cell.extras.get("content")));
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
