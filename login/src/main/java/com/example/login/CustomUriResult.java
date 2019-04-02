package com.example.login;

/**
 * 自定义的UriResultCode，为了避免冲突，自定义的建议用负数值
 * @author ChenQiuE
 * Date：2019/4/1 14:54
 * Email：qiue.chen@supernovachina.com
 */
public interface CustomUriResult {
    int CODE_LOGIN_CANCEL = -100;
    int CODE_LOGIN_FAILURE = -101;

    int CODE_LOCATION_FAILURE = -200;
}
