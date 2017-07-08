package com.hoanganhtuan95ptit.demo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoanganhtuan95ptit.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HOANG ANH TUAN on 7/8/2017.
 */

public class ChatAdapter extends BaseAdapter<SpannableStringBuilder> {
    public ChatAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvInfor.setText(getDatas().get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_infor)
        TextView tvInfor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
