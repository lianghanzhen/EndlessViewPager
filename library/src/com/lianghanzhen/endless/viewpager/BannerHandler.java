package com.lianghanzhen.endless.viewpager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * use to handle ViewPager auto scroll.
 */
public class BannerHandler extends ViewPager.SimpleOnPageChangeListener {

    private static final int MESSAGE_WHAT_NEXT_PAGE = 0;
    private static final long DEFAULT_SWITCH_DELAY = 3000L;

    private final Handler sHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            if (message.what == MESSAGE_WHAT_NEXT_PAGE) {
                final int currentItem = mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(currentItem < mViewPager.getAdapter().getCount() - 1 ? currentItem + 1 : 0);
            }
        }
    };

    private final ViewPager mViewPager;
    private final List<Long> mSwitchDelay = new ArrayList<Long>();

    private boolean mRunning;

    public BannerHandler(ViewPager viewPager, long ... switchDelay) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Do not use BannerHandler out of main looper.");
        }
        if (viewPager == null) {
            throw new NullPointerException("Did you forget initialize ViewPager?");
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalArgumentException("Did you forget set an adapter for ViewPager?");
        }
        mViewPager = viewPager;
        final int count = getPageCount();
        final int length = switchDelay.length;
        for (int i = 0; i < count; i++) {
            if (i < length) {
                mSwitchDelay.add(switchDelay[i] > 0 ? switchDelay[i] : DEFAULT_SWITCH_DELAY);
            } else {
                mSwitchDelay.add(DEFAULT_SWITCH_DELAY);
            }
        }
    }

    private int getPageCount() {
        return (mViewPager instanceof EndlessViewPager) ? ((EndlessViewPager) mViewPager).getPageCount() : mViewPager.getAdapter().getCount();
    }

    private int getCurrentItem() {
        return mViewPager.getCurrentItem() % getPageCount();
    }

    /**
     * start handling auto scroll. Usually, call it in Activity's onResume() method.
     * @return BannerHandler
     */
    public BannerHandler start() {
        if (!mRunning) {
            sendMessage(mSwitchDelay.get(getCurrentItem()));
            mRunning = true;
        }
        return this;
    }

    /**
     * stop handling auto scroll. Usually, call it in Activity's onPause() method.
     * @return BannerHandler
     */
    public BannerHandler stop() {
        removeMessage();
        mRunning = false;
        return this;
    }

    /**
     * change the delay of  auto scroll.
     * if auto scroll is running, then it will send an empty message to indicate next scroll.
     * @param switchDelay delay of auto scroll
     * @return BannerHandler
     */
    public BannerHandler setSwitchDelay(int position, long switchDelay) {
        if (mRunning) {
            long diff = switchDelay > 0 ? mSwitchDelay.get(position) - switchDelay : 0;
            mSwitchDelay.set(position, switchDelay > 0 ? switchDelay : DEFAULT_SWITCH_DELAY);
            if (getCurrentItem() == position) {
                sendMessage(diff > 0 ? diff : 0);
            }
        } else {
            mSwitchDelay.set(position, switchDelay > 0 ? switchDelay : DEFAULT_SWITCH_DELAY);
        }
        return this;
    }

    /**
     * when page has been changed, start next scroll.
     */
    @Override
    public void onPageSelected(int position) {
        sendMessage(mSwitchDelay.get(getCurrentItem()));
    }

    /**
     * when use is dragging, disable the auto scroll.
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            removeMessage();
        } else if (state == ViewPager.SCROLL_STATE_IDLE) {
            sendMessage(mSwitchDelay.get(getCurrentItem()));
        }
    }

    /**
     * make sure that there is only one message
     */
    private void sendMessage(long delay) {
        removeMessage();
        sHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_NEXT_PAGE, delay);
    }

    private void removeMessage() {
        sHandler.removeMessages(MESSAGE_WHAT_NEXT_PAGE);
    }

}