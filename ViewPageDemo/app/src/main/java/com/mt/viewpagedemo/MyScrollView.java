package com.mt.viewpagedemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by admin on 2019/5/28.
 */

public class MyScrollView extends HorizontalScrollView {
    private int iDownX;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean onTouchEvent(MotionEvent e) {
        int iAction = e.getAction();
        switch (iAction) {
            case MotionEvent.ACTION_DOWN:
                iDownX = (int) e.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int iMoveX = (int) e.getX();
                if (Math.abs(iMoveX - iDownX) < 1500) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                int iUpX = (int) e.getX();
                if (Math.abs(iUpX - iDownX) < 1500) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(e);
    }

}
