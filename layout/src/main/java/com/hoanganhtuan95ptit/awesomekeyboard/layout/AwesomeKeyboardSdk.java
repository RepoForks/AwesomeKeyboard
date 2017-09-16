package com.hoanganhtuan95ptit.awesomekeyboard.layout;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hoanganhtuan95ptit.awesomekeyboard.core.constants.EmojiconConstant;

import java.util.HashMap;

/**
 * Created by HOANG ANH TUAN on 7/17/2017.
 */

public class AwesomeKeyboardSdk {

    private AwesomeKeyboardSdk(Context context, HashMap<String, String> emoticons) {
        EmojiconConstant.emoticons = emoticons;
        Fresco.initialize(context);
    }

    private static AwesomeKeyboardSdk awesomeKeyboard;

    public static void initialize(Context context, HashMap<String, String> emoticons) {
        if (awesomeKeyboard == null) awesomeKeyboard = new AwesomeKeyboardSdk(context, emoticons);
    }
}
