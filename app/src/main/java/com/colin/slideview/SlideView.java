package com.colin.slideview;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by reg24 on 2017-04-29.
 */

public class SlideView {
    private ViewPager viewPager;
    private Activity activity;
    private DisplayMetrics displaymetrics;
    private FragmentManager fm;
    private SlideViewPagerAdapter adapter;

    public SlideView(Activity activity, FragmentManager fm, ViewPager vp){
        this.viewPager = vp;
        this.activity = activity;
        this.fm = fm;
        try {
            init();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    private void init() {
        displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int pageMargin = ((displaymetrics.widthPixels / 4) * 2);
        adapter = new SlideViewPagerAdapter(fm,viewPager);

        viewPager.setPageMargin(-pageMargin);
        viewPager.addOnPageChangeListener(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    public SlideView withAnimation(SlideViewAnimation animation){
        adapter.setAnimation(animation);
        return this;
    }
    public SlideView withGetItemListener(SlideViewPagerAdapter.GetItemListener getItemListener){
        adapter.setGetItemListener(getItemListener);
        return this;
    }
}
