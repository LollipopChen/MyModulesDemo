package com.example.libbase.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.libbase.R;

import io.reactivex.annotations.Nullable;

/**
 * WebView进度条
 * @author ChenQiuE
 * Date：2019/3/12 10:24
 * Email：1077503420@qq.com
 */
public class WebViewProgressBar extends View {

    private int progress = 1;
    private final static int HEIGHT = 5;
    private Paint paint;
    private final static int COLOR_ARRAY[] = new int[]{};

    public WebViewProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebViewProgressBar(Context context) {
        super(context);
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(HEIGHT);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() * progress / 100, HEIGHT, paint);
    }
}
