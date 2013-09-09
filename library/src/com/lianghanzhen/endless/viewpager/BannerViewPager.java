package com.lianghanzhen.endless.viewpager;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Custom ViewPager to solve nested ViewPager scrolling issue
 */
public class BannerViewPager extends EndlessViewPager {

    PointF downP = new PointF();
    PointF curP = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        curP.x = event.getX();
        curP.y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            downP.x = event.getX();
            downP.y = event.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(downP.x==curP.x && downP.y==curP.y){
                onSingleTouch();
                return true;
            }
        }

        return super.onTouchEvent(event);
    }

    public void onSingleTouch() {
        if (onSingleTouchListener!= null) {
            onSingleTouchListener.onSingleTouch();
        }
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }

    public interface OnSingleTouchListener {
        public void onSingleTouch();
    }

}
