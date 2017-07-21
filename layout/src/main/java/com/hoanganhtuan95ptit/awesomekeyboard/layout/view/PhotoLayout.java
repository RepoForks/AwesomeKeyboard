package com.hoanganhtuan95ptit.awesomekeyboard.layout.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.utils.FileUtils;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter.PhotoAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.Photo;

/**
 * Created by HOANG ANH TUAN on 7/15/2017.
 */

public class PhotoLayout extends RelativeLayout implements FileUtils.OnPictureRead,PhotoAdapter.OnPhotoClickListener {

    private OnItemPhotoClickListener onItemPhotoClickListener;

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private Activity activity;

    public PhotoLayout(Context context) {
        super(context);
        init();
    }

    public PhotoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        activity = (Activity) getContext();
        recyclerView = new RecyclerView(getContext());
        photoAdapter = new PhotoAdapter(activity);

        photoAdapter.setlistener(this);

        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(photoAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                 photoAdapter.unSelect();
            }
        });

        addView(recyclerView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void executePhoto() {
        photoAdapter.clear();
        setVisibility(VISIBLE);
        FileUtils.readAllPicture(activity, this);
    }

    public void setOnItemPhotoClickListener(OnItemPhotoClickListener onItemPhotoClickListener) {
        this.onItemPhotoClickListener = onItemPhotoClickListener;
    }

    @Override
    public void pictureRead(Object... objects) {
        Photo photo = new Photo();
        photo.setTimeCreate((long) objects[1]);
        photo.setUrl((String) objects[0]);
        photoAdapter.add(photo);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onPhotoClicked(String url) {
        if(onItemPhotoClickListener!=null)onItemPhotoClickListener.onItemPhotoClicked(url);
    }

}
