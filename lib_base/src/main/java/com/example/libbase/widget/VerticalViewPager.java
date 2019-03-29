package com.example.libbase.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 竖直翻页ViewPager
 * @author ChenQiuE
 * Date：2019/3/29 13:21
 * Email：qiue.chen@supernovachina.com
 */
public class VerticalViewPager extends ViewPager {

    private boolean isVertical = false;

    public VerticalViewPager(@NonNull Context context) {
        super(context);
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(){
        setPageTransformer(true,new HorizontalVerticalPageTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
        init();
    }

    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isVertical) {
            //return touch coordinates to original reference frame for any child views
            boolean isOnIntercept = super.onInterceptTouchEvent(swapXY(ev));
            swapXY(ev);
            return isOnIntercept;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isVertical) {
            return super.onTouchEvent(swapXY(ev));
        } else {
            return super.onTouchEvent(ev);
        }
    }

    private class HorizontalVerticalPageTransformer implements PageTransformer {

        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(@NonNull View view, float position) {
            if (isVertical){
               if (position > -1 && position <= 1){
                    view.setAlpha(1);

                    float xPosition = position * view.getWidth();
                    view.setTranslationX(-xPosition);

                    float yPosition = position * view.getHeight();
                    view.setTranslationY(yPosition);
                }else {
                    view.setAlpha(0);
                }
            }else {
                int pageWidth = view.getWidth();

                if (position < -1) {
                    // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 0) {
                    // [-1,0]
                    // Use the default slide transition when moving to the left page
                    view.setAlpha(1);
                    view.setTranslationX(0);
                    view.setScaleX(1);
                    view.setScaleY(1);

                } else if (position <= 1) {
                    // (0,1]
                    // Fade the page out.
                    view.setAlpha(1 - position);

                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);
                    view.setTranslationY(0);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                } else {
                    // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }

            }
        }
    }
}
