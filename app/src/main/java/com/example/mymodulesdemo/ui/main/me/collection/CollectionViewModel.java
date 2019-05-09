package com.example.mymodulesdemo.ui.main.me.collection;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.ToolbarViewModel;

public class CollectionViewModel extends ToolbarViewModel {

    public CollectionViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title){
        setLeftIconVisibleVisible(View.VISIBLE);
        setRightIconVisibleVisible(View.INVISIBLE);
        setTitleText(title);
    }


}
