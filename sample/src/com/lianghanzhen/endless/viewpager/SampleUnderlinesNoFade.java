package com.lianghanzhen.endless.viewpager;

import android.support.v4.view.ViewPager;
import com.viewpagerindicator.UnderlinePageIndicator;

public class SampleUnderlinesNoFade extends BaseSampleActivity {

    @Override
    protected void configureViews() {
        setContentView(R.layout.simple_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        UnderlinePageIndicator indicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFades(false);
        mIndicator = indicator;
    }

}