package com.example.mymodulesdemo.ui.main.me.tangram.support;

import android.view.View;

import com.example.libbase.widget.toast.ToastAlert;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.support.SimpleClickSupport;

/**
 * @author ChenQiuE
 * @date 2019/5/24
 */
public class SampleClickSupport extends SimpleClickSupport {

    public SampleClickSupport() {
        setOptimizedMode(true);
    }

    @Override
    public void defaultClick(View targetView, BaseCell cell, int eventType) {
        super.defaultClick(targetView, cell, eventType);
    }
}
