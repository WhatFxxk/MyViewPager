package com.love.cook.myviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by wanghaixin on 16/12/3.
 */
public class SetViewPager implements Handler.Callback, ViewPager.OnPageChangeListener, View.OnTouchListener {

    private static final int START_SCOLL2 = 101;
    private static final int START_SCOLL = 100;
    private Handler handler = new Handler(this);
    private ViewPager viewPager;
    private Context context;

    private int setViewPagerCurr;
    private int pointRes, pointRes2;

    public SetViewPager(ViewPager viewPager, Context context, ViewGroup ponitViewGroup, int pointRes, int pointRes2) {
        this.viewPager = viewPager;
        this.context = context;
        this.pointRes = pointRes;
        this.pointRes2 = pointRes2;
        this.viewGroup = ponitViewGroup;
        this.viewPager.addOnPageChangeListener(this);

    }


    public void setPointNumber(int ponitNumber) {
        this.number = ponitNumber;
    }

    public void stopHandler() {
        handler.removeCallbacksAndMessages(null);
    }

    public void setNoHandler(int setViewPagerCurr) {
        this.setViewPagerCurr = setViewPagerCurr;
    }

    //开始调用一次
    public void startHandler(int setViewPagerCurr) {
        if (setViewPagerCurr < 10000) {
            Toast.makeText(context, "数字大于10000", Toast.LENGTH_SHORT).show();
        } else {
            this.setViewPagerCurr = setViewPagerCurr;


            this.viewPager.setOnTouchListener(this);
            handler.sendEmptyMessage(START_SCOLL);
        }


    }

    @Override
    public boolean handleMessage(Message message) {

        switch (message.what) {
            case START_SCOLL2:
                viewPager.setCurrentItem(currposition + 1);
                break;

            case START_SCOLL:


                viewPager.setCurrentItem(currposition);


                break;

        }

        handler.postDelayed(new Runnable() {


            @Override
            public void run() {

                handler.sendEmptyMessage(START_SCOLL2);

            }
        }, 5000);
        return false;


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    private int currposition = setViewPagerCurr;


    @Override
    public void onPageSelected(int position) {


        if (number != 0) {
            viewGroup.getChildAt(currposition % number).setBackgroundResource(pointRes);

            viewGroup.getChildAt(position % number).setBackgroundResource(pointRes2);

        } else {


        }


        currposition = position;

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View view, final MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:


                handler.removeCallbacksAndMessages(null);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        handler.sendEmptyMessage(START_SCOLL2);

                    }
                }, 5000);

                break;


            case MotionEvent.ACTION_UP:


                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        handler.sendEmptyMessage(START_SCOLL2);

                    }
                }, 5000);


                break;

            case MotionEvent.ACTION_MOVE:

                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(START_SCOLL2);
                    }
                }, 5000);


                break;

        }

        return false;
    }


    private ViewGroup viewGroup;

    private int number = 0;

    public void addPoint() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
        for (int i = 0; i < number; i++) {
            layoutParams.setMargins(12, 0, 12, 0);
            View view = new View(context);
            view.setLayoutParams(layoutParams);


            view.setBackgroundResource(pointRes);

            this.viewGroup.addView(view);
        }
        if (number != 0) {
            viewGroup.getChildAt(0).setBackgroundResource(pointRes2);

        }


    }
}
