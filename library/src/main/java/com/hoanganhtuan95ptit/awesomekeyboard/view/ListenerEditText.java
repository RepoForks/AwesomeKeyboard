package com.hoanganhtuan95ptit.awesomekeyboard.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnKeyImeChangeListener;

/**
 * Created by HOANG ANH TUAN on 7/6/2017.
 */

public class ListenerEditText extends AppCompatEditText {

    private OnKeyImeChangeListener onKeyIme;

    public ListenerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setKeyImeChangeListener(OnKeyImeChangeListener onKeyIme) {
        this.onKeyIme = onKeyIme;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (onKeyIme != null) {
            return onKeyIme.onKeyImeChanged(keyCode, event);
        }
        return false;
    }
}
