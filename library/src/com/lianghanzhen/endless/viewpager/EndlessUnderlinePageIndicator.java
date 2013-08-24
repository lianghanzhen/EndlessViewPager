package com.lianghanzhen.endless.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.viewpagerindicator.UnderlinePageIndicator;


/**
 * Underline indicator. see my forks: https://github.com/lianghanzhen/Android-ViewPagerIndicator
 */
public class EndlessUnderlinePageIndicator extends UnderlinePageIndicator {

    public EndlessUnderlinePageIndicator(Context context) {
        super(context);
    }

    public EndlessUnderlinePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EndlessUnderlinePageIndicator(Context context, AttributeSet attrs, int defStyle) {
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
