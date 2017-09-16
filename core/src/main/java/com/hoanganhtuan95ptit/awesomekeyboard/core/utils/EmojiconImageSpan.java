package com.hoanganhtuan95ptit.awesomekeyboard.core.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import java.io.InputStream;
import java.lang.ref.WeakReference;

class EmojiconImageSpan extends DynamicDrawableSpan {

    private Drawable drawable;
    private int duration;

    EmojiconImageSpan(Context context, String url, EmojiconType type, int textSize) {
        super();
        initDrawable(context, url, type, textSize);
    }

    private void initDrawable(Context context, String url, EmojiconType type, int textSize) {
        type = getEmoticonType(url, type);
        drawable = EmojiconCache.getDrawable(url+"type: "+type+"textSize "+textSize, type);
        if (drawable == null) {
            if (type == EmojiconType.ANIMATION) {
                drawable = new EmojiconGifDrawable(context, url, textSize);
            } else {
                Drawable d = new BitmapDrawable(context.getResources(), getImage(context, url));
                int width = (textSize * d.getIntrinsicWidth()) / d.getIntrinsicHeight();
                d.setBounds(0, 0, width, textSize);
                drawable = d;
            }
            EmojiconCache.saveDrawable(url+"type: "+type+"textSize "+textSize,drawable);
        }
        if(drawable instanceof EmojiconGifDrawable){
            duration = ((EmojiconGifDrawable) drawable).getDuration();
        }
    }

    private EmojiconType getEmoticonType(String url, EmojiconType type) {
        if (type == EmojiconType.NORMAL) return EmojiconType.NORMAL;
        if (url.subSequence(url.length() - 5, url.length()).toString().contains("gif"))
            return EmojiconType.ANIMATION;
        return EmojiconType.NORMAL;
    }

    int getDuration() {
        return duration;
    }

    @Override
    public Drawable getDrawable() {
        if (drawable instanceof EmojiconGifDrawable) {
            return ((EmojiconGifDrawable) drawable).getDrawable();
        }
        return drawable;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {

        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();

        if (fm != null) {

            int initialDescent = 0;
            int extraSpace = 0;

            if (rect.bottom - (fm.descent - fm.ascent) >= 0) {
                initialDescent = fm.descent;
                extraSpace = rect.bottom - (fm.descent - fm.ascent);
            }

            fm.descent = extraSpace / 2 + initialDescent;
            fm.bottom = fm.descent;

            fm.ascent = -rect.bottom + fm.descent;
            fm.top = fm.ascent;
        }

        return rect.right;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();

        int transY = bottom - b.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= paint.getFontMetricsInt().descent;
        }

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();

    }

    private WeakReference<Drawable> mDrawableRef;

    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null)
            d = wr.get();

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<>(d);
        }

        return d;
    }

    private static Bitmap getImage(Context context, String url) {
        AssetManager mngr = context.getAssets();
        InputStream in = null;
        try {
            in = mngr.open(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(in, null, null);
    }


}
