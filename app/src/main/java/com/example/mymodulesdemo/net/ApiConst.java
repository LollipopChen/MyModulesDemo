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
    String GET_BANNER_LIST = "/banner/json";

    /**
     * 获取文章列表
     */
    String GET_ARTICLE_LIST = "/article/list/{page}/json";

    interface Params{
        String PAGE = "page";
    }
}
