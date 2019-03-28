/*
 * SuperNovaFramework
 * SNHttpConstants
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.console;

/**
 * SNHttpConstants
 *
 * @author leo
 * @date 2017/12/22
 */
public interface SNHttpConstants {

    /**
     * 2000 未知错误
     * 2001 解析(服务器)数据错误
     * 2002 网络连接错误
     * 2003 网络连接超时
     */
    int HTTP_UN_KNOWN_ERROR = 2000;
    int HTTP_ANALYTIC_SERVER_DATA_ERROR = 2001;
    int HTTP_CONNECT_ERROR = 2002;
    int HTTP_TIME_OUT_ERROR = 2003;
    /**
     * 0	成功
     */
    int RESPONSE_CODE_SUCCESS =  0;

}
