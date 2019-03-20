package com.example.mymodulesdemo.ui.search.viewmodel;

import android.support.annotation.NonNull;

import com.example.libbase.base.ItemViewModel;
import com.example.mymodulesdemo.entity.SearchResultEntity;

/**
 * 搜索结果
 * @author ChenQiuE
 * Date：2019/3/20 17:15
 * Email：1077503420@qq.com
 */
public class SearchResultItemViewModel extends ItemViewModel<SearchResultViewModel> {

    public SearchResultItemViewModel(@NonNull SearchResultViewModel viewModel, SearchResultEntity.ItemEntity entity) {
        super(viewModel);
    }
}
