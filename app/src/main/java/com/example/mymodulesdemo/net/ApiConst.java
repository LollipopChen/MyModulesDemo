package com.example.mymodulesdemo.net;

/**
 * @author ChenQiuE
 * Date：2019/3/8 11:56
 * Email：1077503420@qq.com
 */
public interface ApiConst {
    /**
     * 获取Banner
     */
    String GET_BANNER_LIST = "banner/json";

    /**
     * 获取文章列表
     */
    String GET_ARTICLE_LIST = "article/list/{page}/json";

    /**
     * 获取公众号列表
     */
    String CHAPTERS = "wxarticle/chapters/json";

    /**
     * 查看某个公众号历史数据
     */
    String CHAPTERS_LIST = "wxarticle/list/{id}/{page}/json";

    /**
     * 热门搜索词
     */
    String HOT_FLAG = "/hotkey/json";

    /**
     * 搜索
     */
    String SEARCH = "article/query/{page}/json";

    /**
     * 导航数据
     */
    String NAVIGATION = "navi/json";

    /**
     * 体系数据
     */
    String SYSTEM = "tree/json";

    interface Params{
        String PAGE = "page";
        String ID = "id";
    }
}
