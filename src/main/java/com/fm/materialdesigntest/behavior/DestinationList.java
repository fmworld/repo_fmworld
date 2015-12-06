package com.fm.materialdesigntest.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.fm.materialdesigntest.R;

/**
 * Created by zhoufengan on 15/12/5.
 */
public class DestinationList extends FrameLayout {
    private int offsetTop = 0;
    private TopView topView;
    private RecyclerView contentList;

    private int viewTopMargin = 0;
    private int titleResource;
    private int contentResource;
    private int targetId =0;
    private int targetIndex =0;
    private int targetPointY=0;

    public DestinationList(Context context, AttributeSet attrs) {
        super(context, attrs);
        intParams(attrs);
        initList();
        initTopView();
    }

    private void intParams(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.destinationList);
        viewTopMargin = a.getInteger(R.styleable.destinationList_viewMarginTop, 200);
        titleResource = a.getResourceId(R.styleable.destinationList_titleResource, 0);
        contentResource = a.getResourceId(R.styleable.destinationList_contentResource, 0);
        targetId = a.getResourceId(R.styleable.destinationList_targetView, 0);
        targetIndex = a.getResourceId(R.styleable.destinationList_targetIndex, 0);
        a.recycle();
    }

    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        contentList = new RecyclerView(getContext());
        contentList.setLayoutManager(manager);
        contentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                offsetTop -= dy;
                targetPointY = viewTopMargin + offsetTop;
                if (dy > 0) {
                    topView.setY(targetPointY >= 0 ?(targetPointY):0);
                } else if (dy < 0 && targetPointY >= 0) {
                    topView.setY(targetPointY);
                }
            }
        });
        this.addView(contentList);
    }

    private void initTopView() {
        LayoutParams params =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.topMargin = viewTopMargin;
        topView = new TopView(getContext());
        topView.initLayout(contentResource, titleResource);
        this.addView(topView,params);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        contentList.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        contentList.setLayoutManager(manager);
    }

    public void setViewTopMargin(int margin) {
        viewTopMargin = margin;
    }

}
