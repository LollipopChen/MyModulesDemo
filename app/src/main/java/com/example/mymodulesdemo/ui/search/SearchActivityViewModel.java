package com.example.mymodulesdemo.ui.search;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.libbase.widget.toast.ToastAlert;
import com.example.mymodulesdemo.ui.otherview.SearchBarViewModel;

/**
 * 搜索
 * @author ChenQiuE
 * Date：2019/3/19 17:52
 * Email：1077503420@qq.com
 */
public class SearchActivityViewModel extends SearchBarViewModel {

    public SearchActivityViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    protected void onSearch() {
        ToastAlert.show(searchKeyWord.get());
    }
}
