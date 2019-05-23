

package com.example.mymodulesdemo.ui.main.me.tangram.view.menu;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.mymodulesdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MenuViewPagerAdapter extends PagerAdapter {

    private static final int ITEM_COUNT_PER_PAGE = 8;
    private List<MenuEntity> dataList;
    private List<MenuGridViewAdapter> adapters = new ArrayList<>();
    private OnMenuItemClickListener onMenuItemClickListener;

    void setDataList(List<MenuEntity> list) {
        this.dataList = list;
        adapters.clear();
    }

    void setOnMenuItemClickListener(@NonNull OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @NonNull @Override public Object instantiateItem(@NonNull ViewGroup container, int position) {
        GridView gridView = new WrapContentGridView(container.getContext());
        gridView.setNumColumns(4);
        int verticalSpacing = container.getContext().getResources().getDimensionPixelSize(R.dimen.dimen_16_dp);
        gridView.setVerticalSpacing(verticalSpacing);
        gridView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int fromIndex = position * ITEM_COUNT_PER_PAGE;
        int toIndex;
        if (position == getCount() - 1) {
            toIndex = dataList.size();
        } else {
            toIndex = fromIndex + ITEM_COUNT_PER_PAGE;
        }
        List<MenuEntity> list = dataList.subList(fromIndex, toIndex);
        MenuGridViewAdapter adapter = new MenuGridViewAdapter(onMenuItemClickListener, list);
        gridView.setAdapter(adapter);
        adapters.add(adapter);
        adapter.notifyDataSetChanged();
        container.addView(gridView);
        return gridView;
    }

    @Override public int getCount() {
        int count = dataList == null ? 0 : dataList.size();
        if (count == 0) {
            return 0;
        }
        return count % ITEM_COUNT_PER_PAGE == 0 ? count / ITEM_COUNT_PER_PAGE : count / ITEM_COUNT_PER_PAGE + 1;
    }

    @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        if (position < adapters.size()) {
            adapters.remove(position);
        }
    }

    @Override public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

        for (MenuGridViewAdapter adapter : adapters) {
            adapter.notifyDataSetChanged();
        }
    }
}
