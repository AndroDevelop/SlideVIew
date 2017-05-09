package com.colin.slideview;

import android.view.View;

/**
 * Created by reg24 on 2017-05-09.
 */

public class ScrollAnimation implements SlideViewAnimation{
    private final static float BIG_SCALE = 1.0f;
    private final static float SMALL_SCALE = 0.7f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    @Override
    public void updateViewSetting(View view) {
        view.setScaleX(SMALL_SCALE);
        view.setScaleY(SMALL_SCALE);
    }

    @Override
    public void startAnimation(float positionOffset, View... views) {
            int centerPosition = views.length/2;
            float diffScale = DIFF_SCALE / views.length;
            float toBigScale = BIG_SCALE - diffScale * positionOffset;
            float toSmallScale = SMALL_SCALE + diffScale * positionOffset;

            for(int i=0; i<centerPosition; i++) {
                views[i].setScaleX(toBigScale);
                views[i].setScaleY(toBigScale);
            }

            for(int i=centerPosition; i<views.length; i++){
                views[i].setScaleX(toSmallScale);
                views[i].setScaleY(toSmallScale);
            }
    }
}
