package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model;

import java.io.Serializable;

/**
 * Created by HOANG ANH TUAN on 7/19/2017.
 */

public class Emojicon implements Serializable {
    private String key;
    private String url;

    public Emojicon(String key, String url) {
        this.key = key;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public Emojicon setKey(String key) {
        this.key = key;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Emojicon setUrl(String url) {
        this.url = url;
        return this;
    }
}
