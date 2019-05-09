package com.example.mymodulesdemo.console;

/**
 * 路由地址
 * @author ChenQiuE
 * Date：2019/4/1 15:31
 * Email：qiue.chen@supernovachina.com
 */
public interface RouterManager {

    /**
     * 页面
     */
    interface UiConstant{
        /**我的*/
        String ME_FRAGMENT = "/me_fragment";
        /**信息*/
        String MESSAGE_WITH_LOGIN = "/message_with_login";
        /**个人信息*/
        String USER_INFO_WITH_LOGIN = "/user_info_with_login";
        /**我的收藏*/
        String COLLECTION_WITH_LOGIN = "/collection_with_login";
    }


    /***
     * Service
     */
    interface ServiceManager{

    }
}
