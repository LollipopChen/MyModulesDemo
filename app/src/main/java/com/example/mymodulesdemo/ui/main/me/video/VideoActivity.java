package com.example.mymodulesdemo.ui.main.me.video;

import android.os.Bundle;

import com.example.libbase.base.BaseActivity;
import com.example.mymodulesdemo.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivityVideoBinding;
import com.example.mymodulesdemo.ui.adapter.VerticalViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的视频
 * @author ChenQiuE
 * Date：2019/3/29 14:32
 * Email：qiue.chen@supernovachina.com
 */
public class VideoActivity extends BaseActivity<ActivityVideoBinding,VideoViewModel> {

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_video;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("上下翻页");

        List<String> picList = new ArrayList<>();
        picList.add("http://img4.imgtn.bdimg.com/it/u=2731720340,4030338696&fm=26&gp=0.jpg");
        picList.add("http://img4.imgtn.bdimg.com/it/u=2166880302,3421052672&fm=26&gp=0.jpg");
        picList.add("http://img4.imgtn.bdimg.com/it/u=3132952471,2940938017&fm=26&gp=0.jpg");
        picList.add("http://img3.imgtn.bdimg.com/it/u=496641143,3869583512&fm=200&gp=0.jpg");
        picList.add("http://img0.imgtn.bdimg.com/it/u=1486826859,4031998705&fm=200&gp=0.jpg");
        picList.add("http://img5.imgtn.bdimg.com/it/u=1733449609,2131963048&fm=200&gp=0.jpg");
        picList.add("http://img1.imgtn.bdimg.com/it/u=3085279319,3297349710&fm=26&gp=0.jpg");
        picList.add("http://img3.imgtn.bdimg.com/it/u=2403018245,3980541398&fm=200&gp=0.jpg");
        picList.add("http://img5.imgtn.bdimg.com/it/u=4036171861,4047236018&fm=26&gp=0.jpg");
        picList.add("http://img4.imgtn.bdimg.com/it/u=17992916,2595590582&fm=26&gp=0.jpg");
        picList.add("http://img3.imgtn.bdimg.com/it/u=1027934569,1848963062&fm=26&gp=0.jpg");
        picList.add("http://img2.imgtn.bdimg.com/it/u=4060547075,2115428035&fm=11&gp=0.jpg");

        VerticalViewPagerAdapter viewPagerAdapter = new VerticalViewPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setVertical(true);
        binding.viewPager.setOffscreenPageLimit(picList.size());
        viewPagerAdapter.setUrlList(picList);
        binding.viewPager.setAdapter(viewPagerAdapter);
    }
}
