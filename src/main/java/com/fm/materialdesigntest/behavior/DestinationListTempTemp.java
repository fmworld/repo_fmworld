package com.fm.materialdesigntest.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.fm.materialdesigntest.R;

/**
 * Created by zhoufengan on 15/12/5.
 * 目的地列表带头部渐隐渐显搜索框UI
 */
public class DestinationListTempTemp extends FrameLayout {
    private int offsetTop = 0;
    private FixedTopView topView;
    private RecyclerView contentList;

    private int viewTopMargin = 0;
    private int titleResource;
    private int alphaRect;
    private int targetId =0;
    private int targetIndex =0;

    public DestinationListTempTemp(Context context, AttributeSet attrs) {
        super(context, attrs);
        intParams(attrs);
        initList();
        initTopView();
    }

    /**
     * 获取XML定义的数据
     * @param attrs
     */
    private void intParams(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.destinationList);
        titleResource = a.getResourceId(R.styleable.destinationList_titleResource, 0);
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
                if (0 == alphaRect) {
                    intTargetParams(R.id.listitem_name);
                }
                topView.setAlphaFactor((viewTopMargin + offsetTop), alphaRect);


            }
        });
        this.addView(contentList);
    }

    /**
     * 生成顶部view
     */
    private void initTopView() {
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        topView = new FixedTopView(getContext());
        topView.initLayout(titleResource);
        topView.setVisibility(View.GONE);
        this.addView(topView, params);
    }

    /**
     * 初始化目标view的参数
     */
    private void intTargetParams(int targetId) {
        View target = contentList.getChildAt(targetIndex).findViewById(targetId);
        if(null !=target ) {
            viewTopMargin = contentList.getTop() + target.getTop();
            alphaRect = contentList.getChildAt(targetIndex).getBottom() - target.getTop();
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        contentList.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        contentList.setLayoutManager(manager);
    }
}
