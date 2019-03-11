/*
 * SuperNovaFramework
 * BaseResponseEntity
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.console;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * BaseResponseEntity
 *
 * @author leo
 * @date 2017/12/15
 */

public class SNResponseEntity {
    @SerializedName("status_code") private String statusCode;
    //TODO 这个需要修改，删掉默认值
    @SerializedName("ms_code") protected String code = "1000";
    @SerializedName("message") private String message;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return Integer.valueOf(code) == SNHttpConstants.RESPONSE_CODE_SUCCESS;
    }

    @Override
    @NonNull
    public String toString() {
        return "SNResponseEntity{" +
                "statusCode='" + statusCode + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
