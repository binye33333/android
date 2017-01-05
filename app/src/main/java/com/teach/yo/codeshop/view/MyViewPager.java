package com.teach.yo.codeshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyou729 on 16/12/29.
 */

public class MyViewPager extends LinearLayout {

    private Scroller mScroller;
    private float lastMoveX;

    public MyViewPager(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        mScroller = new Scroller(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        setOrientation(HORIZONTAL);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        setOrientation(HORIZONTAL);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int childCount = getChildCount();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastMoveX = event.getX();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float diff = event.getX() - lastMoveX;
            float childWidth = 0;
            for (int i = 0; i < childCount; i++) {
                childWidth += getChildAt(i).getMeasuredWidth();
            }
            //拖动时候的效果
            float maxScroll = childWidth - getMeasuredWidth(); //最大的向右滑动距离
            if (diff < 0) {  //往左滑动 需要判断是否到了最右边
                if (getScrollX() + (-diff) > maxScroll) {
                    //滑到边界了，直接滚动到边界
                    scrollTo((int) maxScroll, 0);
                } else {
                    //手指移动多少就滑动多少
                    scrollBy((int) -diff, 0);
                }
            } else if (diff > 0) {
                if (getScrollX() + (-diff) < 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy((int) -diff, 0);
                }
            }
            lastMoveX = event.getX();
        }
        //放下时候的页面滚动效果
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
            int dx = targetIndex * getWidth() - getScrollX();
            mScroller.startScroll(getScrollX(), 0, dx, 0);
            invalidate();
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
             //需要显示调用重绘制
            invalidate();
        }
    }
}
