package com.example.soul.viewdrayhelpedemo;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * * @author soul
 *
 * @项目名:ViewDrayhelpeDemo
 * @包名: com.example.soul.viewdrayhelpedemo
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/7/15 16:35
 */

public class DragLayout extends FrameLayout {

    private ViewDragHelper mDragHelper;
    private ViewGroup mLeftContent;
    private ViewGroup mMainContent;

    public DragLayout(Context context) {
        this(context,null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDragHelper = ViewDragHelper.create(this, 1.0f, mCalback);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mDragHelper.processTouchEvent(event);
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    private int mRange;
    ViewDragHelper.Callback mCalback= new ViewDragHelper.Callback() {

        //1 决定了当前child是否可以被拖拽
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        //当View被捕获时，调用
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        //2 获取横向的拖拽范围，不限制真正的左右范围=
        //用于确定动画的时长，横向是否可以滑动 >0即可


        @Override
        public int getViewHorizontalDragRange(View child) {

            return mRange;
        }
        //3 返回值 决定了要移动到的位置，此时还未发生真正的移动:修正将要移动到的位置
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child==mMainContent){
//                left =fixLeft(left);
            }

            return  left;
        }


    };


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount()<2){
            throw  new IllegalStateException("You must have 2 children at least!");
        }
        if (!(getChildAt(0)instanceof ViewGroup)||!(getChildAt(2)instanceof  ViewGroup)){
            throw  new IllegalArgumentException("Your hcild must be an instance of ViewGroup!");

        }
        mLeftContent = (ViewGroup) getChildAt(0);
        mMainContent = (ViewGroup) getChildAt(1);


    }
}
