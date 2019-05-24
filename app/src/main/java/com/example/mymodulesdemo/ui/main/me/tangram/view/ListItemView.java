package com.example.mymodulesdemo.ui.main.me.tangram.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ItemListBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.structure.view.ITangramViewLifeCycle;

/**
 * @author ChenQiuE
 * @date 2019/5/23
 */
public class ListItemView extends LinearLayout implements ITangramViewLifeCycle, SampleScrollSupport.IScrollListener {

    private ItemListBinding dataBinding;
    private BaseCell cell;

    public ListItemView(Context context) {
        this(context,null);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_list,this,true);
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
       dataBinding.tvTitle.setText((String)cell.extras.get("title"));
       dataBinding.tvContent.setText((String)cell.extras.get("content"));
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
