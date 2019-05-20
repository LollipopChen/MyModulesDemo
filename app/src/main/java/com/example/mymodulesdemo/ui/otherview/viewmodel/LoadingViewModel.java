package com.example.mymodulesdemo.ui.otherview.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.libbase.base.BaseViewModel;
import com.example.libbase.base.SnBaseApplication;
import com.example.libbase.binding.command.BindingAction;
import com.example.libbase.binding.command.BindingCommand;
import com.example.libbase.utils.SNStringUtils;
import com.example.mymodulesdemo.R;

/**
 * 加载控件
 * @author ChenQiuE
 * Date：2019/3/11 14:05
 * Email：1077503420@qq.com
 */
public class LoadingViewModel extends BaseViewModel {
    /**
     * 正在加载
     */
    public static final int LOADING = 0;
    /**
     * 停止加载
     */
    public static final int STOP_LOADING = 1;
    /**
     * 无数据
     */
    public static final int NO_DATA = 2;
    /**
     * 无网络
     */
    public static final int NO_NETWORK = 3;
    /**
     * 用戶未登錄
     */
    public static final int USER_NOT_LOGIN = 4;

    /** 说明文字 */
    public ObservableField<String> messageText = new ObservableField<>("");
    /** 全布局状态 */
    public ObservableInt layoutVisibleObservable = new ObservableInt(View.GONE);
    /** 图标状态 */
    public ObservableInt noDataIconVisibleObservable = new ObservableInt(View.GONE);
    /**进度条状态*/
    public ObservableInt progressVisibleObservable = new ObservableInt(View.GONE);
    /**按钮状态*/
    public ObservableInt buttonVisibleObservable = new ObservableInt(View.GONE);
    /**按钮文字 */
    public ObservableField<String> buttonText = new ObservableField<>("");
    /**是否需要登录*/
    public ObservableBoolean isLoginObservable = new ObservableBoolean();

    private int status;

    public LoadingViewModel(@NonNull Application application) {
        super(application);
    }

    public void setStatus(int status){
        this.status = status;
        layoutVisibleObservable.set(View.VISIBLE);
        isLoginObservable.set(false);
        switch (status){
            case LOADING:
                noDataIconVisibleObservable.set(View.GONE);
                progressVisibleObservable.set(View.VISIBLE);
                messageText.set(SnBaseApplication.getAppContext().getString(R.string.loading));
                buttonVisibleObservable.set(View.GONE);
                break;

            case STOP_LOADING:
                layoutVisibleObservable.set(View.GONE);
                break;

            case NO_DATA:
                noDataIconVisibleObservable.set(View.VISIBLE);
                progressVisibleObservable.set(View.GONE);
                messageText.set(SnBaseApplication.getAppContext().getString(R.string.no_data));
                buttonText.set(SnBaseApplication.getAppContext().getString(R.string.loading_again));
                buttonVisibleObservable.set(View.VISIBLE);
                break;

            case NO_NETWORK:
                noDataIconVisibleObservable.set(View.VISIBLE);
                progressVisibleObservable.set(View.GONE);
                messageText.set(SnBaseApplication.getAppContext().getString(R.string.http_connect_error));
                buttonText.set(SnBaseApplication.getAppContext().getString(R.string.loading_again));
                buttonVisibleObservable.set(View.VISIBLE);
                break;

            case USER_NOT_LOGIN:
                isLoginObservable.set(true);
                noDataIconVisibleObservable.set(View.VISIBLE);
                progressVisibleObservable.set(View.GONE);
                messageText.set(SnBaseApplication.getAppContext().getString(R.string.user_not_login));
                buttonText.set(SnBaseApplication.getAppContext().getString(R.string.goto_login));
                buttonVisibleObservable.set(View.VISIBLE);
                break;
            default:
                layoutVisibleObservable.set(View.GONE);
                break;
        }
    }

    /**
     * 错误处理
     *
     * @param message 错误信息
     */
    public void setErrorMessage(String message) {
        layoutVisibleObservable.set(View.VISIBLE);
        if (!SNStringUtils.isEmpty(message)) {
            messageText.set(message);
        }
        noDataIconVisibleObservable.set(View.VISIBLE);
        progressVisibleObservable.set(View.GONE);
        buttonText.set(SnBaseApplication.getAppContext().getString(R.string.loading_again));
        buttonVisibleObservable.set(View.VISIBLE);
    }

    public BindingCommand buttonOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (status == USER_NOT_LOGIN){
                onLogin();
            }else {
                setStatus(LOADING);
                onRefreshData();
            }
        }
    });

    protected void onRefreshData(){

    }

    protected void onLogin(){

    }

}
