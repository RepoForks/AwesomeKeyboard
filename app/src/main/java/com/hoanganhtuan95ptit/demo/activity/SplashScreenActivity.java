package com.hoanganhtuan95ptit.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.hoanganhtuan95ptit.demo.R;

/**
 * Created by HOANG ANH TUAN on 7/8/2017.
 */

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(SplashScreenActivity.this);
                finish();
            }
        }, 2000);
    }

}
