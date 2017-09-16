package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.R;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.SquaredFrameLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback.OnStickerClickListener;

/**
 * Created by HOANG ANH TUAN on 7/15/2017.
 */

public class StickerAdapter extends BaseAdapter<String> {

    private OnStickerClickListener onStickerClickListener;

    public StickerAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int padding = (int) activity.getResources().getDimension(R.dimen.itemStickerPadding);
        View v = inflater.inflate(R.layout.item_sticker, parent, false);
        ViewHolder itemViewHolder = new ViewHolder(v);
        itemViewHolder.img.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        itemViewHolder.itemSticker.setPadding(padding, padding, padding, padding);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder itemViewHolder = (ViewHolder) holder;
        final String url = getDatas().get(position);
        Uri uri = Uri.parse(url);
        itemViewHolder.img.setImageURI(uri);
        itemViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onStickerClickListener != null) onStickerClickListener.onStickerClicked(url);
            }
        });
    }

    public void setOnStickerClickListener(OnStickerClickListener onStickerClickListener) {
        this.onStickerClickListener = onStickerClickListener;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView img;
        SquaredFrameLayout itemSticker;

        ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
            itemSticker = (SquaredFrameLayout) itemView.findViewById(R.id.item_sticker);
        }
    }

}
