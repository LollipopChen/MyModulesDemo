package com.example.libbase.net.http.exception;

import android.content.Context;

import com.example.libbase.R;
import com.example.libbase.base.SnBaseApplication;
import com.example.libbase.console.SNHttpConstants;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * 错误/异常处理工具 ExceptionEngine
 *
 * @author leo
 * @date 2018/01/31
 */
public class ExceptionEngine {
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        Context context = SnBaseApplication.getAppContext();

        if (e instanceof HttpException) {
            //http错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            ex.setMsg(context.getString(R.string.network_error));

            return ex;
        } else if (e instanceof ServerException) {
            //服务器返回的错误
            ServerException serverException = (ServerException) e;
            ex = new ApiException(serverException, serverException.getCode());
            ex.setMsg(serverException.getMsg());

            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) {

            //解析数据错误2001
            ex = new ApiException(e, SNHttpConstants.HTTP_ANALYTIC_SERVER_DATA_ERROR);
            ex.setMsg(context.getString(R.string.http_analytic_server_data_error));

            return ex;
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {

            //连接错误或域名错误2002
            ex = new ApiException(e, SNHttpConstants.HTTP_CONNECT_ERROR);
            ex.setMsg(context.getString(R.string.http_connect_error));

            return ex;
        } else if (e instanceof SocketTimeoutException) {
            //网络超时2003
            ex = new ApiException(e, SNHttpConstants.HTTP_TIME_OUT_ERROR);
            ex.setMsg(context.getString(R.string.http_socket_timeout));

            return ex;
        } else {
            //未知错误2000
            ex = new ApiException(e, SNHttpConstants.HTTP_UN_KNOWN_ERROR);
            ex.setMsg(context.getString(R.string.http_unknown_error));

            return ex;
        }
    }
}
