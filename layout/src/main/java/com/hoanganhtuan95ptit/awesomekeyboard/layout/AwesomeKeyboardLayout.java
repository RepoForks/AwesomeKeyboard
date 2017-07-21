package com.hoanganhtuan95ptit.awesomekeyboard.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.core.KeyboardLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.ColorLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.EmojiconLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.PhotoLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.StickerLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.EmojiconCategory;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.StickerCategory;

/**
 * Created by HOANG ANH TUAN on 7/17/2017.
 */

public class AwesomeKeyboardLayout extends KeyboardLayout {

    private StickerLayout stickerLayout;
    private PhotoLayout photoLayout;
    private ColorLayout colorLayout;
    private EmojiconLayout emojiconLayout;

    public AwesomeKeyboardLayout(Context context) {
        super(context);
        initView();
    }

    public AwesomeKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AwesomeKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        stickerLayout = new StickerLayout(getContext());
        photoLayout = new PhotoLayout(getContext());
        colorLayout = new ColorLayout(getContext());
        emojiconLayout = new EmojiconLayout(getContext());

        addView(colorLayout, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        addView(photoLayout, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        addView(emojiconLayout, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        addView(stickerLayout, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        stickerLayout.setVisibility(GONE);
        photoLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
        emojiconLayout.setVisibility(GONE);
    }

    @Override
    public void showKeyboard(int type) {
        switch (type) {
            case 2:
                showSticker();
                break;
            case 3:
                showPhoto();
                break;
            case 4:
                showColor();
                break;
            case 5:
                showEmojicon();
                break;
        }
    }

    private void showEmojicon() {
        emojiconLayout.executeEmojicon();
        photoLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
        stickerLayout.setVisibility(GONE);
    }

    public void showSticker() {
        stickerLayout.executeSticker();
        photoLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
        emojiconLayout.setVisibility(GONE);
    }

    public void showPhoto() {
        photoLayout.executePhoto();
        stickerLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
        emojiconLayout.setVisibility(GONE);
    }

    public void showColor() {
        colorLayout.executeColor();
        photoLayout.setVisibility(GONE);
        stickerLayout.setVisibility(GONE);
        emojiconLayout.setVisibility(GONE);
    }


    public void setOnItemStickerClickListener(OnItemStickerClickListener onItemStickerClickListener) {
        stickerLayout.setOnItemStickerClickListener(onItemStickerClickListener);
    }

    public void setOnItemEmoticonClickListener(OnItemEmoticonClickListener onItemEmoticonClickListener) {
        emojiconLayout.setOnItemEmoticonClickListener(onItemEmoticonClickListener);
    }

    public void setOnItemColorClickListener(OnItemColorClickListener onItemColorClickListener) {
        colorLayout.setOnItemColorClickListener(onItemColorClickListener);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        emojiconLayout.setOnDeleteClickListener(onDeleteClickListener);
    }

    public void setOnItemPhotoClickListener(OnItemPhotoClickListener onItemPhotoClickListener) {
        photoLayout.setOnItemPhotoClickListener(onItemPhotoClickListener);
    }

    public void updateDataSticker(StickerCategory stickerCategory) {
        stickerLayout.updateData(stickerCategory);
    }

    public void clearDataSticker() {
        stickerLayout.clearData();
    }


    public void updateDataEmoticon(EmojiconCategory emojiconCategory) {
        emojiconLayout.updateData(emojiconCategory);
    }

    public void clearDataEmoticon() {
        emojiconLayout.clearData();
    }

    public void updateDataColor(String color) {
        colorLayout.updateData(color);
    }

    public void clearDataColor() {
        colorLayout.clearData();
    }

}
