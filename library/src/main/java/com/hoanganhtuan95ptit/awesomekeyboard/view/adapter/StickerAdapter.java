package com.hoanganhtuan95ptit.awesomekeyboard.view.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnStickerClickListener;

/**
 * Created by DuyPC on 5/23/2017.
 */

public class StickerAdapter extends BaseAdapter<String> {

    private OnStickerClickListener onStickerClickListener;

    public StickerAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int padding = (int) activity.getResources().getDimension(R.dimen.itemStickerPadding);
        View view = inflater.inflate(R.layout.item_sticker, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.imagePicture.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        viewHolder.imagePicture.setPadding(padding, padding, padding, padding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        Uri uri = Uri.parse(getDatas().get(position));
        viewHolder.imagePicture.setImageURI(uri);
        viewHolder.imagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onStickerClickListener != null)
                    onStickerClickListener.onStickerClicked(getDatas().get(viewHolder.getAdapterPosition()));
            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imagePicture;

        ViewHolder(View view) {
            super(view);
            imagePicture = (SimpleDraweeView) view.findViewById(R.id.image_picture);
        }
    }

    public void setOnStickerClickListener(OnStickerClickListener onStickerClickListener) {
        this.onStickerClickListener = onStickerClickListener;
    }

}
