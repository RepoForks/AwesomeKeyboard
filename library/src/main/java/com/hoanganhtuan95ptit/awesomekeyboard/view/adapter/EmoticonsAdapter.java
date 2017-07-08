package com.hoanganhtuan95ptit.awesomekeyboard.view.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnEmoticonClickListener;

/**
 * Created by HOANG ANH TUAN on 6/29/2017.
 */

public class EmoticonsAdapter extends BaseAdapter<String> {

    private OnEmoticonClickListener onEmoticonClickListener;

    public EmoticonsAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int padding = (int) activity.getResources().getDimension(R.dimen.itemEmojiconsPadding);
        View v = inflater.inflate(R.layout.item_emoticon, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.imagePicture.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        viewHolder.imagePicture.setPadding(padding, padding, padding, padding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final int name = position + 1;
        Uri uri = Uri.parse("asset:///emoticons/" + name + ".png");
        viewHolder.imagePicture.setImageURI(uri);
        viewHolder.imagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEmoticonClickListener != null)
                    onEmoticonClickListener.onEmoticonsClicked(name + ".png");
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

    public void setOnEmoticonClickListener(OnEmoticonClickListener onEmoticonClickListener) {
        this.onEmoticonClickListener = onEmoticonClickListener;
    }

}
