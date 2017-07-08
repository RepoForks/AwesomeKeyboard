package com.hoanganhtuan95ptit.awesomekeyboard.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.adapter.ColorAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnColorClickListener;

/**
 * Created by HOANG ANH TUAN on 6/28/2017.
 */

public class ColorLayout extends RelativeLayout implements OnColorClickListener {

    private OnItemColorClickListener onItemColorClickListener;

    public ColorLayout(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_photo, this, false);
        initRecyclerView(view);
        this.addView(view);
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ColorAdapter colorAdapter = new ColorAdapter((Activity) this.getContext());
        colorAdapter.setOnColorClickListener(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(colorAdapter);
        addData(colorAdapter);
    }

    public void executeColor(){
        this.setVisibility(VISIBLE);
    }

    private void addData(ColorAdapter colorAdapter) {
        colorAdapter.add("#1ecef3");
        colorAdapter.add("#12cf13");
        colorAdapter.add("#43bec5");
        colorAdapter.add("#67b967");
        colorAdapter.add("#0084fd");
        colorAdapter.add("#6599cb");
        colorAdapter.add("#7547ff");
        colorAdapter.add("#a695c9");
        colorAdapter.add("#d4a88b");
        colorAdapter.add("#d496bb");
        colorAdapter.add("#e58684");
        colorAdapter.add("#f83c4b");
        colorAdapter.add("#fd5ca2");
        colorAdapter.add("#fdc300");
        colorAdapter.add("#ff7f28");
    }

    public void setOnItemColorClickListener(OnItemColorClickListener onItemColorClickListener) {
        this.onItemColorClickListener = onItemColorClickListener;
    }

    @Override
    public void onColorClicked(String color) {
        if (onItemColorClickListener != null) onItemColorClickListener.onItemColorClicked(color);
    }
}
