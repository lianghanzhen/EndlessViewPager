package com.lianghanzhen.endless.viewpager;

import android.support.v4.view.ViewPager;
import com.viewpagerindicator.UnderlinePageIndicator;

public class SampleUnderlinesStyledTheme extends BaseSampleActivity {

    @Override
    protected void configureViews() {
        //The look of this sample is set via a style in the manifest
        setContentView(R.layout.simple_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

}