package com.chay.test.chaysizemanager.wheelView.lib;

import android.view.MotionEvent;

/**
 * Created by Chay on 2016/11/8.
 */

final class LoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final WheelView loopView;

    LoopViewGestureListener(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
