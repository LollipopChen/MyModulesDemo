package com.example.mymodulesdemo.ui.main.me.collection.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.BR;
import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentArticleBinding;

/**
 * 收藏文章列表
 */
public class ArticleFragment extends BaseFragment<FragmentArticleBinding,ArticleViewModel> {

    private static ArticleFragment instance = null;

    public static synchronized ArticleFragment getInstance() {
        if (instance == null) {
            instance = new ArticleFragment();
        }
        return instance;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_article;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
