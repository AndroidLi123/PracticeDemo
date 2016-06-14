package com.gank.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.gank.R;

public class MyLoadingView extends View {
    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private Paint mPaint;
    private int mCurrentCount = 0;
    private int mSplitSize;
    private int mCount;
    private boolean isNext = false;
    private int mDelay = 20;
    private int type;
    private float lineLength;

    public MyLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLoadingView(Context context) {
        this(context, null);
    }

    public MyLoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyle, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomVolumControlBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomVolumControlBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.CYAN);
                    break;

                case R.styleable.CustomVolumControlBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_dotCount:
                    mCount = a.getInt(attr, 20);
                    break;
                case R.styleable.CustomVolumControlBar_splitSize:
                    mSplitSize = a.getInt(attr, 20);
                    break;
                case R.styleable.CustomVolumControlBar_type:
                    type = a.getInt(attr, 1);
                    break;
                case R.styleable.CustomVolumControlBar_linelength:
                    lineLength = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_delay:
                    mDelay = a.getInt(attr, 20);

            }
        }
        a.recycle();
        mPaint = new Paint();
        new Thread() {
            public void run() {
                while (true) {
                    mCurrentCount++;
                    if (mCurrentCount == mCount) {
                        mCurrentCount = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        }.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int centreX, centreY, centre;
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        centreX = width / 2 + getPaddingLeft();
        centreY = height / 2 + getPaddingTop();
        centre = width < height ? width / 2 : height / 2;
        int radius = centre - mCircleWidth;

        if (type == 0)
            drawOval(canvas, centreX, centreY, radius);
        else
            drawLine(canvas, radius, centreX, centreY);

    }

    private void drawLine(Canvas canvas, int radius, int centreX, int centreY) {
        if (!isNext)
            mPaint.setColor(mFirstColor);
        else
            mPaint.setColor(mSecondColor);
        int index = 360 / mCount;
        drawAllOrCurrentLine(canvas, index, mCount, radius, true, centreX, centreX);
        drawAllOrCurrentLine(canvas, index, mCurrentCount, radius, false, centreX, centreY);

    }

    private void drawAllOrCurrentLine(Canvas canvas, int index, int mCount, int radius, boolean isDrawAllLine, int centreX, int centreY) {
        float start_x;
        float start_y;
        float end_x;
        float end_y;
        if (!isDrawAllLine)
            if (mPaint.getColor() == mFirstColor) mPaint.setColor(mSecondColor);
            else mPaint.setColor(mFirstColor);
        for (int i = 0; i < mCount; ++i) {
            start_x = radius * (float) Math.cos(Math.PI / 180 * (i * index - 90));
            start_y = radius * (float) Math.sin(Math.PI / 180 * (i * index - 90));
            end_x = start_x + lineLength * (float) Math.cos(Math.PI / 180 *( i * 6 - 90));
            end_y = start_y + lineLength * (float) Math.sin(Math.PI / 180 * (i * 6 - 90));
            start_x += centreX;
            end_x += centreX;
            start_y += centreY;
            end_y += centreY;
            canvas.drawLine(start_x, start_y, end_x, end_y, mPaint);
        }
    }

    private void drawOval(Canvas canvas, int centreX, int centreY, int radius) {
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
        RectF oval = new RectF(centreX - radius, centreY - radius, centreX + radius, centreY + radius);
        if (!isNext)
            mPaint.setColor(mFirstColor);
        else
            mPaint.setColor(mSecondColor);
        drawAllOrCurrentCircle(canvas, itemSize, oval, true, mCount);
        drawAllOrCurrentCircle(canvas, itemSize, oval, false, mCurrentCount);

    }

    private void drawAllOrCurrentCircle(Canvas canvas, float itemSize, RectF oval, boolean isDrawAll, int mCount) {
        if (!isDrawAll)
            if (mPaint.getColor() == mFirstColor) mPaint.setColor(mSecondColor);
            else mPaint.setColor(mFirstColor);

        for (int i = 0; i < mCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize) - 90, itemSize, false, mPaint);
        }
    }

}
