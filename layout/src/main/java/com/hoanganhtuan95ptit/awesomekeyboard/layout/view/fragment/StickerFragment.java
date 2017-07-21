package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoanganhtuan95ptit.awesomekeyboard.layout.R;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter.StickerAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback.OnStickerClickListener;

import java.util.ArrayList;


/**
 * Created by HOANG ANH TUAN on 7/15/2017.
 */

public class StickerFragment extends Fragment implements OnStickerClickListener {

    private static final String LISTENER = "ON_STICKER_CLICK_LISTENER";
    private static final String STICKER = "STICKERS";

    private Activity activity;
    private StickerAdapter stickerAdapter;
    private ArrayList<String> stickers;
    private OnStickerClickListener onStickerClickListener;

    public static Fragment initialize(ArrayList<String> stickers, OnStickerClickListener onStickerClickListener) {
        StickerFragment stickerFragment = new StickerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(LISTENER, onStickerClickListener);
        bundle.putSerializable(STICKER, stickers);
        stickerFragment.setArguments(bundle);
        return stickerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        this.onStickerClickListener = (OnStickerClickListener) getArguments().getSerializable(LISTENER);
        this.stickers = (ArrayList<String>) getArguments().getSerializable(STICKER);
        getArguments().remove(LISTENER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        stickerAdapter = new StickerAdapter(activity);

        stickerAdapter.setOnStickerClickListener(this);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(stickerAdapter);

        int padding = (int) activity.getResources().getDimension(R.dimen.itemStickerPadding);
        recyclerView.setPadding(padding, 0, padding, 0);
        updateData();

        return recyclerView;
    }

    private void updateData() {
        if (stickers != null) {
            for (int i = 0; i < stickers.size(); i++) {
                stickerAdapter.add(stickers.get(i));
            }
        }
    }

    @Override
    public void onStickerClicked(String url) {
        if (onStickerClickListener != null)
            onStickerClickListener.onStickerClicked(url);
    }
}
