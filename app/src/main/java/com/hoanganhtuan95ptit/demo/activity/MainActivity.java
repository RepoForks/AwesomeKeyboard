package com.hoanganhtuan95ptit.demo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hoanganhtuan95ptit.awesomekeyboard.core.Keyboard;
import com.hoanganhtuan95ptit.awesomekeyboard.core.KeyboardBuilder;
import com.hoanganhtuan95ptit.awesomekeyboard.core.callback.OnKeyDownListener;
import com.hoanganhtuan95ptit.awesomekeyboard.core.view.EmojiconEditText;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.AwesomeKeyboardLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.AwesomeKeyboardType;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.EmojiconCategory;
import com.hoanganhtuan95ptit.awesomekeyboard.layout.view.model.StickerCategory;
import com.hoanganhtuan95ptit.demo.R;
import com.hoanganhtuan95ptit.demo.adapter.ChatAdapter;
import com.hoanganhtuan95ptit.demo.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnKeyDownListener, OnItemColorClickListener,
        OnItemEmoticonClickListener,
        OnItemPhotoClickListener,
        OnItemStickerClickListener,
        OnDeleteClickListener {

    private static final String TAG = "MainActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.item_chat)
    RecyclerView recyclerViewChat;
    @BindView(R.id.edit_message)
    EmojiconEditText editMessage;
    @BindView(R.id.keyboard_layout)
    AwesomeKeyboardLayout keyboardLayout;
    @BindView(R.id.re_root)
    RelativeLayout reRoot;

    private Keyboard keyboard;
    private ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initData();
        initKeyboard();
        setOnListener();
        setRecycleView();
    }

    private void setRecycleView() {
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this);
        recyclerViewChat.setAdapter(chatAdapter);
    }

    private void setOnListener() {
        keyboardLayout.setOnItemColorClickListener(this);
        keyboardLayout.setOnItemPhotoClickListener(this);
        keyboardLayout.setOnItemStickerClickListener(this);
        keyboardLayout.setOnItemEmoticonClickListener(this);
        keyboardLayout.setOnDeleteClickListener(this);
        editMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyboard.showKeyboard(AwesomeKeyboardType.NOMAL.getValue());
                return false;
            }
        });
    }

    private void initData() {
        ArrayList<EmojiconCategory> emoticonCategories = Utils.initDataEmoticonCategory();
        ArrayList<StickerCategory> stickerCategories = Utils.initDataStickerCategory();

        for (int i = 0; i < emoticonCategories.size(); i++) {
            keyboardLayout.updateDataEmoticon(emoticonCategories.get(i));
        }

        for (int i = 0; i < stickerCategories.size(); i++) {
            keyboardLayout.updateDataSticker(stickerCategories.get(i));
        }

        keyboardLayout.updateDataColor("#1ecef3");
        keyboardLayout.updateDataColor("#12cf13");
        keyboardLayout.updateDataColor("#43bec5");
        keyboardLayout.updateDataColor("#67b967");
        keyboardLayout.updateDataColor("#0084fd");
        keyboardLayout.updateDataColor("#6599cb");
        keyboardLayout.updateDataColor("#7547ff");
        keyboardLayout.updateDataColor("#a695c9");
        keyboardLayout.updateDataColor("#d4a88b");
        keyboardLayout.updateDataColor("#d496bb");
        keyboardLayout.updateDataColor("#e58684");
        keyboardLayout.updateDataColor("#f83c4b");
        keyboardLayout.updateDataColor("#fd5ca2");
        keyboardLayout.updateDataColor("#fdc300");
        keyboardLayout.updateDataColor("#ff7f28");
    }

    private void initKeyboard() {
        keyboard = KeyboardBuilder.with(this)
                .setRoot(reRoot)
                .setKeyboardLayout(keyboardLayout)
                .setOnKeyDownListener(this)
                .addEditText(editMessage)
                .builder();
    }


    @OnClick({R.id.img_photo, R.id.img_sticker, R.id.img_color, R.id.img_emojicon, R.id.img_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_photo:
                addPermisstions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.img_sticker:
                keyboard.showKeyboard(AwesomeKeyboardType.STICKER.getValue());
                break;
            case R.id.img_color:
                keyboard.showKeyboard(AwesomeKeyboardType.COLOR.getValue());
                break;
            case R.id.img_emojicon:
                keyboard.showKeyboard(AwesomeKeyboardType.EMOJICON.getValue());
                break;
            case R.id.img_send:
                if (editMessage.getText().length() > 0) {
                    chatAdapter.add(editMessage.getText().toString());
                    editMessage.setText("");
                }
                break;
        }
    }

    @Override
    public void acceptPermisstion(String[] hasPermissions, String[] noPermissions) {
        super.acceptPermisstion(hasPermissions, noPermissions);
        if (new Gson().toJson(hasPermissions).contains(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                new Gson().toJson(hasPermissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            keyboard.showKeyboard(AwesomeKeyboardType.PHOTO.getValue());
        } else if (new Gson().toJson(noPermissions).contains(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                new Gson().toJson(noPermissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showMessage(R.string.not_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onKeyDownCLicked() {
        Toast.makeText(this, "onKeyDownCLicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemColorClicked(String color) {
        Toast.makeText(this, "onItemColorClicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemPhotoClicked(String url) {
        Toast.makeText(this, "onItemPhotoClicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemEmoticonClicked(String key) {
        Log.e(TAG,"onItemEmoticonClicked");
        int start = editMessage.getSelectionStart();
        int end = editMessage.getSelectionEnd();
        editMessage.getEditableText().replace(start, end, key);
    }

    @Override
    public void onItemStickerClicked(String url) {
        Toast.makeText(this, "onItemStickerClicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeleteClicked() {
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
        editMessage.dispatchKeyEvent(event);
    }

}
