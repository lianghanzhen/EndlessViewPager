package com.lianghanzhen.endless.viewpager;

import android.support.v4.view.ViewPager;
import com.viewpagerindicator.CirclePageIndicator;

public class SampleCirclesSnap extends BaseSampleActivity {

    @Override
    protected void configureViews() {
        setContentView(R.layout.simple_circles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);
        indicator.setSnap(true);
    }

}