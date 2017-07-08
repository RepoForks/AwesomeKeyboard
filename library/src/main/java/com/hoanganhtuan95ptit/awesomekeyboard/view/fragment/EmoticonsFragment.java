package com.hoanganhtuan95ptit.awesomekeyboard.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.adapter.EmoticonsAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnDeleteListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnEmoticonClickListener;

import java.io.Serializable;


/**
 * Created by HOANG ANH TUAN on 6/29/2017.
 */

public class EmoticonsFragment extends Fragment {

    private OnEmoticonClickListener onEmoticonClickListener;
    private OnDeleteListener onDeleteListener;

    public static EmoticonsFragment initialize(OnEmoticonClickListener onEmoticonClickListener,
                                               OnDeleteListener onDeleteListener) {
        EmoticonsFragment emoticonsFragment = new EmoticonsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("onEmoticonClickListener", onEmoticonClickListener);
        bundle.putSerializable("onDeleteListener", onDeleteListener);
        emoticonsFragment.setArguments(bundle);
        return emoticonsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onEmoticonClickListener = (OnEmoticonClickListener) getArguments().getSerializable("onEmoticonClickListener");
        this.onDeleteListener = (OnDeleteListener) getArguments().getSerializable("onDeleteListener");
        getArguments().clear();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emoticons, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initRecyclerView(view);
        initButtonDelete(view);
    }

    private void initButtonDelete(View view) {
        ImageView imgDelete = (ImageView) view.findViewById(R.id.img_delete);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteListener != null) onDeleteListener.onDeleteClicked();
            }
        });
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL));
        EmoticonsAdapter emoticonsAdapter = new EmoticonsAdapter((Activity) getContext());
        emoticonsAdapter.setOnEmoticonClickListener(onEmoticonClickListener);
        recyclerView.setAdapter(emoticonsAdapter);
        for (int i = 1; i <= 54; i++) {
            emoticonsAdapter.add(i + ".png");
        }
    }

}
