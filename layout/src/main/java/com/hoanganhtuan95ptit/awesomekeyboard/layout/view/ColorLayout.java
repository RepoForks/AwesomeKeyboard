package com.hoanganhtuan95ptit.awesomekeyboard.layout.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.layout.R;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter.ColorAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback.OnColorClickListener;

/**
 * Created by HOANG ANH TUAN on 6/28/2017.
 */

public class ColorLayout extends RelativeLayout implements OnColorClickListener {

    private OnItemColorClickListener onItemColorClickListener;
    private ColorAdapter colorAdapter;

    public ColorLayout(Context context) {
        super(context);
        initView();
    }

    public ColorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ColorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Activity activity = (Activity) getContext();
        RecyclerView recyclerView = new RecyclerView(getContext());
        colorAdapter = new ColorAdapter(activity);

        int padding = (int) activity.getResources().getDimension(R.dimen.itemColorMargin);
        colorAdapter.setOnColorClickListener(this);

        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(colorAdapter);
        recyclerView.setPadding(padding, 0, padding, 0);

        addView(recyclerView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void executeColor() {
        setVisibility(VISIBLE);
    }

    public void updateData(String color) {
        colorAdapter.add(color);
    }

    public void clearData() {
        colorAdapter.clear();
    }

    public void setOnItemColorClickListener(OnItemColorClickListener onItemColorClickListener) {
        this.onItemColorClickListener = onItemColorClickListener;
    }

    @Override
    public void onColorClicked(String color) {
        if (onItemColorClickListener != null) onItemColorClickListener.onItemColorClicked(color);
    }
}
