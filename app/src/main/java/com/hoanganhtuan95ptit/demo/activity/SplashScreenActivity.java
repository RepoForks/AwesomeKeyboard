package com.hoanganhtuan95ptit.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.hoanganhtuan95ptit.awesomekeyboard.KeyboardBuilder;
import com.hoanganhtuan95ptit.awesomekeyboard.view.model.Sticker;
import com.hoanganhtuan95ptit.demo.R;
import com.hoanganhtuan95ptit.demo.constant.Constant;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HOANG ANH TUAN on 7/8/2017.
 */

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setDataSticker();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(SplashScreenActivity.this);
                finish();
            }
        }, 2000);
    }

    private void setDataSticker() {
        if (KeyboardBuilder.getSticker(this).isEmpty()) {
            KeyboardBuilder.clearSticker(this);

            ArrayList<String> images = new ArrayList<>();
            Collections.addAll(images, Constant.heromals);
            Sticker sticker = new Sticker(Constant.heromals[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.mango);
            sticker = new Sticker(Constant.mango[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.meep);
            sticker = new Sticker(Constant.meep[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.mim);
            sticker = new Sticker(Constant.mim[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.minion);
            sticker = new Sticker(Constant.minion[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.mood);
            sticker = new Sticker(Constant.mood[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.stickerBun);
            sticker = new Sticker(Constant.stickerBun[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);

            images = new ArrayList<>();
            Collections.addAll(images, Constant.stickerFinch);
            sticker = new Sticker(Constant.stickerFinch[0], "", images);
            KeyboardBuilder.addSticker(this, sticker);


        }
    }
}
