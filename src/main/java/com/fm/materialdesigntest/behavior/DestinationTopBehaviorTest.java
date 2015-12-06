package com.fm.materialdesigntest.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fm.materialdesigntest.R;

/**
 * Created by zhoufengan on 15/12/5.
 */
public class DestinationTopBehaviorTest extends CoordinatorLayout.Behavior<View> {
    int topOffset = 0;
    View dependencyView;

    public DestinationTopBehaviorTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency.getId() == R.id.second;
    }
//


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(child.getHeight() * 5);
        return true;
    }

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
        child.setY(child.getY() - dyConsumed);

    }

    //
    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
//当进行快速滑动
        Log.v("coscroll", "velocityY " + velocityY);
        Log.v("coscroll", "onNestedFling scrollY " + coordinatorLayout.findViewById(R.id.second).getScrollY());
        coordinatorLayout.dispatchNestedFling(velocityX, velocityY, consumed);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);

    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.v("coscroll", "onNestedPreFling velocityY " + velocityY);
        Log.v("coscroll", "onNestedPreFling scrollY " + coordinatorLayout.findViewById(R.id.second).getScrollY());

        return true;
    }
}
