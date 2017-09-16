package com.hoanganhtuan95ptit.awesomekeyboard.core;

import android.app.Activity;
import android.view.View;

import com.hoanganhtuan95ptit.awesomekeyboard.core.callback.OnKeyDownListener;
import com.hoanganhtuan95ptit.awesomekeyboard.core.view.EmojiconEditText;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/5/2017.
 */

public class KeyboardBuilder {

    View root;
    Activity context;
    KeyboardLayout keyboardLayout;
    OnKeyDownListener onKeyDownListener;
    ArrayList<EmojiconEditText> editTexts = new ArrayList<>();

    public static KeyboardBuilder with(Activity context) {
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

    public KeyboardBuilder setOnKeyDownListener(OnKeyDownListener onKeyDownListener) {
        this.onKeyDownListener = onKeyDownListener;
        return this;
    }

    public KeyboardBuilder addEditText(EmojiconEditText editText) {
        this.editTexts.add(editText);
        return this;
    }

}
