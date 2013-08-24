package com.lianghanzhen.endless.viewpager;

import android.support.v4.view.ViewPager;
import com.viewpagerindicator.LinePageIndicator;

public class SampleLinesStyledMethods extends BaseSampleActivity {

    @Override
    protected void configureViews() {
        setContentView(R.layout.simple_lines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        LinePageIndicator indicator = (LinePageIndicator)findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setSelectedColor(0x88FF0000);
        indicator.setUnselectedColor(0xFF888888);
        indicator.setStrokeWidth(4 * density);
        indicator.setLineWidth(30 * density);
    }

}