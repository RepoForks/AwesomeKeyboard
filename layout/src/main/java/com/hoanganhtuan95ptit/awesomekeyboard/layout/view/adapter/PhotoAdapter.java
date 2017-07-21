package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.R;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.Photo;

import java.util.Collections;
import java.util.Comparator;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by HOANG ANH TUAN on 7/15/2017.
 */

public class PhotoAdapter extends BaseAdapter<Photo> {

    private OnPhotoClickListener listener;

    private int positionSelected = -1;
    private static final int photoSizeDefault = 380;

    public PhotoAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int padding = (int) activity.getResources().getDimension(R.dimen.itemPhotoPadding);
        View v = inflater.inflate(R.layout.item_photo, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.rePhoto.setPadding(padding, padding, padding, padding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Photo photo = getDatas().get(position);

        Uri uri = Uri.parse("file://" + photo.getUrl());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(photoSizeDefault, photoSizeDefault))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(viewHolder.img.getController())
                .setImageRequest(request)
                .build();
        viewHolder.img.setController(controller);

        if (position != positionSelected) {
            unSelect(viewHolder);
        } else {
            select(viewHolder);
        }

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.getAdapterPosition() == positionSelected) {
                    unSelect(viewHolder);
                    positionSelected = -1;
                } else {
                    select(viewHolder);
                    int positionSelectOld = positionSelected;
                    positionSelected = viewHolder.getAdapterPosition();
                    if (positionSelectOld >= 0) notifyItemChanged(positionSelectOld);
                }
            }
        });
        viewHolder.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onPhotoClicked(photo.getUrl());
            }
        });

    }

    @Override
    protected void sort() {
        super.sort();
        Collections.sort(getDatas(), new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                if (o1.getTimeCreate() > o2.getTimeCreate()) return -1;
                if (o1.getTimeCreate() < o2.getTimeCreate()) return 1;
                return 0;
            }
        });
    }

    private void unSelect(ViewHolder viewHolder) {
        Blurry.delete(viewHolder.rePhoto);
        viewHolder.tvSend.setVisibility(View.GONE);
    }

    private void select(ViewHolder viewHolder) {
        Blurry.with(activity)
                .radius(6)
                .sampling(6)
                .color(Color.argb(66, 255, 255, 255))
                .async()
                .onto(viewHolder.rePhoto);
        viewHolder.tvSend.setVisibility(View.VISIBLE);
    }

    @Override
    public void clear() {
        super.clear();
        unSelect();
    }

    public void unSelect() {
        if (positionSelected < 0) return;
        int positionSelectOld = positionSelected;
        positionSelected = -1;
        notifyItemChanged(positionSelectOld);
    }

    public void setlistener(OnPhotoClickListener lIstener) {
        this.listener = lIstener;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView tvSend;
        RelativeLayout rePhoto;

        ViewHolder(View view) {
            super(view);
            img = (SimpleDraweeView) view.findViewById(R.id.img);
            tvSend = (TextView) view.findViewById(R.id.tv_send);
            rePhoto = (RelativeLayout) view.findViewById(R.id.re_photo);
        }
    }

    public interface OnPhotoClickListener {
        void onPhotoClicked(String url);
    }
}
