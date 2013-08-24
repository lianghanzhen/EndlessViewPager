package com.lianghanzhen.endless.viewpager;

import android.support.v4.view.ViewPager;
import com.viewpagerindicator.CirclePageIndicator;

public class SampleCirclesInitialPage extends BaseSampleActivity {

    @Override
    protected void configureViews() {
        setContentView(R.layout.simple_circles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.setCurrentItem(mAdapter.getCount() - 1);

        //You can also do: indicator.setViewPager(pager, initialPage);
    }

}