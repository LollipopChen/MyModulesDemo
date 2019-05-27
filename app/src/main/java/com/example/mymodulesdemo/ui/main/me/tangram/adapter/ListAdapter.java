package com.example.mymodulesdemo.ui.main.me.tangram.adapter;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodulesdemo.R;

/**
 * 综合排序列表
 * @author ChenQiuE
 * @date 2019/5/27
 */
public class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int selectPosition = 0;

    public ListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text_view,item);

        if (selectPosition == helper.getLayoutPosition()){
            helper.setTextColor(R.id.text_view, ContextCompat.getColor(mContext,R.color.colorPrimary));
        }else {
            helper.setTextColor(R.id.text_view, ContextCompat.getColor(mContext,R.color.text_color));
        }
    }

    public void setSelectCount(int position){
        this.selectPosition = position;
        notifyDataSetChanged();
    }
}
