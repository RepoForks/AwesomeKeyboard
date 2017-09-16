package com.hoanganhtuan95ptit.awesomekeyboard.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.hoanganhtuan95ptit.awesomekeyboard.core.callback.OnKeyDownListener;
import com.hoanganhtuan95ptit.awesomekeyboard.core.view.EmojiconEditText;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/5/2017.
 */

class KeyboardImpl implements Keyboard, EmojiconEditText.OnKeyImeChangeListener {

    private int keyboardHeight = 0;// chiều cao của bàn phím
    private int temHeight = 0;

    private View root;// view cha
    private Activity context;
    private KeyboardLayout keyboardLayout;// layout bàn phím
    private ArrayList<EmojiconEditText> editTexts;
    private OnKeyDownListener onKeyDownListener;

    KeyboardImpl(KeyboardBuilder builder) {
        this.keyboardLayout = builder.keyboardLayout;
        this.root = builder.root;
        this.context = builder.context;
        this.editTexts = builder.editTexts;
        this.onKeyDownListener = builder.onKeyDownListener;

        intData();

        setOnShowKeyboardDefault();
    }

    private void intData() {

        context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        keyboardLayout.setVisibility(View.GONE);
    }

    private void setOnShowKeyboardDefault() {
        showKeyboardDefault();
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
                            } else {
                                setHeightLayout();
                                context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                                keyboardLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).setOnKeyIme(this);
        }
    }

    private void changeKeyboardHeight(int height) {
        keyboardHeight = height;
        setHeightLayout();
    }

    private void setHeightLayout() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyboardHeight - temHeight);
        keyboardLayout.setLayoutParams(params);
    }

    private void showKeyboardDefault() {
        View viewFocus = root.findFocus();
        if (viewFocus == null) {
            if (editTexts.isEmpty()) return;
            else {
                editTexts.get(0).requestFocus();
                viewFocus = editTexts.get(0);
            }
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(viewFocus, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideKeyboardDefault() {
        View viewFocus = root.findFocus();
        if (viewFocus == null) {
            if (editTexts.isEmpty()) return;
            else {
                editTexts.get(0).requestFocus();
                viewFocus = editTexts.get(0);
            }
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(viewFocus.getWindowToken(), 0);
    }

    @Override
    public void hideAllKeyboard() {
        hideKeyboardDefault();
        keyboardLayout.setVisibility(View.GONE);
    }

    @Override
    public void showKeyboard(int type) {
        if (type == 1) {
            showKeyboardDefault();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    keyboardLayout.setVisibility(View.VISIBLE);
                }
            }, 400);
        } else {
            hideKeyboardDefault();
            keyboardLayout.setVisibility(View.VISIBLE);
            keyboardLayout.showKeyboard(type);
        }
    }

    @Override
    public boolean onKeyImeChanged(int keyCode, KeyEvent event) {
        if (keyboardLayout.getVisibility() == View.VISIBLE) {
            hideAllKeyboard();
            if (onKeyDownListener != null) onKeyDownListener.onKeyDownCLicked();
            return true;
        }
        return false;
    }
}
