package com.hoanganhtuan95ptit.awesomekeyboard.layout;

/**
 * Created by HOANG ANH TUAN on 7/5/2017.
 */

public enum AwesomeKeyboardType {
    NOMAL(1), STICKER(2), PHOTO(3), COLOR(4), EMOJICON(5);

    private int value;

    AwesomeKeyboardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
