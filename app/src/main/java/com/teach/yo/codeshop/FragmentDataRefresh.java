package com.teach.yo.codeshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.teach.yo.codeshop.adapter.FragmentAdapterRefresh;
import com.teach.yo.codeshop.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyou729 on 16/11/14.
 */

public class FragmentDataRefresh extends AppCompatActivity implements View.OnClickListener {


    private FragmentAdapterRefresh mAdapter;

    private ViewPager mViewPager;

    private PagerTabStrip mPagerTabStrip;

    private TextView mButton;

    private List<FragmentInfo> mData = new ArrayList<>();

    private int clickCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_fragment_pager_layout);


        mButton = (TextView) findViewById(R.id.TextView);
        mButton.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.ViewPager);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.PagerTabStrip);

        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        changeData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TextView:
                changeData();
                break;
        }

    }

    private void changeData() {
        mData.clear();

        if (clickCount % 2 == 0) {
            for (int i = 0; i < 9; i++) {
                FragmentInfo info = new FragmentInfo();
                info.name = "tab " + i;
                info.position = i;
                info.id = i;
                mData.add(info);
            }
        } else {
            for (int i = 8; i >= 0; i--) {
                FragmentInfo info = new FragmentInfo();
                info.name = "tab " + i;
                info.position = i;
                info.id = i;
                mData.add(info);
            }
        }

        mAdapter.notifyDataSetChanged();
        clickCount++;
    }


    class FragmentAdapter extends FragmentAdapterRefresh {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        protected int getKeyByPosition(int position) {
            return mData.get(position).id;
        }


        @Override
        protected int getPositionByKey(int key) {
            for (int i = 0; i < mData.size(); i++) {
                FragmentInfo info = mData.get(i);
                if (info.id == key) {
                    return i;
                }
            }
            return super.getPositionByKey(key);
        }

        @Override
        public Fragment getItem(int key) {
            TextShowFragment fragment = new TextShowFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TextShowFragment.PARAM_KEY, mData.get(getPositionByKey(key)));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mData.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mData.get(position).name;
        }

        @Override
        public float getPageWidth(int position) {
            return 0.5f;
        }

        @Override
        public int getItemPosition(Object object) {
            TextShowFragment fragment = (TextShowFragment) object;
            for (int i = 0; i < mData.size(); i++) {
                FragmentInfo info = mData.get(i);
                if (info.id == fragment.getFragmentInfo().id) {
                    return i;
                }
            }
            return POSITION_NONE;
        }
    }


}
