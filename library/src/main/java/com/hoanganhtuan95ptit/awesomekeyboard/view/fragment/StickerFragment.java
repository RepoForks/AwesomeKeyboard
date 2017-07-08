package com.hoanganhtuan95ptit.awesomekeyboard.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.view.adapter.StickerAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnStickerClickListener;

import java.util.ArrayList;

/**
 * Created by DuyPC on 5/31/2017.
 */

public class StickerFragment extends Fragment {

    private ArrayList<String> images;
    private OnStickerClickListener onStickerClickListener;

    public static StickerFragment initialize(ArrayList<String> urls,OnStickerClickListener onStickerClickListener) {
        StickerFragment stickerFragment = new StickerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("urls", urls);
        bundle.putSerializable("onStickerClickListener", onStickerClickListener);
        stickerFragment.setArguments(bundle);
        return stickerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.images=getArguments().getStringArrayList("urls");
        this.onStickerClickListener= (OnStickerClickListener) getArguments().getSerializable("onStickerClickListener");
        getArguments().clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sticker, parent, false);
        setUpRecyclerView(view);
        return view;
    }

    private void setUpRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_sticker);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        StickerAdapter adapter = new StickerAdapter(getActivity());
        adapter.setOnStickerClickListener(onStickerClickListener);
        recyclerView.setAdapter(adapter);
        for (String url : images) {
            adapter.add(url);
        }
    }

}
