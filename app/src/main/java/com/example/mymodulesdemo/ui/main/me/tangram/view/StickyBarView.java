package com.example.mymodulesdemo.ui.main.me.tangram.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.libbase.json.SNGsonHelper;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.TangramViewConst;
import com.example.mymodulesdemo.databinding.LayoutStickyBarBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.adapter.ListAdapter;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram3.support.SimpleClickSupport;

import java.util.List;

/**
 * 悬浮布局
 * @author ChenQiuE
 * @date 2019/5/22
 */
public class StickyBarView extends LinearLayout implements ITangramViewLifeCycle, SampleScrollSupport.IScrollListener {

    private LayoutStickyBarBinding dataBinding;
    private BaseCell cell;

    public StickyBarView(Context context) {
        this(context, null);
    }

    public StickyBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_sticky_bar, this, true);

        setSortText("综合排序");
        setSortDrawable(context,R.mipmap.ic_down_gray);
        setFiltrateDrawable(context,R.mipmap.ic_filtrate);
    }

    public void setFiltrateDrawable(Context context, @DrawableRes int icon) {
        Drawable drawableFiltrate = ContextCompat.getDrawable(context, icon);
        dataBinding.tvSelector.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableFiltrate, null);
        dataBinding.tvSelector.setCompoundDrawablePadding(4);
    }

    public void setSortDrawable(Context context, @DrawableRes int icon) {
        Drawable drawable = ContextCompat.getDrawable(context, icon);
        dataBinding.tvSort.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        dataBinding.tvSort.setCompoundDrawablePadding(4);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
        this.cell = cell;
        if (cell.serviceManager != null) {
            //滑动
            SampleScrollSupport scrollSupport = cell.serviceManager.getService(SampleScrollSupport.class);
            if (scrollSupport != null){
                scrollSupport.register(this);
            }
            //点击
            SimpleClickSupport support = cell.serviceManager.getService(SimpleClickSupport.class);
            dataBinding.tvSort.setOnClickListener(view ->{
                if (support != null){
                    support.defaultClick(this,cell, TangramViewConst.StickyBarViewConst.SORT);
                }
            });
            dataBinding.tvSell.setOnClickListener(v ->{
                if (support != null){
                    support.defaultClick(this,cell,TangramViewConst.StickyBarViewConst.SELL);
                    setSellTextColor();
                }
            });
            dataBinding.tvDistance.setOnClickListener(v ->{
                if (support != null){
                    support.defaultClick(this,cell,TangramViewConst.StickyBarViewConst.DISTANCE);
                    setDistanceTextColor();
                }
            });
            dataBinding.tvSelector.setOnClickListener(v ->{
                if (support != null){
                    support.defaultClick(this,cell,TangramViewConst.StickyBarViewConst.FILTRATE);
                }
            });
            dataBinding.spaceView.setOnClickListener(null);
        }
    }

    @Override
    public void postBindView(BaseCell cell) {
        Logger.e("sortItems:" + cell.extras.get("sortItems"));
        Logger.e("discountItems:" + cell.extras.get("discountItems"));
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

    public void setSortText(String value){
        dataBinding.tvSort.setText(value);
        dataBinding.tvSort.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        dataBinding.tvSell.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color));
        dataBinding.tvDistance.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color));
    }

    private void setSellTextColor(){
        dataBinding.tvSort.setText("综合排序");
        dataBinding.tvSort.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color));
        dataBinding.tvSell.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        dataBinding.tvDistance.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color));
    }

    private void setDistanceTextColor(){
        dataBinding.tvSort.setText("综合排序");
        dataBinding.tvSort.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color));
        dataBinding.tvSell.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color));
        dataBinding.tvDistance.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
    }
}
