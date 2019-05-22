package com.example.mymodulesdemo.ui.main.me.tangram;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ItemImageViewBinding;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.structure.view.ITangramViewLifeCycle;

/**
 * @author ChenQiuE
 * @date 2019/5/22
 */
public class BannerView extends LinearLayout implements ITangramViewLifeCycle,SampleScrollSupport.IScrollListener {

    private ItemImageViewBinding dataBinding;
    private BaseCell cell;
    private Context context;

    public BannerView(@NonNull Context context) {
        this(context,null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_image_view,this,true);
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
        Glide.with(context)
                .load((String)cell.extras.get("imgUrl"))
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher))
                .into(dataBinding.imageView);
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
