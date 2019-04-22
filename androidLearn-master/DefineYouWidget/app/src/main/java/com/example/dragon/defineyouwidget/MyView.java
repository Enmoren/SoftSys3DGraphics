package com.example.dragon.defineyouwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * This file created by dragon on 2016/10/3 20:03,
 * belong to com.example.dragon.defineyouwidget .
 */
public class MyView extends View {

    /**
     * 文本
     */
    private String mTitleText;
    private String mContent;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;
//背景颜色
    private int mTitleBackGroundColor;
    private int mContentBackGroundColor;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBoundTitle;
    private Rect mBoundContent;
    private Paint mPaint;

    public MyView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public MyView(Context context)
    {
        this(context, null);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = a.getString(attr);
                    break;
//默认标题背景颜色绿
                case R.styleable.CustomTitleView_titleBackGroundColor:
                    mTitleBackGroundColor = a.getColor(attr,Color.GREEN);
                    break;
//默认内容背景颜色黄
                case R.styleable.CustomTitleView_contentBackGroundColor:
                    mContentBackGroundColor = a.getColor(attr,Color.YELLOW);
                    break;
                case R.styleable.CustomTitleView_content:
                    mContent = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高，初始化画笔
         */
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        // mPaint.setColor(mTitleTextColor);
//        mBoundTitle = new Rect();
//        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBoundTitle);
//        mBoundContent = new Rect();
//        mPaint.getTextBounds(mContent, mTitleText.length(), mTitleText.length(), mBoundContent);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint.setColor(mTitleBackGroundColor);
        RectF re1 = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(re1,20,20,mPaint);
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mContentBackGroundColor);
        RectF re2 = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()/2);
        canvas.drawRoundRect(re2,20,0,mPaint);
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight()/2, mPaint);
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(this.mTitleText, getWidth() / 2 , getHeight() / 3 +10 , mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mContent, getWidth() / 2 , getHeight()-getHeight()/7 , mPaint);
    }



}
