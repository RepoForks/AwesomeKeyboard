package com.hoanganhtuan95ptit.awesomekeyboard.view.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.model.Photo;

import java.util.Collections;
import java.util.Comparator;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by DuyPC on 5/23/2017.
 */

public class PhotoAdapter extends BaseAdapter<Photo> {

    private OnPhotoClickListener onPhotoClickListener;

    public PhotoAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected void sort() {
        super.sort();
        Collections.sort(getDatas(), new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                if (o1.getTime() > o2.getTime()) return -1;
                if (o1.getTime() < o2.getTime()) return 1;
                return 0;
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int padding = (int) activity.getResources().getDimension(R.dimen.itemPhotoPadding);
        View v = inflater.inflate(R.layout.item_photo, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.imagePhoto.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
        viewHolder.rePhoto.setPadding(padding, padding, padding, padding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Photo photo = getDatas().get(position);

        // load anh
        Uri uri = Uri.parse("file://" + photo.getUrl());
        int width = 400, height = 400;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(viewHolder.imagePhoto.getController())
                .setImageRequest(request)
                .build();
        viewHolder.imagePhoto.setController(controller);

        //blure anh
        if (!photo.isSelect()) {
            Blurry.delete(viewHolder.rePhoto);
            viewHolder.tvSend.setVisibility(View.GONE);
        } else {
            Blurry.with(activity)
                    .radius(6)
                    .sampling(6)
                    .color(Color.argb(66, 255, 255, 255))
                    .async()
                    .animate(100)
                    .onto(viewHolder.rePhoto);
            viewHolder.tvSend.setVisibility(View.VISIBLE);
        }

        viewHolder.imagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photo.isSelect()) {
                    Blurry.delete(viewHolder.rePhoto);
                    viewHolder.tvSend.setVisibility(View.GONE);
                    photo.setSelect(false);
                } else {
                    unseleckAll();
                    Blurry.with(activity)
                            .radius(6)
                            .sampling(6)
                            .color(Color.argb(66, 255, 255, 255))
                            .async()
                            .animate(100)
                            .onto(viewHolder.rePhoto);
                    viewHolder.tvSend.setVisibility(View.VISIBLE);
                    photo.setSelect(true);
                }
            }
        });
        viewHolder.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPhotoClickListener!=null)onPhotoClickListener.onPhotoClicked(photo.getUrl());
            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imagePhoto;
        TextView tvSend;
        RelativeLayout rePhoto;

        ViewHolder(View view) {
            super(view);
            imagePhoto = (SimpleDraweeView) view.findViewById(R.id.image_photo);
            tvSend = (TextView) view.findViewById(R.id.tv_send_photo);
            rePhoto = (RelativeLayout) view.findViewById(R.id.re_photo);
        }
    }

    public void unseleckAll(){
        for (int i = 0; i < getDatas().size(); i++) {
            if (getDatas().get(i).isSelect()) {
                getDatas().get(i).setSelect(false);
                notifyItemChanged(i);
            }
        }
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }

}
