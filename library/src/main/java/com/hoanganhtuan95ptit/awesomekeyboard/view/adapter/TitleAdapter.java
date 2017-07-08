package com.hoanganhtuan95ptit.awesomekeyboard.view.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnTitleClickListener;

/**
 * Created by HOANG ANH TUAN on 6/28/2017.
 */

public class TitleAdapter extends BaseAdapter<String> {

    private OnTitleClickListener onTitleClickListener;
    private int select;

    public TitleAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int padding = (int) activity.getResources().getDimension(R.dimen.itemTitlePadding);
        View view = inflater.inflate(R.layout.item_title, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imagePicture.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        viewHolder.imagePicture.setPadding(padding, padding, padding, padding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        String sticker = getDatas().get(position);

        if (this.select == position) viewHolder.imagePicture.setBackgroundColor(0xffe1e1e1);
        else viewHolder.imagePicture.setBackgroundColor(0xffffffff);

        Uri uri = Uri.parse(sticker);
        viewHolder.imagePicture.setImageURI(uri);
        viewHolder.imagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleClickListener != null)
                    onTitleClickListener.onTitleClicked(holder.getAdapterPosition());
            }
        });
    }

    public void setSelect(int select) {
        int a = this.select;
        this.select = select;
        notifyItemChanged(a);
        notifyItemChanged(this.select);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imagePicture;

        ViewHolder(View view) {
            super(view);
            imagePicture = (SimpleDraweeView) view.findViewById(R.id.image_picture);
        }
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }
}
