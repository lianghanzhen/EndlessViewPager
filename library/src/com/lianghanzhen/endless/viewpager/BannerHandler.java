package com.lianghanzhen.endless.viewpager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;


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
    private long mSwitchDelay;

    private boolean mRunning;

    public BannerHandler(ViewPager viewPager) {
        this(viewPager, DEFAULT_SWITCH_DELAY);
    }

    public BannerHandler(ViewPager viewPager, long switchDelay) {
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
        mSwitchDelay = switchDelay > 0 ? switchDelay : DEFAULT_SWITCH_DELAY;
    }

    /**
     * start handling auto scroll. Usually, call it in Activity's onResume() method.
     * @return BannerHandler
     */
    public BannerHandler start() {
        sendMessage(mSwitchDelay);
        mRunning = true;
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
    public BannerHandler setSwitchDelay(long switchDelay) {
        if (mRunning) {
            long diff = switchDelay > 0 ? mSwitchDelay - switchDelay : 0;
            mSwitchDelay = switchDelay > 0 ? switchDelay : DEFAULT_SWITCH_DELAY;
            sendMessage(diff > 0 ? diff : 0);
        } else {
            mSwitchDelay = switchDelay > 0 ? switchDelay : DEFAULT_SWITCH_DELAY;
        }
        return this;
    }

    /**
     * when page has been changed, start next scroll.
     */
    @Override
    public void onPageSelected(int position) {
        sendMessage(mSwitchDelay);
    }

    /**
     * when use is dragging, disable the auto scroll.
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            removeMessage();
        } else if (state == ViewPager.SCROLL_STATE_IDLE) {
            sendMessage(mSwitchDelay);
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
