package com.example.login;

import android.support.annotation.NonNull;

import com.example.libbase.widget.toast.ToastAlert;
import com.sankuai.waimai.router.core.UriCallback;
import com.sankuai.waimai.router.core.UriInterceptor;
import com.sankuai.waimai.router.core.UriRequest;
import com.sankuai.waimai.router.core.UriResult;

/**
 * 登录 拦截器（用于检验是否需要登录后才能操作的页面）
 * @author ChenQiuE
 * Date：2019/4/1 14:24
 * Email：qiue.chen@supernovachina.com
 */
public class LoginInterceptor implements UriInterceptor {

    @Override
    public void intercept(@NonNull UriRequest request, @NonNull UriCallback callback) {
        final ILoginService service = LoginManager.getLoginService();

        if (service.isLogin()){
            callback.onNext();
        }else {
            ToastAlert.show("请先登录");
            service.registerObserver(new ILoginService.Observer() {
                @Override
                public void onLoginSuccess() {
                    service.unregisterObserver(this);
                    callback.onNext();
                }

                @Override
                public void onLoginCancel() {
                    service.unregisterObserver(this);
                    callback.onComplete(CustomUriResult.CODE_LOGIN_CANCEL);
                }

                @Override
                public void onLoginFailure() {
                    service.unregisterObserver(this);
                    callback.onComplete(CustomUriResult.CODE_LOGIN_FAILURE);
                }

                @Override
                public void onLogout() {
                    service.unregisterObserver(this);
                    callback.onComplete(UriResult.CODE_ERROR);
                }
            });
            LoginManager.getLoginService().startLogin(request.getContext());
        }
    }
}
