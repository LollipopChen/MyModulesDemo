package com.example.mymodulesdemo.ui.main.me.tangram;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.example.libbase.base.BaseActivity;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.login.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.console.TangramViewConst;
import com.example.mymodulesdemo.databinding.ActivityTangramBinding;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleClickSupport;
import com.example.mymodulesdemo.ui.main.me.tangram.view.BannerView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.ItemImageView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.ListItemView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.StickyBarView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.menu.MenuView;
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.orhanobut.logger.Logger;
import com.tmall.wireless.tangram3.TangramBuilder;
import com.tmall.wireless.tangram3.TangramEngine;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.support.SimpleClickSupport;
import com.tmall.wireless.tangram3.util.IInnerImageSetter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 阿里七巧板
 * @author ChenQiuE
 * @date 2019/5/21
 */
public class TangramActivity extends BaseActivity<ActivityTangramBinding,TangramViewModel> {

    private TangramBuilder.InnerBuilder builder;
    private TangramEngine engine;

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_tangram;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("阿里七巧板");
        initTangram();
    }

    private void initTangram() {
        //Step 1: init tangram
        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                Glide.with(TangramActivity.this.getApplicationContext()).load(url).into(view);
            }
        },ImageView.class);

        //Step 2: init angramBuilder
        builder = TangramBuilder.newInnerBuilder(this);

        //Step 3: register business cells and cards
        builder.registerCell(TangramViewConst.BANNER_VIEW, BannerView.class);
        builder.registerCell(TangramViewConst.MENU_VIEW, MenuView.class);
        builder.registerCell(TangramViewConst.ITEM_IMAGE_VIEW, ItemImageView.class);
        builder.registerCell(TangramViewConst.STICKY_VIEW, StickyBarView.class);
        builder.registerCell(TangramViewConst.LIST_VIEW, ListItemView.class);

        //Step 4: new engine
        engine = builder.build();

//        Utils.setUedScreenWidth(720);

        //Step 6: enable auto load more if your page's data is lazy loaded
        engine.enableAutoLoadMore(true);


        //Step 7: bind recyclerView to engine 绑定RecyclerView
        engine.bindView(binding.recyclerView);

        //Step 8: listener recyclerView onScroll event to trigger auto load more  关联滑动监听
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                engine.onScrolled();
            }
        });

        //Step 9: set an offset to fix card 设置悬浮类型布局的偏移
//        engine.getLayoutManager().setFixOffset(0, 0, 0, 0);

        //Step 10: get tangram data and pass it to engine
        String json = new String(getAssertsFile(this, "data3.0"));
        Logger.e("Json:" + SNGsonHelper.toJson(json));
        JSONArray data = null;
        try {
            data = JSON.parseArray(json);
            engine.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Demo for component to listen container's event  recyclerView滑动监听
        engine.register(SampleScrollSupport.class, new SampleScrollSupport(binding.recyclerView));

        //点击
        engine.addSimpleClickSupport(new SimpleClickSupport() {
            @Override
            public void setOptimizedMode(boolean optimizedMode) {
                super.setOptimizedMode(true);
            }

            @Override
            public void defaultClick(View targetView, BaseCell cell, int eventType) {
//                super.defaultClick(targetView, cell, eventType);
                if (cell.stringType.equals(TangramViewConst.STICKY_VIEW)){
                    ToastAlert.show("点击的是：" + targetView.getTag());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (engine != null){
            engine.destroy();
        }
    }

    public static byte[] getAssertsFile(Context context, String fileName) {
        InputStream inputStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(fileName);
            if (inputStream == null) {
                return null;
            }

            BufferedInputStream bis = null;
            int length;
            try {
                bis = new BufferedInputStream(inputStream);
                length = bis.available();
                byte[] data = new byte[length];
                bis.read(data);
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
