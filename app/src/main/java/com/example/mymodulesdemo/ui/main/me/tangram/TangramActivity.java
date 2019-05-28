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
import com.example.mymodulesdemo.ui.main.me.tangram.support.SampleScrollSupport;
import com.example.mymodulesdemo.ui.main.me.tangram.view.BannerView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.FiltratePopupWindow;
import com.example.mymodulesdemo.ui.main.me.tangram.view.ItemImageView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.ListItemView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.ListPopupWindow;
import com.example.mymodulesdemo.ui.main.me.tangram.view.StickyBarView;
import com.example.mymodulesdemo.ui.main.me.tangram.view.menu.MenuView;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tmall.wireless.tangram3.TangramBuilder;
import com.tmall.wireless.tangram3.TangramEngine;
import com.tmall.wireless.tangram3.structure.BaseCell;
import com.tmall.wireless.tangram3.support.SimpleClickSupport;
import com.tmall.wireless.tangram3.util.IInnerImageSetter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 阿里七巧板--首页
 *
 * @author ChenQiuE
 * @date 2019/5/21
 */
public class TangramActivity extends BaseActivity<ActivityTangramBinding, TangramViewModel> implements ListPopupWindow.ItemClickListener, FiltratePopupWindow.FiltrateItemClickListener {

    private TangramBuilder.InnerBuilder builder;
    private TangramEngine engine;
    private ListPopupWindow sortList;
    private FiltratePopupWindow filtrateList;

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

        sortList = new ListPopupWindow(this);
        filtrateList = new FiltratePopupWindow(this);

        initTangram();
    }

    private void initTangram() {
        //Step 1: init tangram
        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                Glide.with(TangramActivity.this.getApplicationContext()).load(url).into(view);
            }
        }, ImageView.class);

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

        // Step 9: set an offset to fix card 设置悬浮类型布局的偏移
//        engine.getLayoutManager().setFixOffset(0, 0, 0, 0);

        // Step 10: get tangram data and pass it to engine 设置数据
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
            public void defaultClick(View targetView, BaseCell cell, int eventType) {
                switch (cell.stringType) {
                    case TangramViewConst.BANNER_VIEW:
                        ToastAlert.show("点击了广告" + cell.extras.get("msg"));
                        break;
                    case TangramViewConst.ITEM_IMAGE_VIEW:
                        ToastAlert.show("点击了卡片:" + cell.extras.get("title"));
                        break;
                    case TangramViewConst.STICKY_VIEW:
                        //吸顶
                        engine.topPosition(cell);
                        onStickyBar(targetView, cell, eventType);
                        break;
                    case TangramViewConst.LIST_VIEW:
                        ToastAlert.show("点击列表：" + cell.extras.get("title"));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * stickyBar on click event 悬浮条点击事件
     *
     * @param targetView view
     * @param cell       cell
     * @param eventType  clickType
     */
    private void onStickyBar(View targetView, BaseCell cell, int eventType) {
        switch (eventType) {
            case TangramViewConst.StickyBarViewConst.SORT:
                onSortClick(targetView, cell);
                break;
            case TangramViewConst.StickyBarViewConst.SELL:
                onSellClick();
                break;
            case TangramViewConst.StickyBarViewConst.DISTANCE:
                onDistanceClick();
                break;
            case TangramViewConst.StickyBarViewConst.FILTRATE:
                onFiltrateClick(targetView, cell);
                break;
            default:
                break;
        }
    }

    /**
     * 综合排序
     *
     * @param targetView targetView
     * @param cell       cell
     */
    private void onSortClick(View targetView, BaseCell cell) {
        ((StickyBarView) targetView).setSortDrawable(this, R.mipmap.ic_up_green);
        sortList.setItemClickListener(targetView, this);
        List<String> list = SNGsonHelper.toList("" + cell.extras.get("sortItems"), new TypeToken<List<String>>() {
        });
        sortList.setData(list);
        sortList.showPopupWindow(targetView);
    }

    /**
     * 距离
     */
    private void onDistanceClick() {
        ToastAlert.show("距离");
        if (sortList != null) {
            sortList.setSelectCount(-1);
        }
    }

    /**
     * 销量
     */
    private void onSellClick() {
        ToastAlert.show("销量");
        if (sortList != null) {
            sortList.setSelectCount(-1);
        }
    }

    /**
     * 筛选
     *
     * @param targetView targetView
     * @param cell       cell
     */
    private void onFiltrateClick(View targetView, BaseCell cell) {
        filtrateList.setItemClickListener(this);
        List<String> list = SNGsonHelper.toList("" + cell.extras.get("discountItems"), new TypeToken<List<String>>() {
        });
        filtrateList.setData(list);
        filtrateList.showPopupWindow(targetView);
    }

    @Override
    public void onItemClick(View targetView, int position) {
        if (position == 0) {
            ((StickyBarView) targetView).setSortText("综合排序");
        } else {
            ((StickyBarView) targetView).setSortText("最低消费");
        }
    }

    @Override
    public void onItemDismiss(View targetView) {
        ((StickyBarView) targetView).setSortDrawable(this, R.mipmap.ic_down_gray);
    }

    @Override
    public void onFiltrateItemClick(String value) {
        ToastAlert.show(value);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (engine != null) {
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
