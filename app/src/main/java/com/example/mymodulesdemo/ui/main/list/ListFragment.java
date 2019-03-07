package com.example.mymodulesdemo.ui.main.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentListBinding;

import org.jetbrains.annotations.NotNull;

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
}
