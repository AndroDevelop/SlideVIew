package com.colin.slideview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class SlideViewPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private SlideViewAnimation animation;
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> initFrgmentList;
    private GetItemListener getItemListener;
    private int pageLimit;
    private ViewPager viewPager;

    public void setAnimation(SlideViewAnimation animation) {
        this.animation = animation;
    }

    public SlideViewPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        this.fragmentManager = fm;
        this.viewPager = viewPager;
        initFrgmentList = new ArrayList<Fragment>();
        this.animation = new ScrollAnimation();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        fragment = getItemListener.getItem(position);
        if(fragment != null)
            initFrgmentList.add(fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return 9999;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        for(Fragment f : initFrgmentList){
            animation.updateViewSetting(f.getView());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        View[] views = new View[]{
                getRootView(position),
                getRootView(position+1)
        };
        animation.startAnimation(positionOffset,views);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface GetItemListener {
        Fragment getItem(int position);
    }

    public void setGetItemListener(GetItemListener getItemListener) {
        try {
            this.getItemListener = getItemListener;
        }catch(ClassCastException e){
                e.printStackTrace();
        }
    }

    private View getRootView(int position) {
        return fragmentManager.findFragmentByTag(this.getFragmentTag(position)).getView();
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + viewPager.getId() + ":" + position;
    }
}