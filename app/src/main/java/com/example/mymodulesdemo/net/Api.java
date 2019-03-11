package com.example.mymodulesdemo.net;

import com.example.mymodulesdemo.entity.ArticleListEntity;
import com.example.mymodulesdemo.entity.BannerEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ChenQiuE
 * Date：2019/3/11 10:25
 * Email：1077503420@qq.com
 */
public interface Api {

    /**
     * 获取广告
     * @return 广告数据
     */
    @GET(ApiConst.GET_BANNER_LIST)
    Observable<BannerEntity> getBanner();

    /**
     * 首页列表
     * @param page 页数
     * @return 列表数据
     */
    @GET(ApiConst.GET_ARTICLE_LIST)
    Observable<ArticleListEntity> getHomeList(@Path(ApiConst.Params.PAGE) String page);
}
