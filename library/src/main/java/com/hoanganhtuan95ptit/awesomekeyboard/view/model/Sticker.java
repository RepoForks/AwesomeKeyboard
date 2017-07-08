package com.hoanganhtuan95ptit.awesomekeyboard.view.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 6/29/2017.
 */

public class Sticker implements Serializable {
    private String image;
    private String name;
    private ArrayList<String> images;

    public Sticker(String image, String name, ArrayList<String> images) {
        this.image = image;
        this.name = name;
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
