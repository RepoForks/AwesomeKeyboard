package com.hoanganhtuan95ptit.awesomekeyboard.layout.view;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.R;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter.ViewPagerAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback.OnStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.fragment.StickerFragment;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.StickerCategory;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/15/2017.
 */

public class StickerLayout extends RelativeLayout implements SmartTabLayout.TabProvider,
        OnStickerClickListener{

    private ArrayList<StickerCategory> stickers = new ArrayList<>();

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    private ViewPagerAdapter viewPagerAdapter;

    private OnItemStickerClickListener onItemStickerClickListener;

    public StickerLayout(Context context) {
        super(context);
        initView();
    }

    public StickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StickerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        FragmentActivity activity = (FragmentActivity) getContext();
        viewPagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());

        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_sticker, this, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        smartTabLayout = (SmartTabLayout) view.findViewById(R.id.smartTabLayout);

        viewPager.setAdapter(viewPagerAdapter);

        smartTabLayout.setCustomTabView(this);

        addView(view);
    }

    public void executeSticker() {
        setVisibility(VISIBLE);
    }

    public void updateData(StickerCategory stickerCategory) {
        stickers.add(stickerCategory);
        viewPagerAdapter.add(StickerFragment.initialize(stickerCategory.getStickers(), this));
        smartTabLayout.setViewPager(viewPager);
    }

    public void clearData() {
        stickers.clear();
        viewPagerAdapter.clear();
        smartTabLayout.setViewPager(viewPager);
    }

    private void updatePage(){
        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View tab = inflater.inflate(R.layout.item_title, container, false);
        String url = stickers.get(position).getUrlTitle();
        Uri uri = Uri.parse(url);
        SimpleDraweeView img = (SimpleDraweeView) tab.findViewById(R.id.img);
        img.setImageURI(uri);

        return tab;
    }

    public void setOnItemStickerClickListener(OnItemStickerClickListener onItemStickerClickListener) {
        this.onItemStickerClickListener = onItemStickerClickListener;
    }

    @Override
    public void onStickerClicked(String url) {
        if (onItemStickerClickListener != null)
            onItemStickerClickListener.onItemStickerClicked(url);
    }
}
