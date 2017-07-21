package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model;

import java.io.Serializable;

/**
 * Created by HOANG ANH TUAN on 7/11/2017.
 */

public class Photo implements Serializable {

    private String url;
    private long timeCreate;

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
