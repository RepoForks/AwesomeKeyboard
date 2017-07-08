package com.hoanganhtuan95ptit.awesomekeyboard.view.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnColorClickListener;

/**
 * Created by DuyPC on 5/27/2017.
 */

public class ColorAdapter extends BaseAdapter<String> {
    private OnColorClickListener onColorClickListener;

    public ColorAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int margin = (int) activity.getResources().getDimension(R.dimen.itemStickerMargin);

        View v = inflater.inflate(R.layout.item_sticker, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.imagePicture.setImageResource(R.drawable.xml_circle);
        viewHolder.imagePicture.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        viewHolder.imagePicture.setPadding(margin, margin, margin, margin);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.imagePicture.setColorFilter(Color.parseColor(getDatas().get(position)));
        viewHolder.imagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onColorClickListener != null)
                    onColorClickListener.onColorClicked(getDatas().get(holder.getAdapterPosition()));
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

    public void setOnColorClickListener(OnColorClickListener onColorClickListener) {
        this.onColorClickListener = onColorClickListener;
    }

}
