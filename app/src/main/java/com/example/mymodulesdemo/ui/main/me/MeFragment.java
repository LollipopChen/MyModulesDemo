package com.example.mymodulesdemo.ui.main.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.base.BaseFragment;
import com.example.libbase.event.BaseEntityEvent;
import com.example.libbase.event.BaseRefreshDataEvent;
import com.example.login.LoginCenter;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.center.UserCenter;
import com.example.mymodulesdemo.console.AppConst;
import com.example.mymodulesdemo.console.RouterManager;
import com.example.mymodulesdemo.databinding.FragmentMeBinding;
import com.example.mymodulesdemo.entity.UserInfoEntity;
import com.example.register.RegisterLoginEntity;
import com.sankuai.waimai.router.annotation.RouterUri;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 我的
 * @author ChenQiuE
 * Date：2019/3/6 16:34
 * Email：1077503420@qq.com
 */
@RouterUri(path = RouterManager.UiConstant.ME_FRAGMENT)
public class MeFragment extends BaseFragment<FragmentMeBinding,MeViewModel>{

    private static MeFragment instance = null;

    public static synchronized MeFragment getInstance() {
        if (instance == null) {
            instance = new MeFragment();
        }
        return instance;
    }

    @Override
    public int initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_me;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        if (LoginCenter.getInstance().isLogin()){
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserName(UserCenter.getInstance().getUserName());
            userInfoEntity.setImage(UserCenter.getInstance().getUserAvatar());
            viewModel.setUserInfoEntity(userInfoEntity);

            binding.tvTip.setText(UserCenter.getInstance().getDescription());
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    /**
     * 使用EventBus接收LoginModule傳來的數據，登錄成功后的操作
     * @param event event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(BaseEntityEvent<RegisterLoginEntity.DataEntity> event){
        RegisterLoginEntity.DataEntity entity = event.getEntity();
        if (entity != null) {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserName(entity.getUsername());
            userInfoEntity.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551954435661&di=01620611af17a4feafb940171167fda0&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181204%2Fbfef8528ad444fe6a6cbf22da6a690ac.jpeg");
            //TODO 存sp
            UserCenter.getInstance().setUserName(userInfoEntity.getUserName());
            UserCenter.getInstance().setDescription("这个人很懒，什么都没写！");
            UserCenter.getInstance().setUserAvatar(userInfoEntity.getImage());
            binding.tvTip.setText(UserCenter.getInstance().getDescription());
            viewModel.setUserInfoEntity(userInfoEntity);
        }
    }

    /**
     * 退出登录
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(BaseRefreshDataEvent event){
        if (event.getType() == AppConst.RefreshDataParams.LOGOUT){
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserName("");
            userInfoEntity.setImage(null);
            viewModel.setUserInfoEntity(userInfoEntity);

            binding.tvTip.setText("登录后数据不丢失");
        }
    }
}
