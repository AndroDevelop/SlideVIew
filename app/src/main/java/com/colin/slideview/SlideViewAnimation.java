package com.colin.slideview;

import android.view.View;



/**
 * Created by reg24 on 2017-05-08.
 */

public interface SlideViewAnimation {
    void updateViewSetting(View item);
    void startAnimation(float positionOffset, View... views);
}
