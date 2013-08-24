package com.lianghanzhen.endless.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class TestFragmentAdapter extends FragmentPagerAdapter {

    protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", };

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }

}
