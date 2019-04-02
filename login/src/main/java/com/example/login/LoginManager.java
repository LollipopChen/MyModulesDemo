package com.example.login;

import com.sankuai.waimai.router.Router;

/**
 * @author ChenQiuE
 * Date：2019/4/1 15:14
 * Email：qiue.chen@supernovachina.com
 */
public class LoginManager {
    public static ILoginService getLoginService(){
        return Router.getService(ILoginService.class,LoginConstant.ServiceConstant.SERVICE_LOGIN);
    }

}
