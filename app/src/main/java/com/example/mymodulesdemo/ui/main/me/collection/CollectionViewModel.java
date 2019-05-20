package com.example.mymodulesdemo.ui.main.me.collection;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mymodulesdemo.ui.otherview.viewmodel.ToolbarViewModel;

public class CollectionViewModel extends ToolbarViewModel {

    public CollectionViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar(String title){
        setLeftIconVisible(View.VISIBLE);
        setRightIconVisible(View.INVISIBLE);
        setTitleText(title);
    }

}
