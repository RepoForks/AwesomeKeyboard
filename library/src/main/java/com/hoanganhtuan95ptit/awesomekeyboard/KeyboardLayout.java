package com.hoanganhtuan95ptit.awesomekeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnShopClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.ColorLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.view.PhotoLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.view.StickerLayout;

/**
 * Created by HOANG ANH TUAN on 7/5/2017.
 */

public class KeyboardLayout extends RelativeLayout {

    private StickerLayout stickerLayout;
    private PhotoLayout photoLayout;
    private ColorLayout colorLayout;

    public KeyboardLayout(Context context) {
        super(context);
        addKeyboard();
    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        addKeyboard();
    }

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addKeyboard();
    }

    private void addKeyboard() {
        stickerLayout = new StickerLayout(getContext());
        photoLayout = new PhotoLayout(getContext());
        colorLayout = new ColorLayout(getContext());

        addView(colorLayout, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(photoLayout, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(stickerLayout, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        stickerLayout.setVisibility(GONE);
        photoLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
    }


    public void showSticker() {
        stickerLayout.executeSticker();
        photoLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
    }


    public void showPhoto() {
        photoLayout.executePhoto();
        stickerLayout.setVisibility(GONE);
        colorLayout.setVisibility(GONE);
    }


    public void showColor() {
        colorLayout.executeColor();
        photoLayout.setVisibility(GONE);
        stickerLayout.setVisibility(GONE);
    }

    public void updateStiker(){
        stickerLayout.updateSticker();
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        stickerLayout.setOnDeleteClickListener(onDeleteClickListener);
    }

    public void setOnShopClickListener(OnShopClickListener onShopClickListener) {
        stickerLayout.setOnShopClickListener(onShopClickListener);
    }

    public void setOnItemEmoticonClickListener(OnItemEmoticonClickListener onItemEmoticonClickListener) {
        stickerLayout.setOnItemEmoticonClickListener(onItemEmoticonClickListener);
    }

    public void setOnItemStickerClickListener(OnItemStickerClickListener onItemStickerClickListener) {
        stickerLayout.setOnItemStickerClickListener(onItemStickerClickListener);
    }

    public void setOnItemPhotoClickListener(OnItemPhotoClickListener onItemPhotoClickListener) {
        photoLayout.setOnItemPhotoClickListener(onItemPhotoClickListener);
    }

    public void setOnItemColorClickListener(OnItemColorClickListener onItemColorClickListener) {
        colorLayout.setOnItemColorClickListener(onItemColorClickListener);
    }
}
