package com.lianghanzhen.endless.viewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.PageIndicator;

public abstract class BaseSampleActivity extends FragmentActivity {

    ViewPager mPager;
    PageIndicator mIndicator;
    TestFragmentAdapter mAdapter;
    BannerHandler mBannerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureViews();

        mBannerHandler = new BannerHandler(mPager);
        mIndicator.setOnPageChangeListener(mBannerHandler);
    }

    protected abstract void configureViews();

    @Override
    protected void onResume() {
        super.onResume();
        mBannerHandler.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBannerHandler.stop();
    }

}
