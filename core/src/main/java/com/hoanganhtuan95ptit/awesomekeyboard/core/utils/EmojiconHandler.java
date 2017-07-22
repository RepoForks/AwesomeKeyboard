package com.hoanganhtuan95ptit.awesomekeyboard.core.utils;

import android.content.Context;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.DynamicDrawableSpan;

import com.hoanganhtuan95ptit.awesomekeyboard.core.constants.EmojiconConstant;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HOANG ANH TUAN on 7/20/2017.
 */

public class EmojiconHandler {

    private static final int textSizeMax = 100;

    public void convertFromTextToEmoticon(Context context, EmojiconSpannableStringBuilder builder, int textSize) {
        if(EmojiconConstant.emoticons==null)return;
        textSize = getTextSize(builder, textSize);
        convertToEmoticon(context, builder, EmojiconType.ANIMATION, textSize, 0, builder.length());
    }

    public void convertFromTextToEmoticon(Context context, Editable builder, int textSize, int start, int end) {
        if(EmojiconConstant.emoticons==null)return;
        convertToEmoticon(context, builder, EmojiconType.NORMAL, textSize, start, end);
    }

    private void convertToEmoticon(Context context, Editable editable, EmojiconType type, int textSize, int start, int end) {
        start = Math.max(start, 0);
        end = Math.min(end, editable.length());
        if (start >= end) return;

        for (Map.Entry<String, String> entry : EmojiconConstant.emoticons.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            Matcher matcher = Pattern.compile(Pattern.quote(key)).matcher(editable);
            matcher.region(start, end);

            while (matcher.find()) {
                DynamicDrawableSpan newImageSpan = new EmojiconImageSpan(context, value, type, textSize);
                if (type == EmojiconType.ANIMATION) {
                    ((EmojiconSpannableStringBuilder) editable).setDuration(((EmojiconImageSpan) newImageSpan).getDuration());
                }
                editable.setSpan(newImageSpan, matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }


    private int getTextSize(Editable editable, int textSize) {
        int length = 0;
        int count = 0;
        for (Map.Entry<String, String> entry : EmojiconConstant.emoticons.entrySet()) {

            String key = entry.getKey();

            Matcher matcher = Pattern.compile(Pattern.quote(key)).matcher(editable);

            while (matcher.find()) {
                length = length + key.length();
                count++;
                if (count > 3) return textSize;
            }
        }
        if (length < editable.length()) return textSize;
        return textSizeMax;
    }

}
