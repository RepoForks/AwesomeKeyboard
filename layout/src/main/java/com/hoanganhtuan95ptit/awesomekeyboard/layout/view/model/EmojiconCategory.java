package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/19/2017.
 */

public class EmojiconCategory implements Serializable {
    private String urlTitle;
    private ArrayList<Emojicon> emojicons;

    public EmojiconCategory(String urlTitle, ArrayList<Emojicon> emojicons) {
        this.urlTitle = urlTitle;
        this.emojicons = emojicons;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public EmojiconCategory setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
        return this;
    }

    public ArrayList<Emojicon> getEmojicons() {
        return emojicons;
    }

    public EmojiconCategory setEmojicons(ArrayList<Emojicon> emojicons) {
        this.emojicons = emojicons;
        return this;
    }
}
