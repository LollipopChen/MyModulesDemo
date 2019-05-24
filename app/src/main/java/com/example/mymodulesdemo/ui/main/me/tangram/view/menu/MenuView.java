package com.example.mymodulesdemo.ui.main.me.tangram.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.structure.view.ITangramViewLifeCycle;

import java.util.ArrayList;
import java.util.List;

public class MenuView extends ConstraintLayout implements ITangramViewLifeCycle,ViewPager.OnPageChangeListener,SampleScrollSupport.IScrollListener, OnMenuItemClickListener {

    private ViewPager viewPager;
    private MenuViewPagerAdapter adapter;
    private LinearLayout indicatorLayout;
    private List<ImageView> indicatorImages;
    private int indicatorMargin;
    private int lastPosition;
    private BaseCell cell;

    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.menu_view, this);
        viewPager = findViewById(R.id.menu_view_pager);
        indicatorLayout = findViewById(R.id.menu_indicator);
        indicatorMargin = getResources().getDimensionPixelSize(R.dimen.dimen_3_dp);

        adapter = new MenuViewPagerAdapter();
        viewPager.setAdapter(adapter);
        indicatorImages = new ArrayList<>();
        viewPager.addOnPageChangeListener(this);
    }

    private void createIndicators() {
        indicatorImages.clear();
        indicatorLayout.removeAllViews();
        lastPosition = 0;
        if (adapter.getCount() <= 1) {
            indicatorLayout.setVisibility(GONE);
            return;
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = indicatorMargin;
            params.rightMargin = indicatorMargin;
            imageView.setLayoutParams(params);

            if (i == 0) {
                imageView.setImageResource(R.drawable.banner_green_radius);
            } else {
                imageView.setImageResource(R.drawable.banner_gray_radius);
            }
            indicatorImages.add(imageView);
            indicatorLayout.addView(imageView);
        }
        indicatorLayout.setVisibility(VISIBLE);
    }

    @Override public void onPageScrolled(int position, float positionOffset, int
            positionOffsetPixels) {
    }

    @Override public void onPageSelected(int position) {
        if (indicatorImages == null || indicatorImages.isEmpty()) {
            return;
        }
        indicatorImages.get(lastPosition).setImageResource(R.drawable.banner_gray_radius);
        indicatorImages.get(position).setImageResource(R.drawable.banner_green_radius);
        lastPosition = position;
    }

    @Override public void onPageScrollStateChanged(int state) {

    }

    @Nullable @Override protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
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
        Object items = cell.extras.get("items");
        Logger.e("BaseCell: " + items);
        if (items == null){
            return;
        }
        List<MenuEntity> list = SNGsonHelper.toList("" + items,new TypeToken<List<MenuEntity>>(){});
        if (list == null || list.isEmpty()){
            return;
        }
        adapter.setOnMenuItemClickListener(this);
        adapter.setDataList(list);
        adapter.notifyDataSetChanged();
        createIndicators();
        viewPager.setCurrentItem(0);
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

    @Override
    public void onMenuItemClick(int position, MenuEntity item) {
        ToastAlert.show("在MenuView中onMenuItemClick" + item.getTitle());
    }
}
