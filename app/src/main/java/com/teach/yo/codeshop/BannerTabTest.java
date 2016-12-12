package com.teach.yo.codeshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.teach.yo.toollibrary.view.banner.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class BannerTabTest extends AppCompatActivity {

    private ViewPager fragmentViewPager;
    private String[] title = {"拉萨es", "拉萨d", "拉e萨", "拉萨w拉萨"};
    private PagerSlidingTabStrip pagerSlidingTabStrip;

    public static AutoScrollViewPager banner;

    public static View bannerLayout;
    public static FrameLayout bannerContainer;
    List<ListFragment> list = new ArrayList<>();
    public static Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_tab_test);

        fragmentViewPager = (ViewPager) findViewById(R.id.viewPager);
        bannerLayout = LayoutInflater.from(this).inflate(R.layout.banner_head_layout, null);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) bannerLayout.findViewById(R.id.sliding);
        banner = (AutoScrollViewPager) bannerLayout.findViewById(R.id.banner);
        bannerContainer = (FrameLayout) findViewById(R.id.float_banner_layout);
        init();
    }


    private void init() {
        fragmentViewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        pagerSlidingTabStrip.setViewPager(fragmentViewPager);
        fragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewGroup parent = (ViewGroup) BannerTabTest.bannerLayout.getParent();
                if (parent != null) {
                    BannerTabTest.banner.stopAutoScroll();
                    parent.removeView(BannerTabTest.bannerLayout);
                }
                BannerTabTest.bannerContainer.setVisibility(View.VISIBLE);
                BannerTabTest.bannerContainer.addView(BannerTabTest.bannerLayout);
                BannerTabTest.banner.startAutoScroll();

            }

            @Override
            public void onPageSelected(int position) {
                if (list.size() != 0) {
                    currentFragment = list.get(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bannerContainer.addView(bannerLayout);
        BannerAdapter bannerAdapter = new BannerAdapter();
        banner.setAdapter(bannerAdapter);
        banner.setOffscreenPageLimit(3);
        banner.startAutoScroll();
    }

    class BannerAdapter extends PagerAdapter {

        private List<View> list = new ArrayList<>();

        BannerAdapter() {


        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(BannerTabTest.this).inflate(R.layout.banner_item_layout, container, false);
            if (position == 0) {
                view.setBackgroundColor(Color.BLACK);
            } else if (position == 1) {
                view.setBackgroundColor(Color.GREEN);
            } else if (position == 2) {
                view.setBackgroundColor(Color.RED);
            } else if (position == 3) {
                view.setBackgroundColor(Color.GRAY);
            }
            list.add(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    class Adapter extends FragmentStatePagerAdapter {


        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ListFragment();
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
