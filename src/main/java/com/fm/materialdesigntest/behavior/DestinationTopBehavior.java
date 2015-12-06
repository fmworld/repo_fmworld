package com.fm.materialdesigntest.behavior;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.fm.materialdesigntest.R;

/**
 * Created by zhoufengan on 15/12/5.
 */
public class DestinationTopBehavior extends AppBarLayout.ScrollingViewBehavior {
    int topOffset = 0;
    View dependencyView;
    public DestinationTopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        boolean depended = super.layoutDependsOn(parent,child,dependency);
        if(depended){
            dependencyView = dependency;
        }
        return depended;
    }
//
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.v("coscroll", "onStartNestedScroll");
        return true;//这里返回true，才会接受到后续滑动事件。
    }

    //
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//进行滑动事件处理
        Log.v("coscroll", "dxConsumed  " + dxConsumed + "  dyConsumed  " + dyConsumed + " dxUnconsumed " + dxUnconsumed + "  dyUnconsumed  " + dyUnconsumed);
        topOffset += dyConsumed;
//        Log.v("coscroll", "dependencyView.getHeight() " + dependencyView.getHeight());
//        Log.v("coscroll", "topOffset " + topOffset);
//        child.setBackgroundColor(Color.parseColor("#898900"));
//        child.setY(child.getY() - dyConsumed);
        int visibalHeight = dependencyView.getHeight() - topOffset;
//        Log.v("coscroll", "visibalHeight " + visibalHeight);
        if (visibalHeight < 100 && View.GONE
                == coordinatorLayout.findViewById(R.id.toolBar_content).getVisibility()) {
            coordinatorLayout.findViewById(R.id.toolBar_content).setVisibility(View.VISIBLE);
        } else if (visibalHeight > 100 && View.VISIBLE
                == coordinatorLayout.findViewById(R.id.toolBar_content).getVisibility()) {
            coordinatorLayout.findViewById(R.id.toolBar_content).setVisibility(View.GONE);
        }
    }
//
//    @Override
//    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
////当进行快速滑动
//        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
//    }
}
