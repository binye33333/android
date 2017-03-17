package com.teach.yo.codeshop.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by chenyou729 on 17/3/16.
 */

public class FanChartView extends View {

    //需要计算占比的数据项集合
    private List<ChartBean> mList;
    //总数，用于计算占比
    private int all;
    private Paint mPaint = new Paint();
    //画扇形图的正方形，内切圆
    private RectF mRectF;

    private int currentItemIndex = 0;
    //扇形圆的直径 占用的正方形宽度比例
    double ratio = 1.0 / 2;

    public FanChartView(Context context) {
        super(context);
    }

    public FanChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FanChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setData(List<ChartBean> list) {

        mList = list;
        if (mList == null) {
            return;
        }

        all = 0;
        for (ChartBean bean : mList) {
            all += bean.number;
        }
        currentItemIndex = 0;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (mList == null) {
            return;
        }

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width + height == 0) {
            return;
        }

        double angle = 0;
        for (int i = 0; i < currentItemIndex; i++) {
            ChartBean bean = mList.get(i);
            mPaint.reset();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
            mPaint.setColor(bean.color);

            //画扇形
            double increase = (double) bean.number / all * 360;
            canvas.drawArc(mRectF, (float) angle, (float) increase, true, mPaint);

            //画从圆心伸出去的直线
            double θ = angle + increase / 2;
            double lineX = (mRectF.width() / 2 + 20) * Math.cos(θ * Math.PI / 180);
            double lineY = (mRectF.width() / 2 + 20) * Math.sin(θ * Math.PI / 180);
            mPaint.setStrokeWidth(1);
            canvas.drawLine(mRectF.centerX(), mRectF.centerY(), mRectF.centerX() + (float) lineX, mRectF.centerY() + (float) lineY, mPaint);


            mPaint.setTextSize(20);
            String text = bean.name + "比例：" + bean.number * 100 / all + "%";
            float textLength = mPaint.measureText(text);

            //画横着的那根直线
            canvas.drawLine(mRectF.centerX() + (float) lineX, mRectF.centerY() + (float) lineY, mRectF.centerX() + (float) lineX + (float) (lineX / Math.abs(lineX)) * textLength, mRectF.centerY() + (float) lineY, mPaint);

            //左边的时候，减去文字长度，开始画，右边的时候就从坐标开始位置画
            int leftRightX = lineX > 0 ? 0 : -1;
            //画文字
            canvas.drawText(text, mRectF.centerX() + (float) lineX + (float) leftRightX * textLength, mRectF.centerY() + (float) lineY, mPaint);

            angle += increase;
        }

        if (currentItemIndex != mList.size()) {
            postInvalidateDelayed(200);
            currentItemIndex++;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w != h) {
            throw new RuntimeException("视图View应该放到一个正方形内,width != height");
        }

        //圆形和正方形之前的空白间距  得要画线和文字的
        float blank = (float) ((1.0 - ratio) / 2 * w);
        mRectF = new RectF(blank, blank, w - blank, w - blank);
    }
}
