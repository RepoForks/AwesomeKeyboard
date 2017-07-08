package com.hoanganhtuan95ptit.awesomekeyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnKeyDownListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnKeyImeChangeListener;
import com.hoanganhtuan95ptit.awesomekeyboard.utils.CacheUtils;
import com.hoanganhtuan95ptit.awesomekeyboard.view.ListenerEditText;

/**
 * Created by HOANG ANH TUAN on 7/5/2017.
 */

class KeyboardImpl implements Keyboard, OnItemEmoticonClickListener, OnDeleteClickListener {

    private int keyboardHeight = 0;// chiều cao của bàn phím
    private int temHeight = 0;

    private KeyboardLayout keyboardLayout;// layout bàn phím
    private View root;// view cha
    private ListenerEditText editText;
    private Activity context;

    private OnKeyDownListener onKeyDownListener;

    KeyboardImpl(KeyboardBuilder builder) {
        this.keyboardLayout = builder.keyboardLayout;
        this.root = builder.root;
        this.editText = builder.editText;
        this.context = builder.context;
        this.onKeyDownListener = builder.onKeyDownListener;

        intData();

        this.keyboardLayout.setOnItemStickerClickListener(builder.onItemStickerClickListener);
        this.keyboardLayout.setOnItemEmoticonClickListener(this);
        this.keyboardLayout.setOnDeleteClickListener(this);
        this.keyboardLayout.setOnShopClickListener(builder.onShopClickListener);
        this.keyboardLayout.setOnItemPhotoClickListener(builder.onItemPhotoClickListener);
        this.keyboardLayout.setOnItemColorClickListener(builder.onItemColorClickListener);

        setOnShowKeyboardDefault();
    }

    private void intData() {
        temHeight = getTemHeight();
        keyboardHeight = getHeight();

        this.context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        keyboardLayout.setVisibility(View.GONE);
    }

    private void setOnShowKeyboardDefault() {
        showKeyboardNomal();
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        root.getWindowVisibleDisplayFrame(r);

                        int screenHeight = root.getRootView().getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference < 200 && heightDifference > temHeight)
                            temHeight = heightDifference;
                        if (heightDifference > 200 && heightDifference >= keyboardHeight) {
                            if (heightDifference > keyboardHeight) {
                                changeKeyboardHeight(heightDifference);
                                context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                            } else {
                                setHeightLayout();
                                context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                                keyboardLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
        editText.setKeyImeChangeListener(new OnKeyImeChangeListener() {
            @Override
            public boolean onKeyImeChanged(int keyCode, KeyEvent event) {
                if (keyboardLayout.getVisibility() == View.VISIBLE) {
                    hideAllKeyboard();
                    if (onKeyDownListener != null) onKeyDownListener.onKeyDownCLicked();
                    return true;
                }
                return false;
            }
        });
    }

    private void changeKeyboardHeight(int height) {
        keyboardHeight = height;
        cache();
        setHeightLayout();
    }

    private void setHeightLayout() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyboardHeight - temHeight);
        keyboardLayout.setLayoutParams(params);
    }

    private void showKeyboardNomal() {
        if (editText == null) return;
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideKeyboardNomal() {
        if (editText == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private int getHeight() {
        return CacheUtils.getData(context, R.string.app_name, "keyboardHeight", 0);
    }

    private int getTemHeight() {
        return CacheUtils.getData(context, R.string.app_name, "temHeight", 0);
    }

    private void cache() {
        CacheUtils.getData(context, R.string.app_name, "keyboardHeight", keyboardHeight);
        CacheUtils.getData(context, R.string.app_name, "temHeight", temHeight);
    }

    @Override
    public void hideAllKeyboard() {
        hideKeyboardNomal();
        keyboardLayout.setVisibility(View.GONE);
    }

    @Override
    public void showKeyboard(KeyboardType type) {
        if (type == KeyboardType.NOMAL) {
            showNomal();
        } else {
            showEmoticons(type);
        }
    }

    @Override
    public void updateSticker() {
        keyboardLayout.updateStiker();
    }

    @Override
    public void onItemEmoticonClicked(ImageSpan emoji) {
        int start = editText.getSelectionStart();
        int end = editText.getSelectionEnd();
        editText.getEditableText().replace(start, end, emoji.getSource());
        editText.getEditableText().setSpan(emoji, start, start + emoji.getSource().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    @Override
    public void onDeleteClicked() {
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
        editText.dispatchKeyEvent(event);
    }

    private void showNomal() {
        showKeyboardNomal();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                keyboardLayout.setVisibility(View.VISIBLE);
            }
        }, 400);
    }

    private void showEmoticons(KeyboardType type) {
        hideKeyboardNomal();
        keyboardLayout.setVisibility(View.VISIBLE);
        switch (type) {
            case COLOR:
                keyboardLayout.showColor();
                break;
            case STICKER:
                keyboardLayout.showSticker();
                break;
            case PHOTO:
                keyboardLayout.showPhoto();
                break;
        }
    }

}
