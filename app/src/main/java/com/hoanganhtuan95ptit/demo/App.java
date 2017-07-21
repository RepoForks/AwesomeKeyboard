package com.hoanganhtuan95ptit.demo;

import android.app.Application;

import com.hoanganhtuan95ptit.awesomekeyboard.layout.AwesomeKeyboardSdk;
import com.hoanganhtuan95ptit.demo.utils.Utils;

/**
 * Created by HOANG ANH TUAN on 7/17/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AwesomeKeyboardSdk.initialize(this, Utils.emoticons);
    }
}
