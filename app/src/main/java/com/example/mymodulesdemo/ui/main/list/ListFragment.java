package com.example.mymodulesdemo.ui.main.list;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.FragmentListBinding;
import com.example.mymodulesdemo.entity.ListTabLayoutEntity;
import com.example.mymodulesdemo.ui.adapter.ViewPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:30
 * Email：1077503420@qq.com
 */
public class ListFragment extends BaseFragment<FragmentListBinding,ListViewModel> {

    public static ListFragment getInstance() {
        return new ListFragment();
    }

    @Override
    public int initContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar();
        viewModel.getTabLayoutTitle();
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.titles.observe(this, itemsEntities -> {
            if (itemsEntities == null){
                return;
            }
            List<Fragment> fragmentList = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            for (int i=0;i<itemsEntities.size();i++){
                ListTabLayoutEntity.ItemsEntity item = itemsEntities.get(i);
                if (item != null){
                    titles.add(item.getName());

                    Bundle bundle = new Bundle();
                    bundle.putString(AppConst.IntentParams.ID,item.getId());
                    ListChildrenFragment listFragment = new ListChildrenFragment();
                    listFragment.setArguments(bundle);
                    fragmentList.add(listFragment);
                }
            }
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),fragmentList,titles);
            binding.viewPager.setAdapter(viewPagerAdapter);
            binding.tableLayout.setupWithViewPager(binding.viewPager);
            binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tableLayout));
            binding.viewPager.setOffscreenPageLimit(fragmentList.size());
        });
    }
}
