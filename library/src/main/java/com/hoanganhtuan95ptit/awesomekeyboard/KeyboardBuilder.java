package com.hoanganhtuan95ptit.awesomekeyboard;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnKeyDownListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnShopClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.ListenerEditText;
import com.hoanganhtuan95ptit.awesomekeyboard.view.StickerLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.view.model.Sticker;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/5/2017.
 */

public class KeyboardBuilder {

    KeyboardLayout keyboardLayout;// layout bàn phím
    View root;// view cha
    ListenerEditText editText;
    Activity context;


    OnKeyDownListener onKeyDownListener;
    OnShopClickListener onShopClickListener;
    OnItemStickerClickListener onItemStickerClickListener;
    OnItemPhotoClickListener onItemPhotoClickListener;
    OnItemColorClickListener onItemColorClickListener;

    public static KeyboardBuilder with(Activity context) {
        Fresco.initialize(context);
        return new KeyboardBuilder(context);
    }

    public Keyboard builder() {
        return new KeyboardImpl(this);
    }

    private KeyboardBuilder(Activity context) {
        this.context = context;
    }

    public KeyboardBuilder setKeyboardLayout(KeyboardLayout keyboardLayout) {
        this.keyboardLayout = keyboardLayout;
        return this;
    }

    public KeyboardBuilder setRoot(View root) {
        this.root = root;
        return this;
    }

    public KeyboardBuilder setEditText(ListenerEditText editText) {
        this.editText = editText;
        return this;
    }

    public KeyboardBuilder setOnKeyDownListener(OnKeyDownListener onKeyDownListener) {
        this.onKeyDownListener = onKeyDownListener;
        return this;
    }

    public KeyboardBuilder setOnShopClickListener(OnShopClickListener onShopClickListener) {
        this.onShopClickListener = onShopClickListener;
        return this;
    }

    public KeyboardBuilder setOnItemStickerClickListener(OnItemStickerClickListener onItemStickerClickListener) {
        this.onItemStickerClickListener = onItemStickerClickListener;
        return this;
    }

    public KeyboardBuilder setOnItemPhotoClickListener(OnItemPhotoClickListener onItemPhotoClickListener) {
        this.onItemPhotoClickListener = onItemPhotoClickListener;
        return this;
    }

    public KeyboardBuilder setOnItemColorClickListener(OnItemColorClickListener onItemColorClickListener) {
        this.onItemColorClickListener = onItemColorClickListener;
        return this;
    }

    public static void addSticker(Context context, Sticker sticker) {
        StickerLayout.addSticker(context, sticker);
    }

    public static ArrayList getSticker(Context context) {
        return StickerLayout.getStickers(context);
    }

    public static void clearSticker(Context context) {
        StickerLayout.clearSticker(context);
    }

    public static SpannableStringBuilder convertFromTextToEmoji(Context context, String text) {
        return StickerLayout.convertFromTextToEmoji(context, text);
    }

}
