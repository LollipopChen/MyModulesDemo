package com.example.mymodulesdemo.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.libbase.base.BaseActivity;
import com.example.libbase.base.BaseViewModel;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityMainBinding;
import com.example.mymodulesdemo.ui.main.home.HomeFragment;
import com.example.mymodulesdemo.ui.main.list.ListFragment;
import com.example.mymodulesdemo.ui.main.me.MeFragment;
import com.example.mymodulesdemo.ui.main.navigation.NavigationFragment;
import com.example.mymodulesdemo.ui.main.system.SystemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * @author ChenQiuE
 * Date：2019/3/6 13:25
 * Email：1077503420@qq.com
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {

    private MenuItem menuItem;

    /**
     * 初始化根布局
     * @return 布局layout的id
     */
    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    /**
     * 初始化ViewModel的id
     * @return ViewModel的id
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.getInstance());
        fragmentList.add(SystemFragment.getInstance());
        fragmentList.add(ListFragment.getInstance());
        fragmentList.add(NavigationFragment.getInstance());
        fragmentList.add(MeFragment.getInstance());

        viewPagerAdapter.setList(fragmentList);
        binding.viewPager.setOffscreenPageLimit(fragmentList.size());
    }

    /**
     * 初始化界面观察者监听
     */
    @Override
    public void initViewObservable() {
        binding.mainNavigation.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    binding.viewPager.setCurrentItem(0);
                    return true;

                case R.id.action_system:
                    binding.viewPager.setCurrentItem(1);
                    return true;

                case R.id.action_view:
                    binding.viewPager.setCurrentItem(2);
                    return true;

                case R.id.action_navigation:
                    binding.viewPager.setCurrentItem(3);
                    return true;

                case R.id.action_me:
                    binding.viewPager.setCurrentItem(4);
                    return true;

                default:
                    return false;
            }
        });

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    binding.mainNavigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = binding.mainNavigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void setList(List<Fragment> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }
    }

    /*********************** Double click to exit *****************************/
    private boolean isOnKeyBacking;
    protected final Handler mMainLoopHandler = new Handler(Looper.getMainLooper());

    private final Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            isOnKeyBacking = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOnKeyBacking) {
                mMainLoopHandler.removeCallbacks(onBackTimeRunnable);
                isOnKeyBacking = false;
                finish();
            } else {
                isOnKeyBacking = true;
                ToastAlert.show(R.string.toast_double_click_to_exit);
                mMainLoopHandler.postDelayed(onBackTimeRunnable, 1000);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
