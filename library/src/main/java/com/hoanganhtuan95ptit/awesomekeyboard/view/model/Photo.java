package com.hoanganhtuan95ptit.awesomekeyboard.view.model;

import java.io.Serializable;

/**
 * Created by DuyPC on 5/23/2017.
 */

public class Photo implements Serializable {

    private String url;
    private long time;
    private boolean select=false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
