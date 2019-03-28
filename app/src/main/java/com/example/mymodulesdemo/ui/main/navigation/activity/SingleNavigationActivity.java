package com.example.mymodulesdemo.ui.main.navigation.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.ActivityNavagationLayoutBinding;
import com.example.mymodulesdemo.entity.ListTabLayoutEntity;
import com.example.mymodulesdemo.entity.NavigationEntity;
import com.example.mymodulesdemo.ui.adapter.ViewPagerAdapter;
import com.example.mymodulesdemo.ui.main.list.ListChildrenFragment;
import com.example.mymodulesdemo.ui.main.navigation.viewmodel.SingleNavigationViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个体系
 * @author ChenQiuE
 * Date：2019/3/28 10:32
 * Email：qiue.chen@supernovachina.com
 */
public class SingleNavigationActivity extends BaseActivity<ActivityNavagationLayoutBinding, SingleNavigationViewModel> {

    private String title;
    private ArrayList<NavigationEntity.ItemEntity> dataList;

    @Override
    public void initParams() {
        title = getIntent().getStringExtra(AppConst.IntentParams.NAVIGATION);
        dataList = getIntent().getParcelableArrayListExtra(AppConst.IntentParams.NAVIGATION_TAB);
    }

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_navagation_layout;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar(title);

        initLayoutViewpager();
    }

    private void initLayoutViewpager() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i=0;i<dataList.size();i++){
            NavigationEntity.ItemEntity item = dataList.get(i);
            if (item != null){
                titles.add(item.getName());

                Bundle bundle = new Bundle();
                bundle.putString(AppConst.IntentParams.ID,item.getId());
                SingleNavigationFragment singleNavigationFragment = new SingleNavigationFragment();
                singleNavigationFragment.setArguments(bundle);
                fragmentList.add(singleNavigationFragment);
            }
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList,titles);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tableLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tableLayout));
        binding.viewPager.setOffscreenPageLimit(fragmentList.size());
    }
}
