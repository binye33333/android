package com.teach.yo.codeshop.view.luckPan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.teach.yo.codeshop.R;


/**
 * Created by 陈哟哟 on 17/3/20.
 * <p>
 * www.chenyoyo.cn
 */

public class LuckView extends View {

    private boolean selfDrawPan = false;

    //抽奖配置
    private LuckBean mLuckBean;
    //当前的旋转角度
    private int currentDegree = 0;
    //加速减速插值器
    private AccelerateDecelerateInterpolator mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    // 抽奖动画开始时间   currentTime -startTime >=animTime 表示抽奖结束
    private long startTime;
    //抽奖整个过程持续时间
    private long animTime = 3000;
    //抽奖整个过程 旋转的角度  currentDegree->allDegree  10圈
    private int allDegree = 0;
    //是否正在抽奖
    private boolean isAnim;

    //抽奖回调接口
    private LuckPanListener mLuckPanListener;

    //默认的奖项图标
    private Bitmap iconDefeat = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
    //抽奖箭头
    private Bitmap mArrow = ((BitmapDrawable) getResources().getDrawable(R.mipmap.icon_lottery_arrow)).getBitmap();
    //幸运转盘
    private Bitmap mLotteryPan = ((BitmapDrawable) getResources().getDrawable(R.mipmap.icon_lottery_pan)).getBitmap();
    //画笔
    private Paint mPaint = new Paint();
    //幸运转盘的绘制区域
    private RectF mCircleRect;
    //抽中的奖项索引位置
    private int resultIndex = 0;

    public LuckView(Context context) {
        super(context);
    }

    public LuckView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LuckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置奖品相关信息
     *
     * @param bean 奖品设置
     */
    public void loadData(LuckBean bean) {
        mLuckBean = bean;
        //默认当前角度 让指针刚好指向第一个奖项的正中间
        currentDegree = 90 + 360 / mLuckBean.details.size() / 2;

        //默认旋转10圈
        allDegree = currentDegree + 3600;
        invalidate();
    }


    /**
     * 设置抽中奖品结果
     *
     * @param index 抽中奖品在列表中的位置
     */
    public void stopIndex(int index) {
        //在原有旋转角度基础上，增加一个偏移量，刚好停到对应的奖品上面去
        allDegree += (index * 360 / mLuckBean.details.size());
        resultIndex = index;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mCircleRect == null) {
            return;
        }

        if (mLuckBean == null || mLuckBean.details.size() == 0) {
            return;
        }

        // 自己绘制奖品奖项圆盘的方式
        //本次绘制时候，开始的角度偏移量
        int arcStart = currentDegree;
        //扇形区域扫过的面积
        int increase = 360 / mLuckBean.details.size();
        for (int i = 0; i < mLuckBean.details.size(); i++) {
            LuckItemInfo info = mLuckBean.details.get(i);
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.parseColor(info.color));
            //画扇形
            canvas.drawArc(mCircleRect, arcStart, increase, true, mPaint);

            //画文字 奖品图标
            {//此处代码 借助了hongyang 大神的文章写出来的
                mPaint.setTextSize(30);
                mPaint.setColor(Color.BLACK);
                drawText(canvas, arcStart, increase, info.prize_name);
                drawIcon(canvas, arcStart, increase);
            }
            arcStart += increase;
        }

        if (!selfDrawPan) {
            {// 旋转转盘的方式
                canvas.save();
                canvas.rotate(currentDegree, mCircleRect.centerX(), mCircleRect.centerY());
                canvas.drawBitmap(mLotteryPan, 0, 0, null);
                canvas.restore();
            }
        }

        canvas.drawBitmap(mArrow, (getMeasuredWidth() - mArrow.getWidth()) / 2, (getMeasuredHeight() - mArrow.getHeight()) / 2, null);

        if (isAnim) {
            doCircle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mArrow == null) {
            return true;
        }

        int centerX = getMeasuredWidth() / 2;
        int centerY = getMeasuredHeight() / 2;

        if (centerX <= 0 || centerY <= 0) {
            return true;
        }

        if (Math.abs(event.getX() - centerX) > mArrow.getWidth() / 2) {
            return true;
        }

        if (Math.abs(event.getY() - centerY) > mArrow.getHeight() / 2) {
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onStartViewClick();
        }

        return true;
    }

    private void drawText(Canvas canvas, float startAngle, float sweepAngle, String string) {
        Path path = new Path();
        path.addArc(mCircleRect, startAngle, sweepAngle);
        float textWidth = mPaint.measureText(string);
        // 利用水平偏移让文字居中
        float hOffset = (float) (getMeasuredWidth() * Math.PI / mLuckBean.details.size() / 2 - textWidth / 2);// 水平偏移
        float vOffset = getMeasuredHeight() / 2 / 6;// 垂直偏移
        canvas.drawTextOnPath(string, path, hOffset, vOffset, mPaint);
    }

    private void drawIcon(Canvas canvas, float startAngle, int sweep) {
        // 设置图片的宽度为直径的1/8
        int imgWidth = getMeasuredWidth() / 8;

        //扇形中间线条的角度
        float angle = (float) ((sweep / 2 + startAngle) * (Math.PI / 180));

        int x = (int) (mCircleRect.centerX() + getMeasuredWidth() / 2 / 2 * Math.cos(angle));
        int y = (int) (mCircleRect.centerY() + getMeasuredWidth() / 2 / 2 * Math.sin(angle));

        // 确定绘制图片的位置
        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth
                / 2, y + imgWidth / 2);

        canvas.drawBitmap(iconDefeat, null, rect, null);
    }

    /**
     * 设置回调接口监听
     *
     * @param luckPanListener 监听器
     */
    public void setLuckPanListener(LuckPanListener luckPanListener) {
        mLuckPanListener = luckPanListener;
    }

    /**
     * 开始抽奖
     */
    private void onStartViewClick() {

        //抽奖过程的时候，不允许再次抽奖
        if (isAnim) {
            return;
        }

        //抽奖开始时间
        startTime = System.currentTimeMillis();
        isAnim = true;
        if (mLuckPanListener != null) {
            mLuckPanListener.onStartLottery();
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w != 0 && h != 0) {
            mCircleRect = new RectF(0, 0, w, h);
        }

        if (w != h) {
            throw new RuntimeException("幸运转盘需要放到一个正方形中");
        }
    }

    /**
     * 循环触发器
     */
    private void doCircle() {

        //抽奖结束 ，充值字段
        if (System.currentTimeMillis() * 1.0 - startTime >= animTime) {
            isAnim = false;
            currentDegree = 90 + 360 / mLuckBean.details.size() / 2;
            allDegree = currentDegree + 3600;
            startTime = 0;
            if (mLuckPanListener != null) {
                mLuckPanListener.onStopLottery(mLuckBean.details.get(resultIndex));
            }
            resultIndex = 0;
            return;
        }

        //插值器 计算当前进度，实现加速减速效果
        float outPut = mAccelerateDecelerateInterpolator.getInterpolation((float) ((System.currentTimeMillis() * 1.0 - startTime) / animTime));
        currentDegree = (int) (allDegree * outPut);
        //出发重绘
        postInvalidate();
    }


}
