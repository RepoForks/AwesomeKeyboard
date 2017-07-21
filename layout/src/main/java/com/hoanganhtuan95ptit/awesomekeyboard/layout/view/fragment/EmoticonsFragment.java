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
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter.EmoticonsAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback.OnEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.Emojicon;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 6/29/2017.
 */

public class EmoticonsFragment extends Fragment implements OnEmoticonClickListener {

    private static final String LISTENER = "ON_EMOTICON_CLICK_LISTENER";
    private static final String EMOJICONS = "EMOJICONS";

    private OnEmoticonClickListener onEmoticonClickListener;

    private Activity activity;
    private ArrayList<Emojicon> urls;
    private EmoticonsAdapter emoticonsAdapter;

    public static EmoticonsFragment initialize(ArrayList<Emojicon> urls, OnEmoticonClickListener onEmoticonClickListener) {
        EmoticonsFragment emoticonsFragment = new EmoticonsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EMOJICONS, urls);
        bundle.putSerializable(LISTENER, onEmoticonClickListener);
        emoticonsFragment.setArguments(bundle);
        return emoticonsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        this.urls = (ArrayList<Emojicon>) getArguments().getSerializable(EMOJICONS);
        this.onEmoticonClickListener = (OnEmoticonClickListener) getArguments().getSerializable(LISTENER);
        getArguments().remove(LISTENER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        emoticonsAdapter = new EmoticonsAdapter(activity);

        emoticonsAdapter.setOnEmoticonClickListener(this);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(emoticonsAdapter);

        int padding = (int) activity.getResources().getDimension(R.dimen.itemStickerPadding);
        recyclerView.setPadding(padding, 0, padding, 0);
        updateData();

        return recyclerView;
    }

    private void updateData() {
        if (urls != null) {
            for (int i = 0; i < urls.size(); i++) {
                emoticonsAdapter.add(urls.get(i).getUrl());
            }
        }
    }

    @Override
    public void onEmoticonsClicked(String assetUrl, int position) {
        if (onEmoticonClickListener != null)
            onEmoticonClickListener.onEmoticonsClicked(urls.get(position).getKey(), position);
    }
}
