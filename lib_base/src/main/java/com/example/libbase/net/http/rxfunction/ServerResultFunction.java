/*
 * SuperNovaFramework
 * ServerResultFunction
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.rxfunction;

import com.example.libbase.console.SNResponseEntity;
import com.example.libbase.json.SNGsonHelper;
import com.example.libbase.net.http.exception.ServerException;
import com.orhanobut.logger.Logger;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 服务器结果处理函数
 *
 * @author leo
 * @date 2018/01/31
 */
public class ServerResultFunction implements Function<SNResponseEntity, Object> {
    @Override
    public Object apply(@NonNull SNResponseEntity response) throws Exception {
        Logger.wtf("ServerResultFunction" + response.toString());
        if (!response.isSuccess()) {
            throw new ServerException(Integer.valueOf(response.getCode()), response.getMessage());
        }
        return SNGsonHelper.toJson(response);
    }
}
