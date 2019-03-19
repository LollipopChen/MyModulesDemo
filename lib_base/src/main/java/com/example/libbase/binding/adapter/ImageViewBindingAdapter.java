package com.example.libbase.binding.adapter;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.libbase.utils.ImageUtils;
import com.example.libbase.utils.SNStringUtils;

/**
 * ImageView 绑定监听
 * @author ChenQiuE
 * Date：2019/3/5 16:35
 * Email：1077503420@qq.com
 */
public class ImageViewBindingAdapter {

    /**
     * 图片加载
     * @param imageView  imageView
     * @param url  链接
     * @param placeholderRes 占位图
     * @param isCircle 是否需要弄成圆形
     */
    @BindingAdapter(value = {"url","placeholderRes","isCircle"},requireAll = false)
    public static void setImage(ImageView imageView, String url, @DrawableRes int placeholderRes,boolean isCircle){
        if(!SNStringUtils.isEmpty(url)){
            if (isCircle){
                ImageUtils.loadImageCircle(imageView,url,placeholderRes);
            }else {
                ImageUtils.loadImage(imageView,url,placeholderRes);
            }
        }
    }
}
