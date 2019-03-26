package com.example.mymodulesdemo.net;

import com.example.mymodulesdemo.entity.ArticleListEntity;
import com.example.mymodulesdemo.entity.BannerEntity;
import com.example.mymodulesdemo.entity.HotSearchEntity;
import com.example.mymodulesdemo.entity.ListDataEntity;
import com.example.mymodulesdemo.entity.ListTabLayoutEntity;
import com.example.mymodulesdemo.entity.NavigationEntity;
import com.example.mymodulesdemo.entity.SearchResultEntity;
import com.example.mymodulesdemo.entity.SystemEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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

    /**
     * 列表--tabLayout数据
     * @return 公众号列表数据
     */
    @GET(ApiConst.CHAPTERS)
    Observable<ListTabLayoutEntity> getChapters();

    /**
     * 查看某个公众号历史数据
     * @param id  公众号id
     * @param page 当前页数
     * @return 某个公众号历史数据
     */
    @GET(ApiConst.CHAPTERS_LIST)
    Observable<ListDataEntity> getChaptersList(@Path(ApiConst.Params.ID) String id,@Path(ApiConst.Params.PAGE) String page);

    /**
     * 热门搜索词
     * @return  搜索词数据
     */
    @GET(ApiConst.HOT_FLAG)
    Observable<HotSearchEntity> getHotFlag();

    /**
     * 搜索
     * @param page 页码
     * @param params 参数
     * @return 搜索结果数据
     */
    @POST(ApiConst.SEARCH)
    Observable<SearchResultEntity> getSearchList(@Path(ApiConst.Params.PAGE) String page, @QueryMap Map<String, Object> params);

    /**
     * 获取导航数据
     * @return 导航数据
     */
    @GET(ApiConst.NAVIGATION)
    Observable<SystemEntity> getNavigationData();

    /**
     * 获取体系数据
     * @return 体系数据
     */
    @GET(ApiConst.SYSTEM)
    Observable<NavigationEntity> getSystemData();
}
