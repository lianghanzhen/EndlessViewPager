package com.lianghanzhen.endless.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.viewpagerindicator.CirclePageIndicator;


/**
 * Circle indicator. see my forks: https://github.com/lianghanzhen/Android-ViewPagerIndicator
 */
public class EndlessCirclePageIndicator extends CirclePageIndicator {

    public EndlessCirclePageIndicator(Context context) {
        super(context);
    }

    public EndlessCirclePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EndlessCirclePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getPageCount() {
        final ViewPager viewPager = getViewPager();
        return viewPager != null && viewPager instanceof EndlessViewPager ? ((EndlessViewPager) viewPager).getPageCount() : super.getPageCount();
    }

    @Override
    protected int getFillPage() {
        return super.getFillPage() % getPageCount();
    }

}
