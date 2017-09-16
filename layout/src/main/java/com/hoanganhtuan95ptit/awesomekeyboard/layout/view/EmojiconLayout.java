package com.hoanganhtuan95ptit.awesomekeyboard.layout.view;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.R;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter.ViewPagerAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback.OnEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.fragment.EmoticonsFragment;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.EmojiconCategory;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/18/2017.
 */

public class EmojiconLayout extends RelativeLayout implements SmartTabLayout.TabProvider,
        View.OnClickListener,
        OnEmoticonClickListener{

    private static final String TAG = "EmojiconLayout";

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    private ArrayList<EmojiconCategory> emoticonCategories=new ArrayList<>();

    private ViewPagerAdapter viewPagerAdapter;

    private OnItemEmoticonClickListener onItemEmoticonClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public EmojiconLayout(Context context) {
        super(context);
        initView();
    }

    public EmojiconLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public EmojiconLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        Log.e(TAG,"initView");
        FragmentActivity activity = (FragmentActivity) getContext();
        viewPagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());

        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_emojicon, this, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerEmojicon);
        smartTabLayout = (SmartTabLayout) view.findViewById(R.id.smartTabLayoutEmojicon);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.img_delete);

        viewPager.setAdapter(viewPagerAdapter);

        smartTabLayout.setCustomTabView(this);
        imgDelete.setOnClickListener(this);

        addView(view);
    }

    public void executeEmojicon() {
        setVisibility(VISIBLE);
    }

    public void updateData(EmojiconCategory emojiconCategory) {
        emoticonCategories.add(emojiconCategory);
        viewPagerAdapter.add(EmoticonsFragment.initialize(emojiconCategory.getEmojicons(), this));
        updatePage();
    }

    public void clearData() {
        emoticonCategories.clear();
        viewPagerAdapter.clear();
        updatePage();
    }

    private void updatePage(){
        smartTabLayout.setViewPager(viewPager);
    }

    public void setOnItemEmoticonClickListener(OnItemEmoticonClickListener onItemEmoticonClickListener) {
        this.onItemEmoticonClickListener = onItemEmoticonClickListener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View tab = inflater.inflate(R.layout.item_title, container, false);
        SimpleDraweeView img = (SimpleDraweeView) tab.findViewById(R.id.img);

        String url = "asset:///" + emoticonCategories.get(position).getUrlTitle();
        Uri uri = Uri.parse(url);
        img.setImageURI(uri);

        return tab;
    }

    @Override
    public void onEmoticonsClicked(String key, int position) {
        if (onItemEmoticonClickListener != null)
            onItemEmoticonClickListener.onItemEmoticonClicked(key);
    }

    @Override
    public void onClick(View v) {
        if (onDeleteClickListener != null) onDeleteClickListener.onDeleteClicked();
    }

}
