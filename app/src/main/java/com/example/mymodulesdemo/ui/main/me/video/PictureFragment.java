package com.example.mymodulesdemo.ui.main.me.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.libbase.utils.SNStringUtils;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.databinding.FragmentPictureBinding;

import org.jetbrains.annotations.NotNull;

/**
 * @author ChenQiuE
 * Date：2019/3/29 15:24
 * Email：qiue.chen@supernovachina.com
 */
public class PictureFragment extends BaseFragment<FragmentPictureBinding,PictureViewModel> {

    private String url;

    @Override
    public void initParams() {
        Bundle bundle = getArguments();
        if (bundle != null){
            url = bundle.getString(AppConst.IntentParams.URL);
        }
    }

    @Override
    public int initContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_picture;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        if (!SNStringUtils.isEmpty(url)){
            viewModel.setUrl(url);
        }
    }
}
