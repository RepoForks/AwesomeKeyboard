package com.hoanganhtuan95ptit.awesomekeyboard.core.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

/**
 * Created by HOANG ANH TUAN on 7/20/2017.
 */

public class EmojiconCache {

    private static final int SIZE_MAX=1024;

    private static EmojiconCache instance;

    private static EmojiconCache getInstance() {
        if (instance == null) instance = new EmojiconCache();
        return instance;
    }

    private LruCache<String, EmojiconDecoder> emojiconDecoderLruCache;
    private LruCache<String, Drawable> drawableLruCache;
    private LruCache<String, EmojiconSpannableStringBuilder> stringBuilderLruCache;
    private LruCache<String, Bitmap> bitmapLruCache;

    private EmojiconCache() {
        emojiconDecoderLruCache=new LruCache<>(SIZE_MAX);
        drawableLruCache=new LruCache<>(SIZE_MAX);
        stringBuilderLruCache=new LruCache<>(SIZE_MAX);
        bitmapLruCache=new LruCache<>(SIZE_MAX);
    }

    public static EmojiconSpannableStringBuilder getStringBuilder(String value) {
        return getInstance().getStringBuilderLruCache().get(value);
    }

    public static void saveStringBuilder(String key, EmojiconSpannableStringBuilder stringBuilder) {
        getInstance().getStringBuilderLruCache().put(key, stringBuilder);
    }

    static EmojiconDecoder getEmojiconDecoder(String key) {
        return getInstance().getEmojiconDecoderLruCache().get(key);
    }

    static void saveEmojiconDecoder(String key, EmojiconDecoder emojiconDecoder) {
        getInstance().getEmojiconDecoderLruCache().put(key, emojiconDecoder);
    }

    static Drawable getDrawable(String url, EmojiconType type) {
        return getInstance().getDrawableLruCache().get(url + "type: " + type);
    }

    static void saveDrawable(String key, Drawable drawable) {
        getInstance().getDrawableLruCache().put(key, drawable);
    }

    static Bitmap getBitmap(String key) {
        return getInstance().getBitmapLruCache().get(key);
    }

    static void saveBitmap(String key, Bitmap bitmap) {
        bitmap = Bitmap.createScaledBitmap(bitmap, 180, 180, false);
        getInstance().getBitmapLruCache().put(key, bitmap);
    }

    private LruCache<String, EmojiconDecoder> getEmojiconDecoderLruCache() {
        return emojiconDecoderLruCache;
    }

    private LruCache<String, Drawable> getDrawableLruCache() {
        return drawableLruCache;
    }

    private LruCache<String, EmojiconSpannableStringBuilder> getStringBuilderLruCache() {
        return stringBuilderLruCache;
    }

    private LruCache<String, Bitmap> getBitmapLruCache() {
        return bitmapLruCache;
    }
}
