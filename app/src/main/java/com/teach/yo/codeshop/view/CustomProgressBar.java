package com.teach.yo.codeshop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.teach.yo.codeshop.R;

/**
 * 作者：chenyo on 2016/1/12 21:56
 */
public class CustomProgressBar extends View {

    private int mFirstColor = Color.GREEN;
    private int mSecondColor = Color.YELLOW;
    private int mCircleWidth;
    private int mProgress;
    private int mSpeed;
    private Paint mPaint;

    private boolean isNext = true;


    private boolean isStopped = false;
    private RectF oval;

    public CustomProgressBar(Context context) {
        super(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = a.getColor(index, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = a.getColor(index, Color.RED);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(index, 20);// 默认20
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        run();
    }

    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStopped) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        isNext = !isNext;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }


    public void stop() {
        isStopped = true;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心

        int center = getWidth() / 2; // 获取圆心的x坐标
        int radius = center - mCircleWidth / 2;// 半径

        if (oval == null) {
            oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        }

        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环

            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环

            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }
}
