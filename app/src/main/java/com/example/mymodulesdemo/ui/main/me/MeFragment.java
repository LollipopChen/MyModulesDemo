package com.example.mymodulesdemo.ui.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentMeBinding;
import com.example.mymodulesdemo.entity.UserInfoEntity;

import org.jetbrains.annotations.NotNull;

/**
 * @author ChenQiuE
 * Date：2019/3/6 16:34
 * Email：1077503420@qq.com
 */
public class MeFragment extends BaseFragment<FragmentMeBinding,MeViewModel> {

    public static MeFragment getInstance(){
        return new MeFragment();
    }

    @Override
    public int initContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_me;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserName("MvvM DEMO");
        userInfoEntity.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551954435661&di=01620611af17a4feafb940171167fda0&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181204%2Fbfef8528ad444fe6a6cbf22da6a690ac.jpeg");

        viewModel.setUserInfoEntity(userInfoEntity);
    }
}
