package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/11/2017.
 */

public class StickerCategory implements Serializable {
    private String urlTitle;
    private ArrayList<String> stickers;

    public StickerCategory(String urlTitle, ArrayList<String> stickers) {
        this.urlTitle = urlTitle;
        this.stickers = stickers;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public ArrayList<String> getStickers() {
        return stickers;
    }

    public void setStickers(ArrayList<String> stickers) {
        this.stickers = stickers;
    }
}
