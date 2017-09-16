package com.hoanganhtuan95ptit.awesomekeyboard.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HOANG ANH TUAN on 7/20/2017.
 */

class EmojiconDecoder {

    private static final String TAG = "EmojiconDecoder";

    private int width = 0;
    private int height = 0;
    private int[] delays = new int[0];
    private int frameNum;
    private long handle;
    private String url;
    private Context context;

    static {
        System.loadLibrary("native_lib_GifDecoder");
    }

    private native long nativeInit();

    private native void nativeClose(long var1);

    private native boolean nativeLoad(long var1, String var3);

    private native int nativeGetFrameCount(long var1);

    private native Bitmap nativeGetFrame(long var1, int var3);

    private native int nativeGetDelay(long var1, int var3);

    private native int nativeGetWidth(long var1);

    private native int nativeGetHeight(long var1);

    EmojiconDecoder(String url, Context context) {
        this.url = url;
        this.context = context;
        this.handle = nativeInit();
        this.url = copy();
        load();
    }

    int width() {
        return this.width;
    }

    int height() {
        return this.height;
    }

    int frameNum() {
        return this.frameNum;
    }

    Bitmap frame(int idx) {
        int i = idx % this.frameNum;
        return 0 == this.frameNum ? null : EmojiconCache.getBitmap(url + i);
    }

    int delay(int idx) {
        return 0 == this.frameNum ? 0 : this.delays[idx % this.frameNum];
    }

    private boolean load() {
        if (!this.nativeLoad(handle, url)) {
            this.nativeClose(handle);
            return false;
        } else {
            this.width = this.nativeGetWidth(handle);
            this.height = this.nativeGetHeight(handle);
            this.frameNum = this.nativeGetFrameCount(handle);
            this.delays = new int[this.frameNum];
            for (int i = 0; i < this.frameNum; ++i) {
                EmojiconCache.saveBitmap(url + i, this.nativeGetFrame(handle, i));
                this.delays[i] = this.nativeGetDelay(handle, i);
            }
            this.nativeClose(handle);
            return true;
        }
    }

    private String copy() {
        try {
            InputStream is = context.getAssets().open(url);
            String destFile = context.getFilesDir().getAbsolutePath() + File.separator + url;

            File file = new File(destFile);
            destFile = context.getFilesDir().getAbsolutePath() + File.separator + file.getName();
            file = new File(destFile);
            if (file.exists()) return destFile;

            FileOutputStream os = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
            is.close();
            os.flush();
            os.close();
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
