package com.example.mymodulesdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/13 16:50
 * Email：1077503420@qq.com
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    /**ViewPager要填充的fragment列表*/
    private List<Fragment> list;
    /**tab中的title文字列表*/
    private List<String> title;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
