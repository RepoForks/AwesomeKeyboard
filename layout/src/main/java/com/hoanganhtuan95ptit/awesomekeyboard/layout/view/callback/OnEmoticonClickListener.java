package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.callback;

import java.io.Serializable;

/**
 * Created by HOANG ANH TUAN on 7/6/2017.
 */

public interface OnEmoticonClickListener extends Serializable {
    void onEmoticonsClicked(String key, int position);
}
