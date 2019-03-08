/*
 * SuperNovaFramework
 * ApiException
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.exception;

/**
 * api接口错误/异常统一处理类
 * 异常=[程序异常, 网络异常, 解析异常..]
 * 错误=[接口逻辑错误 eg:{code:1002, msg:缺少参数}]
 *
 * @author leo
 * @date 2018/01/31
 */
public class ApiException extends Exception {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.msg = throwable == null ? null : throwable.getMessage();
    }

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
