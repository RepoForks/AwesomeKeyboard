package com.hoanganhtuan95ptit.awesomekeyboard.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.utils.FileUtils;
import com.hoanganhtuan95ptit.awesomekeyboard.utils.callback.OnPictureRead;
import com.hoanganhtuan95ptit.awesomekeyboard.view.adapter.PhotoAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.model.Photo;


/**
 * Created by HOANG ANH TUAN on 6/28/2017.
 */

public class PhotoLayout extends RelativeLayout implements OnPhotoClickListener,OnPictureRead {

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private boolean readFile=false;

    private OnItemPhotoClickListener onItemPhotoClickListener;

    public PhotoLayout(Context context) {
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        photoAdapter = new PhotoAdapter((Activity) this.getContext());
        photoAdapter.setOnPhotoClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(photoAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                photoAdapter.unseleckAll();
            }
        });
    }

    public void executePhoto() {
        this.setVisibility(VISIBLE);
        if (!readFile) {
            readFile=true;
            FileUtils.readAllPicture((Activity) getContext(),this);
        }
    }


    public void setOnItemPhotoClickListener(OnItemPhotoClickListener onItemPhotoClickListener) {
        this.onItemPhotoClickListener = onItemPhotoClickListener;
    }

    @Override
    public void pictureRead(Object... objects) {
        Photo photo = new Photo();
        photo.setSelect(false);
        photo.setTime((Long) objects[1]);
        photo.setUrl((String) objects[0]);
        photoAdapter.add(photo);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onPhotoClicked(String url) {
        if(onItemPhotoClickListener!=null)onItemPhotoClickListener.onItemPhotoClicked(url);
    }

}
