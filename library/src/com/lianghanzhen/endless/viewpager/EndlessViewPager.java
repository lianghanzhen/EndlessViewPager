package com.lianghanzhen.endless.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


/**
 * ViewPager that will be scrolled infinitely.
 *
 * it is useful for some cases, like ads banner.
 *
 * when you use FragmentPagerAdapter or FragmentStatePagerAdapter, it only supports >= 3 pages.
 * because when you scroll to next page, the page that being destroyed and created are the same.
 */
public class EndlessViewPager extends ViewPager {

    private EndlessPagerAdapter mEndlessPagerAdapter;

    public EndlessViewPager(Context context) {
        super(context);
    }

    public EndlessViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mEndlessPagerAdapter = new EndlessPagerAdapter(adapter);
        super.setAdapter(mEndlessPagerAdapter);
        // make sure we have enough space to scroll infinitely.
        setCurrentItem(mEndlessPagerAdapter.getPagerAdapter().getCount() << 12);
    }

    /**
     * use the method to get the real PagerAdapter
     * @return the real PagerAdapter
     */
    public PagerAdapter getRealAdapter() {
        return mEndlessPagerAdapter != null ? mEndlessPagerAdapter.getPagerAdapter() : null;
    }

    int getPageCount() {
        final PagerAdapter pagerAdapter = getRealAdapter();
        return pagerAdapter != null ? pagerAdapter.getCount() : 0;
    }

}
