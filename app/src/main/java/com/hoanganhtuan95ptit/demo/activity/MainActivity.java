package com.hoanganhtuan95ptit.demo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hoanganhtuan95ptit.awesomekeyboard.Keyboard;
import com.hoanganhtuan95ptit.awesomekeyboard.KeyboardBuilder;
import com.hoanganhtuan95ptit.awesomekeyboard.KeyboardLayout;
import com.hoanganhtuan95ptit.awesomekeyboard.KeyboardType;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemColorClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemPhotoClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnKeyDownListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnShopClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.ListenerEditText;
import com.hoanganhtuan95ptit.demo.R;
import com.hoanganhtuan95ptit.demo.adapter.ChatAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnItemColorClickListener,
        OnItemPhotoClickListener,
        OnItemStickerClickListener,
        OnKeyDownListener,
        OnShopClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.listenerEditText)
    ListenerEditText listenerEditText;
    @BindView(R.id.img_send)
    ImageView imgSend;
    @BindView(R.id.img_emoji)
    ImageView imgEmoji;
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.img_color)
    ImageView imgColor;
    @BindView(R.id.keyboardLayout)
    KeyboardLayout keyboardLayout;
    @BindView(R.id.root)
    RelativeLayout root;

    private ChatAdapter chatAdapter;
    private Keyboard keyboard;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setRecyclerView();
        setKeyboard();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this);
        recyclerView.setAdapter(chatAdapter);
    }

    public void setKeyboard() {
        keyboard = KeyboardBuilder.with(this)
                .setEditText(listenerEditText)
                .setKeyboardLayout(keyboardLayout)
                .setRoot(root)
                .setOnItemColorClickListener(this)
                .setOnItemPhotoClickListener(this)
                .setOnItemStickerClickListener(this)
                .setOnKeyDownListener(this)
                .setOnShopClickListener(this)
                .builder();
        listenerEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyboard.showKeyboard(KeyboardType.NOMAL);
                return false;
            }
        });
    }

    @OnClick({R.id.img_send, R.id.img_emoji, R.id.img_photo, R.id.img_color})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_send:
                if (!listenerEditText.getText().toString().isEmpty()) {
                    chatAdapter.add(KeyboardBuilder.convertFromTextToEmoji(this, listenerEditText.getText().toString()));
                    listenerEditText.setText("");
                }
                break;
            case R.id.img_emoji:
                keyboard.showKeyboard(KeyboardType.STICKER);
                break;
            case R.id.img_photo:
                addPermisstions(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.img_color:
                keyboard.showKeyboard(KeyboardType.COLOR);
                break;
        }
    }

    @Override
    public void acceptPermisstion(String[] hasPermissions, String[] noPermissions) {
        super.acceptPermisstion(hasPermissions, noPermissions);
        if (new Gson().toJson(hasPermissions).contains(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                new Gson().toJson(hasPermissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            keyboard.showKeyboard(KeyboardType.PHOTO);
        } else if (new Gson().toJson(noPermissions).contains(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                new Gson().toJson(noPermissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showMessage(R.string.not_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onKeyDownCLicked() {
        Toast.makeText(this, "onKeyDownCLicked ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShopClicked() {
        Toast.makeText(this, "onShopClicked ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemColorClicked(String color) {
        Toast.makeText(this, "onItemColorClicked ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemPhotoClicked(String url) {
        Toast.makeText(this, "onItemPhotoClicked ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemStickerClicked(String url) {
        Toast.makeText(this, "onItemStickerClicked ", Toast.LENGTH_LONG).show();
    }
}
