package com.changxin.chapter3;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Scroller;

/**
 * Created by changxin on 2017/1/6.
 */

public class MyButton extends Button {

    private Scroller  mScroller ;

    public MyButton(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public void smoothScroll(int destX , int destY){
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        mScroller.startScroll(scrollX,0,deltaX,0,1000);
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
