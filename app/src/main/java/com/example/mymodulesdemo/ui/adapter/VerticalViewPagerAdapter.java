package com.example.mymodulesdemo.ui.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.ui.main.me.video.PictureFragment;
import java.util.List;

/**
 * 上下翻页viewpager适配器
 * @author ChenQiuE
 * Date：2019/3/29 15:21
 * Email：qiue.chen@supernovachina.com
 */
public class VerticalViewPagerAdapter extends PagerAdapter {

    private FragmentManager fragmentManager;
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem = null;
    private List<String> urlList;

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }


    public VerticalViewPagerAdapter(FragmentManager fm) {
        this.fragmentManager = fm;
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @SuppressLint("CommitTransaction")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        if (mCurTransaction == null) {
            mCurTransaction = fragmentManager.beginTransaction();
        }

        PictureFragment fragment = new PictureFragment();
        if (urlList != null && urlList.size() > 0) {
            Bundle bundle = new Bundle();
            if (position >= urlList.size()) {
                bundle.putString(AppConst.IntentParams.URL, urlList.get(position % urlList.size()));
            } else {
                bundle.putString(AppConst.IntentParams.URL, urlList.get(position));
            }
            fragment.setArguments(bundle);
        }


        mCurTransaction.add(container.getId(), fragment,
                makeFragmentName(container.getId(), position));
        fragment.setUserVisibleHint(false);

        return fragment;
    }


    @SuppressLint("CommitTransaction")
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = fragmentManager.beginTransaction();
        }
        mCurTransaction.detach((Fragment) object);
        mCurTransaction.remove((Fragment) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((Fragment) object).getView() == view;
    }

    private String makeFragmentName(int viewId, int position) {
        return "android:switcher:" + viewId + position;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }
}
