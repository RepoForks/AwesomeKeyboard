package com.hoanganhtuan95ptit.awesomekeyboard.core.utils;

import android.text.SpannableStringBuilder;

/**
 * Created by HOANG ANH TUAN on 7/14/2017.
 */

public class EmojiconSpannableStringBuilder extends SpannableStringBuilder {
    private int duration = 0;

    public EmojiconSpannableStringBuilder(CharSequence text) {
        super(text);
    }

    public int getDuration() {
        return duration;
    }

    void setDuration(int duration) {
        if(duration==0)return;
        if (this.duration == 0) {
            this.duration = duration;
        } else if (this.duration > duration) this.duration = duration;
    }
}
