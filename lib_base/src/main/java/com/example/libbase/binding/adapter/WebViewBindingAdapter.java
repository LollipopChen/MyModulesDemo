package com.example.libbase.binding.adapter;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;

/**
 * webView
 * @author ChenQiuE
 * Date：2019/3/12 10:54
 * Email：1077503420@qq.com
 */
public class WebViewBindingAdapter {
    @BindingAdapter({"link"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
//            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
            webView.loadUrl(html);
        }
    }
}
