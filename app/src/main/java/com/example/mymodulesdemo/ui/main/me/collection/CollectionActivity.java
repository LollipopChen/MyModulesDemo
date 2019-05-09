package com.example.mymodulesdemo.ui.main.me.collection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.example.libbase.BR;
import com.example.libbase.base.BaseActivity;
import com.example.login.LoginInterceptor;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.RouterManager;
import com.example.mymodulesdemo.databinding.ActivityCollectionBinding;
import com.example.mymodulesdemo.ui.adapter.ViewPagerAdapter;
import com.example.mymodulesdemo.ui.main.me.collection.fragment.ArticleFragment;
import com.example.mymodulesdemo.ui.main.me.collection.fragment.NetAddressFragment;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏
 */
@RouterUri(path = RouterManager.UiConstant.COLLECTION_WITH_LOGIN,interceptors = LoginInterceptor.class)
public class CollectionActivity extends BaseActivity<ActivityCollectionBinding,CollectionViewModel> {

    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_collection;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("我的收藏");

        titles.add("文章");
        titles.add("网站地址");

        fragmentList.add(ArticleFragment.getInstance());
        fragmentList.add(NetAddressFragment.getInstance());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tableLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tableLayout));
    }
}
