package com.example.mymodulesdemo.ui.search;

import android.databinding.ViewDataBinding;

import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ItemHotFlagBinding;
import com.example.mymodulesdemo.ui.search.viewmodel.HotItemViewModel;
import com.orhanobut.logger.Logger;

import java.util.Objects;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * 自定义的RecyclerView Adapter 继承 BindingRecyclerViewAdapter，覆写onBindBinding
 * @author ChenQiuE
 * Date：2019/3/20 13:07
 * Email：1077503420@qq.com
 */
public class HotFlagAdapter extends BindingRecyclerViewAdapter<HotItemViewModel> {

    private String selectId;

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, HotItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        /*
         * TODO 添加自己需要的逻辑
         */
        if (Objects.requireNonNull(item.observableField.get()).getId().equals(selectId)){
            ((ItemHotFlagBinding)binding).textView.setBackgroundResource(R.drawable.bg_ellipse_gradient);
        }else {
            ((ItemHotFlagBinding)binding).textView.setBackgroundResource(R.drawable.bg_ellipse_gray);
        }
    }

    public void setSelectId(String id) {
        selectId = id;
        notifyDataSetChanged();
    }
}
