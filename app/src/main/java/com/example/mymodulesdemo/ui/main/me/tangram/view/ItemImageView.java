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
import com.example.mymodulesdemo.console.TangramViewConst;
import com.example.mymodulesdemo.databinding.ItemMenuViewBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.orhanobut.logger.Logger;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.structure.view.ITangramViewLifeCycle;
import com.tmall.wireless.tangram3.support.SimpleClickSupport;

/**
 * @author ChenQiuE
 * @date 2019/5/22
 */
public class ItemImageView extends LinearLayout implements ITangramViewLifeCycle , SampleScrollSupport.IScrollListener {

    private ItemMenuViewBinding dataBinding;
    private BaseCell cell;

    public ItemImageView(Context context) {
        this(context,null);
    }

    public ItemImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_menu_view, this, true);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
        this.cell = cell;
        if (cell.serviceManager != null){
            SampleScrollSupport scrollSupport = cell.serviceManager.getService(SampleScrollSupport.class);
            if (scrollSupport != null){
                scrollSupport.register(this);
            }
        }
    }

    @Override
    public void postBindView(BaseCell cell) {
        Glide.with(this.getContext())
                .load((String)cell.extras.get("imgUrl"))
                .into(dataBinding.ivMenu);
    }

    @Override
    public void postUnBindView(BaseCell cell) {
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Logger.e("MenuView:onScrollStateChanged" );

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Logger.e("MenuView:onScrolled" );
    }
}
