package com.fm.materialdesigntest.behavior;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zhoufengan on 15/12/6.
 * 固定在顶部当渐隐渐显UI
 */
public class FixedTopView extends FrameLayout {
    private View titleView;

    public FixedTopView(Context context) {
        super(context);
    }

    public void initLayout(int resoureceTite) {
        if (0 != resoureceTite) {
            this.removeAllViews();
            LayoutParams params =
                    new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            titleView = View.inflate(getContext(), resoureceTite, null);
            this.addView(titleView, params);
        }
    }

    /**
     * 当目标view移出上边界时，titleview渐显
     * 当目标view移进上边界时，titleview渐隐
     * @param pointY 当前目标view的y坐标
     * @param height targetView 的高度
     */
    public void setAlphaFactor(int pointY, int height) {
        this.setVisibility(pointY <= 0 ? View.VISIBLE : View.GONE);
        setAlpha(Math.abs((float) pointY / (float) height));
    }
}
