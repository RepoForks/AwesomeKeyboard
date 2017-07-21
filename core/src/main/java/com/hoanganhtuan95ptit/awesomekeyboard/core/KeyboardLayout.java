package com.hoanganhtuan95ptit.awesomekeyboard.core;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by HOANG ANH TUAN on 7/17/2017.
 */

public abstract class KeyboardLayout extends RelativeLayout {

    private static final String TAG = "KeyboardLayout";

    public KeyboardLayout(Context context) {
        super(context);
    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void showKeyboard(int type);

}
