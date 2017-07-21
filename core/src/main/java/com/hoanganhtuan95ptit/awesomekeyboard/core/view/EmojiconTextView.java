package com.hoanganhtuan95ptit.awesomekeyboard.core.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hoanganhtuan95ptit.awesomekeyboard.core.utils.EmojiconCache;
import com.hoanganhtuan95ptit.awesomekeyboard.core.utils.EmojiconHandler;
import com.hoanganhtuan95ptit.awesomekeyboard.core.utils.EmojiconSpannableStringBuilder;


/**
 * Created by HOANG ANH TUAN on 7/12/2017.
 */

public class EmojiconTextView extends AppCompatTextView {

    private int duration;
    private int textSizeDefault = 50;
    private Handler mHandler;
    private EmojiconSpannableStringBuilder stringBuilder;
    private EmojiconHandler emojiconHandler;

    public EmojiconTextView(Context context) {
        super(context);
        init();
    }

    public EmojiconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHandler = new Handler();
        emojiconHandler = new EmojiconHandler();
        textSizeDefault = Math.max((int) getTextSize(), textSizeDefault);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null && text.length() > 0) {
            stringBuilder = EmojiconCache.getStringBuilder(text.toString());
            if(stringBuilder==null){
                stringBuilder=new EmojiconSpannableStringBuilder(text);
                emojiconHandler.convertFromTextToEmoticon(getContext(), stringBuilder, textSizeDefault);
                EmojiconCache.saveStringBuilder(text.toString(),stringBuilder);
            }
            text = stringBuilder;
            if(duration==0) {
                duration = Math.max(duration, stringBuilder.getDuration());
                if (duration > 0) {
                    mHandler.post(new Runnable() {
                        public void run() {
                            postInvalidate();
                            mHandler.postDelayed(this, duration);
                        }
                    });
                }
            }
        }
        super.setText(text, type);
    }
}
