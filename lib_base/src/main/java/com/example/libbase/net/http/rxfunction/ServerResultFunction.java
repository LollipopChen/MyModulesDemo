/*
 * SuperNovaFramework
 * ServerResultFunction
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.rxfunction;

import com.orhanobut.logger.Logger;
import com.supernova.snlibrary.console.SNResponseEntity;
import com.supernova.snlibrary.json.SNGsonHelper;
import com.supernova.snlibrary.net.http.exception.ServerException;

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
