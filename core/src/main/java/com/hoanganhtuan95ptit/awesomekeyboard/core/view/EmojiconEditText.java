package com.hoanganhtuan95ptit.awesomekeyboard.core.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import com.hoanganhtuan95ptit.awesomekeyboard.core.utils.EmojiconHandler;

/**
 * Created by HOANG ANH TUAN on 7/11/2017.
 */

public class EmojiconEditText extends AppCompatEditText {

    private int textSizeDefault = 50;
    private OnKeyImeChangeListener onKeyIme;
    private EmojiconHandler emojiconHandler;

    public EmojiconEditText(Context context) {
        super(context);
        init();
    }

    public EmojiconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        emojiconHandler = new EmojiconHandler();
        if (getTextSize() > textSizeDefault) textSizeDefault = (int) getTextSize();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return onKeyIme != null && onKeyIme.onKeyImeChanged(keyCode, event);
    }

    public void setOnKeyIme(OnKeyImeChangeListener onKeyIme) {
        this.onKeyIme = onKeyIme;
    }

    public interface OnKeyImeChangeListener {
        boolean onKeyImeChanged(int keyCode, KeyEvent event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter > lengthBefore) {
            emojiconHandler.convertFromTextToEmoticon(getContext(), getEditableText(), textSizeDefault, getSelectionStart() - 10, getSelectionStart());
        }
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new EmojiconInputConnection(super.onCreateInputConnection(outAttrs), true);
    }

    private class EmojiconInputConnection extends InputConnectionWrapper {

        EmojiconInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if (beforeLength == 1 && afterLength == 0) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }
            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }

}
