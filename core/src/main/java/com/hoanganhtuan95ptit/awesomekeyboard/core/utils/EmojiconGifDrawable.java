package com.hoanganhtuan95ptit.awesomekeyboard.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;

/**
 * Created by HOANG ANH TUAN on 7/9/2017.
 */

class EmojiconGifDrawable extends AnimationDrawable {

    private String url;
    private Context context;
    private int textSize;
    private int mCurrentIndex = 0;

    EmojiconGifDrawable(Context context, String url, int textSize) {
        this.context = context;
        this.url = url;
        this.textSize = textSize;
        init();
    }

    private void init() {
        //get cache EmojiconDecoder
        EmojiconDecoder emojiconDecoder = EmojiconCache.getEmojiconDecoder(url);
        if(emojiconDecoder==null){
            emojiconDecoder=new EmojiconDecoder(url,context);
            EmojiconCache.saveEmojiconDecoder(url,emojiconDecoder);
        }
        // add frame
        for (int i = 0; i < emojiconDecoder.frameNum(); ++i) {
            Bitmap bitmap = emojiconDecoder.frame(i);
            BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            int height = textSize;
            int width = (height * drawable.getIntrinsicWidth()) / drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, width, height);
            addFrame(drawable, emojiconDecoder.delay(i));
            if (i == 0) {
                setBounds(0, 0, width, height);
            }
        }
        animation();
    }

    private void animation() {
        final Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            public void run() {
                mCurrentIndex = (mCurrentIndex + 1) % getNumberOfFrames();
                mHandler.postDelayed(this, getDuration(mCurrentIndex));
            }
        });
    }

    int getDuration() {
        return getDuration(mCurrentIndex);
    }

    public Drawable getDrawable() {
        return getFrame(mCurrentIndex);
    }


}
