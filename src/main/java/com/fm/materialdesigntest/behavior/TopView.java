package com.fm.materialdesigntest.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zhoufengan on 15/12/6.
 * 头部可滑动文件
 */
public class TopView extends FrameLayout {
    private View contentView;
    private View titleView;

    public TopView(Context context) {
        super(context);
    }

    public void initLayout(int resoureceContent, int resoureceTite) {
        if (0 != resoureceContent && 0 != resoureceTite) {
            this.removeAllViews();
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            contentView = View.inflate(getContext(), resoureceContent, null);
            titleView = View.inflate(getContext(), resoureceTite, null);
            titleView.setVisibility(View.GONE);
            this.addView(contentView, params);
            this.addView(titleView, params);
        }
    }


//    private void setAlphaFactor(int distance){
//        if(0 != getHeight()){
//            setAlphaFactor((float) distance / (float) getHeight());
//        }
//    }
    /**
     * 设置上下两层view的显示与隐藏百分比,；
     *
     * @param alphaFactor 假定先显示conetentview,当alphaFactor为1时显示titleView，隐藏contentView
     */
    private void setAlphaFactor(int alphaFactor) {
        if (0 == alphaFactor) {
            titleView.setVisibility(View.VISIBLE);
            contentView.setVisibility(View.GONE);

        }
        else{
            titleView.setVisibility(View.GONE);
            contentView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置上下两层view的显示与隐藏百分比,；
     *
     * @param alphaFactor 假定先显示conetentview,当alphaFactor为1时显示titleView，隐藏contentView
     */
    private void setAlphaFactorComplex(float alphaFactor) {
        if (0 == alphaFactor) {
            if (contentView.getVisibility() != View.VISIBLE) {
                contentView.setVisibility(View.VISIBLE);
                contentView.setAlpha(0);
            }

            if (titleView.getVisibility() != View.GONE) {
                titleView.setVisibility(View.GONE);
            }
        } else if (1f == alphaFactor) {
            if (contentView.getVisibility() != View.GONE) {
                contentView.setVisibility(View.GONE);
            }

            if (titleView.getVisibility() != View.VISIBLE) {
                titleView.setVisibility(View.VISIBLE);
                titleView.setAlpha(0);
            }
        } else{
            titleView.setVisibility(View.VISIBLE);
            titleView.setAlpha(1f - alphaFactor);
            contentView.setVisibility(View.VISIBLE);
            contentView.setAlpha(alphaFactor);
        }
    }

    /**
     * 设置当前y坐标，如果pointY < 0,则停止在坐标y = 0处,如果pointY大于初始化坐标Y，则停止在初始化位置
     * @param pointY
     */
    public void setY(int pointY){
        super.setY(pointY);
        Log.v("destscrol", "pointY  " + pointY);
        setAlphaFactor(pointY);
    }

    /**
     * 向上滑动时，坐标设置，如果pointY < 0,则停止在坐标y = 0处
     * @param pointY
     */
    public void setUpY(int pointY){
        super.setY(pointY);
//        setAlphaFactor(pointY);
    }
}
