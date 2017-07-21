package com.hoanganhtuan95ptit.demo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hoanganhtuan95ptit.awesomekeyboard.core.view.EmojiconTextView;
import com.hoanganhtuan95ptit.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HOANG ANH TUAN on 7/17/2017.
 */

public class ChatAdapter extends BaseAdapter<String> {

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
        ((ViewHolder) holder).tvInfor.setText(getDatas().get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        EmojiconTextView tvInfor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
