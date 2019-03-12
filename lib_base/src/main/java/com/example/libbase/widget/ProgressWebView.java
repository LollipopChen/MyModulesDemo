package com.example.libbase.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.libbase.widget.dialog.SNLoadingDialog;

/**
 * 带进度条的WebView
 *
 * @author ChenQiuE
 * Date：2019/3/12 10:23
 * Email：1077503420@qq.com
 */
public class ProgressWebView extends WebView {

    private SNLoadingDialog loadingDialog;
    private WebViewProgressBar progressBar;
    private Handler handler;
    private WebView _this;

    @SuppressLint({"SetJavaScriptEnabled", "ObsoleteSdkInt"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressBar = new WebViewProgressBar(context);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        progressBar.setVisibility(GONE);
        addView(progressBar);
        handler = new Handler();
        _this = this;
        loadingDialog = new SNLoadingDialog(context);
        loadingDialog.setCancelable(true);

        //关闭webView中缓存
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置不可以访问文件
        getSettings().setAllowFileAccess(true);
        //支持通过JS打开新窗口
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        getSettings().setLoadsImagesAutomatically(true);
        //设置编码格式
        getSettings().setDefaultTextEncodingName("utf-8");
        //设置不用系统浏览器打开,直接显示在当前WebView
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        setWebChromeClient(new MyWebChromeClient());

        /*
         *  Webview在安卓5.0之前默认允许其加载混合网络协议内容
         *  在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        /* 设置webView不显示图片问题 */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBlockNetworkImage(false);

    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setProgress(100);
                handler.postDelayed(runnable, 200);
            } else if (progressBar.getVisibility() == GONE) {
                progressBar.setVisibility(VISIBLE);
            }
            if (newProgress < 5) {
                newProgress = 5;
            }
            progressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    private Runnable runnable = this::dismissDialog;

    /**
     * 关闭加载框
     */
    public void dismissDialog() {
        if (loadingDialog.isShow()) {
            loadingDialog.dismissProgress();
        }
        progressBar.setVisibility(View.GONE);
    }
}
