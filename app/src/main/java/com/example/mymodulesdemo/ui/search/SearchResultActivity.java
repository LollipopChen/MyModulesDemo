package com.example.mymodulesdemo.ui.search;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.ActivitySearchResultBinding;
import com.example.mymodulesdemo.ui.search.viewmodel.SearchResultViewModel;

/**
 * @author ChenQiuE
 * Date：2019/3/20 16:29
 * Email：1077503420@qq.com
 */
public class SearchResultActivity extends BaseActivity<ActivitySearchResultBinding, SearchResultViewModel> {

    private String keyWord;

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_search_result;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParams() {
        keyWord = getIntent().getStringExtra(AppConst.IntentParams.KEY_WORD);
    }

    @Override
    public void initData() {
        viewModel.initTooBar(keyWord);
        viewModel.requestListData();
    }
}
