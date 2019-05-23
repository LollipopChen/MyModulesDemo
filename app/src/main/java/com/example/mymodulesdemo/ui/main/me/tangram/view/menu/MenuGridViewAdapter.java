/*
 * iFoodMacau4.0
 * MenuGridViewAdapter
 * Created by xiewenyong on 2018-07-31.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.mymodulesdemo.ui.main.me.tangram.view.menu;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymodulesdemo.R;

import java.util.List;

public class MenuGridViewAdapter extends BaseAdapter {

    private List<MenuEntity> list;
    private OnMenuItemClickListener onMenuItemClickListener;

    MenuGridViewAdapter(@Nullable OnMenuItemClickListener onMenuItemClickListener, List<MenuEntity> list) {
        this.list = list;
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,
                    parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MenuEntity item = list.get(position);
        String thumb = item.getThumb();
        String title = item.getTitle();
        holder.tv.setText(title);
        Glide.with(parent.getContext())
                .load(thumb)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .transform(new CircleCrop()))
                .into(holder.iv);

        convertView.setOnClickListener(v -> {
            //TODO menu点击
            onMenuItemClickListener.onMenuItemClick(position, item);
        });

        return convertView;
    }

    class ViewHolder {

        ImageView iv;
        TextView tv;

        ViewHolder(@NonNull View itemView) {
            iv = itemView.findViewById(R.id.menu_item_iv);
            tv = itemView.findViewById(R.id.menu_item_tv);
        }
    }

}
