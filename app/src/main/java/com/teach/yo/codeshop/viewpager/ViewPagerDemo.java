package com.teach.yo.codeshop.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teach.yo.codeshop.R;
import com.teach.yo.toollibrary.view.banner.PagerSlidingTabStrip;

/**
 * Created by chenyoyo
 * on 2016/11/28.
 */

public class ViewPagerDemo extends Activity {

    private String[] title = {"page0", "page1", "page2", "page3", "page4", "page5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_demo_layout);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new InnerPagerAdapter(this));
        PagerSlidingTabStrip pagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.PagerTabStrip);
        pagerTabStrip.setViewPager(mViewPager);
    }


    class InnerPagerAdapter extends PagerAdapter {

        private Context mContext;

        InnerPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_pager_layout, container, false);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
