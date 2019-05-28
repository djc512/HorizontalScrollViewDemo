package com.mt.viewpagedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "DJC";
    private LinearLayout ll_container;
    private int marginRight;
    private MyScrollView hsv;
    private int marginScreen;
    private int marginItem;
    private LinearLayout ll_container_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCongfig();
        initView();
        setView();
    }

    //条目之间的间距
    int itemMargin = 24;
    //与屏幕之间的间距
    int screemargin = 48;
    //条目的宽度
    private int itemWidth;
    //条目的高度

    private void getCongfig() {
        //获取屏幕的宽度
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        Log.i(TAG, "setViewPostion: " + width);

        //获取条目的宽度
        itemWidth = (width - DensityUtil.px2dip(this, 2 * screemargin + itemMargin)) / 2;
        Log.i(TAG, "itemWidth: " + itemWidth);

        //条目与屏幕的间距
        marginScreen = (DensityUtil.px2dip(this, screemargin));
        //获取条目间距
        marginItem = (DensityUtil.px2dip(this, itemMargin)) / 2;
        Log.i(TAG, "marginRight: " + marginRight);
    }

    private void initView() {
        hsv = (MyScrollView) findViewById(R.id.hsv);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ll_container_1 = (LinearLayout) findViewById(R.id.ll_container_1);
    }

    private int itemCount = 2;

    private void setView() {
        if (itemCount == 1) {
            hsv.setVisibility(View.VISIBLE);
            ll_container_1.setVisibility(View.VISIBLE);
            TextView tv = new TextView(this);
            tv.setTextSize(15);
            tv.setText("Cam0");
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.shape_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, itemWidth / 5);
            tv.setLayoutParams(params);
            ll_container_1.addView(tv);
        } else {
            hsv.setVisibility(View.VISIBLE);
            ll_container_1.setVisibility(View.GONE);
            for (int i = 0; i < itemCount; i++) {
                TextView tv_2 = new TextView(this);
                tv_2.setTextSize(15);
                tv_2.setText("Cam" + i);
                tv_2.setGravity(Gravity.CENTER);
                tv_2.setBackgroundResource(R.drawable.shape_bg);
                LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(itemWidth, itemWidth / 5);
                if (i == 0) {
                    params_2.leftMargin = marginScreen;
                    params_2.rightMargin = marginItem;
                    Log.i(TAG, " params_2.leftMargin: "+marginScreen);
                    Log.i(TAG, " params_2.rightMargin: "+marginItem);
                } else if (i == (itemCount - 1)) {
                    params_2.leftMargin = marginItem;
                    params_2.rightMargin = marginScreen;

                    Log.i(TAG, " params_2.leftMargin: "+marginItem+"---");
                    Log.i(TAG, " params_2.rightMargin: "+marginScreen+"---");
                } else {
                    Log.i(TAG, " params_2.leftMargin: "+marginItem+"+++");
                    Log.i(TAG, " params_2.rightMargin: "+marginItem+"+++");
                    params_2.leftMargin = marginItem;
                    params_2.rightMargin = marginItem;
                }
                tv_2.setLayoutParams(params_2);
                tv_2.setId(i);
                tv_2.setOnClickListener(this);
                ll_container.addView(tv_2);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        Log.i(TAG, "onClick-vId: " + vId);
        for (int i = 0; i < itemCount; i++) {
            if (vId == i) {
                int offSet = itemWidth / 2 * (2 * i - 1);
                Log.i(TAG, "onClick-itemWidth/4: " + offSet);
                hsv.smoothScrollTo(offSet, 0);
            }
            if (i == vId) {
                Toast.makeText(this, "点击条目" + i, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
