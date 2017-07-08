package com.hoanganhtuan95ptit.awesomekeyboard.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoanganhtuan95ptit.awesomekeyboard.R;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnDeleteClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnItemStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.callback.OnShopClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.utils.CacheUtils;
import com.hoanganhtuan95ptit.awesomekeyboard.view.adapter.TitleAdapter;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnDeleteListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnEmoticonClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnStickerClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.callback.OnTitleClickListener;
import com.hoanganhtuan95ptit.awesomekeyboard.view.fragment.EmoticonsFragment;
import com.hoanganhtuan95ptit.awesomekeyboard.view.fragment.StickerFragment;
import com.hoanganhtuan95ptit.awesomekeyboard.view.model.Sticker;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOANG ANH TUAN on 6/28/2017.
 */

public class StickerLayout extends RelativeLayout implements OnTitleClickListener,
        OnDeleteListener,
        View.OnClickListener,
        OnEmoticonClickListener,
        OnStickerClickListener {

    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private ImageView imgShop;

    private ArrayList<Sticker> stickers;
    private TitleAdapter titleAdapter;

    private OnDeleteClickListener onDeleteClickListener;
    private OnShopClickListener onShopClickListener;
    private OnItemEmoticonClickListener onItemEmoticonClickListener;
    private OnItemStickerClickListener onItemStickerClickListener;

    public StickerLayout(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_sticker, this, false);
        mappingView(view);
        this.addView(view);
    }

    private void mappingView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_title);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_sticker);
        imgShop = (ImageView) view.findViewById(R.id.img_shop);
        initData();
        setOnclick();
    }

    private void setOnclick() {
        imgShop.setOnClickListener(this);
    }

    private void initData() {
        stickers = getStickers(getContext());
        initViewpager();
        initRecyclerView();
    }

    private void initRecyclerView() {
        titleAdapter = new TitleAdapter((Activity) getContext());
        titleAdapter.setOnTitleClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(titleAdapter);
        recyclerView.setHasFixedSize(true);

        titleAdapter.add("asset:///emoticons/16.png");
        for (int i = 0; i < stickers.size(); i++) {
            titleAdapter.add(stickers.get(i).getImage());
        }
    }

    private void initViewpager() {
        viewPager.setAdapter(new MyPagerAdapter(((FragmentActivity) getContext()).getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                titleAdapter.setSelect(position);
                recyclerView.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void executeSticker() {
        this.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_shop && onShopClickListener != null) {
            onShopClickListener.onShopClicked();
        }
    }

    @Override
    public void onDeleteClicked() {
        if (onDeleteClickListener != null) {
            onDeleteClickListener.onDeleteClicked();
        }
    }

    @Override
    public void onTitleClicked(int position) {
        viewPager.setCurrentItem(position);
        titleAdapter.setSelect(position);
    }

    @Override
    public void onStickerClicked(String url) {
        if (onItemStickerClickListener != null) {
            onItemStickerClickListener.onItemStickerClicked(url);
        }
    }

    @Override
    public void onEmoticonsClicked(String assetUrl) {
        if (onItemEmoticonClickListener != null) {
            onItemEmoticonClickListener.onItemEmoticonClicked(getImagSpan(getContext(), assetUrl));
        }
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public void setOnShopClickListener(OnShopClickListener onShopClickListener) {
        this.onShopClickListener = onShopClickListener;
    }

    public void setOnItemEmoticonClickListener(OnItemEmoticonClickListener onItemEmoticonClickListener) {
        this.onItemEmoticonClickListener = onItemEmoticonClickListener;
    }

    public void setOnItemStickerClickListener(OnItemStickerClickListener onItemStickerClickListener) {
        this.onItemStickerClickListener = onItemStickerClickListener;
    }

    public void updateSticker() {
        initData();
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return EmoticonsFragment.initialize(StickerLayout.this, StickerLayout.this);
            return StickerFragment.initialize(stickers.get(position - 1).getImages(), StickerLayout.this);
        }

        @Override
        public int getCount() {
            return stickers.size() + 1;
        }
    }

    private static ImageSpan getImagSpan(Context context, String url) {
        Drawable d = new BitmapDrawable(context.getResources(), getImage(context, url));
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        return new ImageSpan(d, convertFromTextToCode(url), ImageSpan.ALIGN_BASELINE);
    }

    private static SpannableStringBuilder convert(SpannableStringBuilder spannableStringBuilder, String code, Context context, String url) {
        StringBuilder stringBuilder = new StringBuilder(spannableStringBuilder.toString());
        int position = stringBuilder.indexOf(code);
        if (position >= 0) {
            spannableStringBuilder.replace(position, position + code.length(), Html.fromHtml("<img src ='" + url + "'/>", getImageGetter(context, url), null));
            spannableStringBuilder = convert(spannableStringBuilder, code, context, url);
        }
        return spannableStringBuilder;
    }

    private static Html.ImageGetter getImageGetter(final Context context, final String url) {
        return new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                Drawable d = new BitmapDrawable(context.getResources(), getImage(context, url));
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };
    }

    private static Bitmap getImage(Context context, String path) {
        AssetManager mngr = context.getAssets();
        InputStream in = null;
        try {
            in = mngr.open("emoticons/" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(in, null, null);
    }

    private static String convertFromTextToCode(String url) {
        switch (url) {
            case "1.png":
                url = ":,)";
                break;
            case "2.png":
                url = ":')";
                break;
            case "3.png":
                url = ":)";
                break;
            case "4.png":
                url = ":3";
                break;
            case "5.png":
                url = ":'(";
                break;
            case "6.png":
                url = ":D";
                break;
            case "7.png":
                url = ":|";
                break;
            case "8.png":
                url = ":/";
                break;
            case "9.png":
                url = ":B";
                break;
            case "10.png":
                url = ":/)";
                break;
            case "11.png":
                url = "f:|";
                break;
            case "12.png":
                url = ":/B";
                break;
            case "13.png":
                url = "p:|";
                break;
            case "14.png":
                url = ":p";
                break;
            case "15.png":
                url = ":b";
                break;
            case "16.png":
                url = ":))";
                break;
            case "17.png":
                url = ":|)";
                break;
            case "18.png":
                url = ":-B";
                break;
            case "19.png":
                url = ":'))";
                break;
            case "20.png":
                url = ":*|";
                break;
            case "21.png":
                url = ">:)";
                break;
            case "22.png":
                url = ":*";
                break;
            case "23.png":
                url = ">:|";
                break;
            case "24.png":
                url = "<:(";
                break;
            case "25.png":
                url = "B:(";
                break;
            case "26.png":
                url = "=(";
                break;
            case "27.png":
                url = "':(";
                break;
            case "28.png":
                url = "b)";
                break;
            case "29.png":
                url = ":zz";
                break;
            case "30.png":
                url = ":=";
                break;
            case "31.png":
                url = "~.:zz";
                break;
            case "32.png":
                url = ">:'|";
                break;
            case "33.png":
                url = ":oD";
                break;
            case "34.png":
                url = ":''";
                break;
            case "35.png":
                url = "8D";
                break;
            case "36.png":
                url = ":o";
                break;
            case "37png":
                url = ":n.";
                break;
            case "38.png":
                url = ":9";
                break;
            case "39.png":
                url = "8.";
                break;
            case "40.png":
                url = ";.";
                break;
            case "41.png":
                url = ":?";
                break;
            case "42.png":
                url = "K:.";
                break;
            case "43.png":
                url = "._|";
                break;
            case "44.png":
                url = ".|";
                break;
            case "45.png":
                url = "::)";
                break;
            case "46.png":
                url = "Spiderman";
                break;
            case "47.png":
                url = ";))";
                break;
            case "48.png":
                url = ";((";
                break;
            case "49.png":
                url = ">B:";
                break;
            case "50.png":
                url = ":'C";
                break;
            case "51.png":
                url = ":8";
                break;
            case "52.png":
                url = "B(";
                break;
            case "53.png":
                url = ":=`";
                break;
            case "54.png":
                url = ":=D";
                break;
        }
        return url;
    }

    public static ArrayList<Sticker> getStickers(Context context) {
        String data = CacheUtils.getData(context, R.string.app_name, "STICKER", "");
        ArrayList<Sticker> stickers = new Gson().fromJson(data, new TypeToken<List<Sticker>>() {
        }.getType());
        if (stickers == null) stickers = new ArrayList<>();
        return stickers;
    }

    public static void clearSticker(Context context) {
        CacheUtils.saveData(context, R.string.app_name, "STICKER", new Gson().toJson(new ArrayList<>()));
    }

    public static SpannableStringBuilder convertFromTextToEmoji(Context context, String text) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        for (int i = 54; i >= 1; i--) {
            String url = i + ".png";
            spannableStringBuilder = convert(spannableStringBuilder, convertFromTextToCode(url), context, url);
        }
        return spannableStringBuilder;
    }

    public static void addSticker(Context context, Sticker sticker) {
        //get data
        ArrayList<Sticker> stickers = getStickers(context);
        //add data
        stickers.add(stickers.size(), sticker);
        // save data
        CacheUtils.saveData(context, R.string.app_name, "STICKER", new Gson().toJson(stickers));
    }


}

//        public String getImageSpanByCode(String code){
//            String url = null;
//            switch (code) {
//                case ":,)":
//                    url = "1.png";
//                    break;
//                case ":')":
//                    url = "2.png";
//                    break;
//                case ":)":
//                    url = "3.png";
//                    break;
//                case ":3":
//                    url = "4.png";
//                    break;
//                case ":'(":
//                    url = "5.png";
//                    break;
//                case ":D":
//                    url = "6.png";
//                    break;
//                case ":|":
//                    url = "7.png";
//                    break;
//                case ":/":
//                    url = "8.png";
//                    break;
//                case ":B":
//                    url = "9.png";
//                    break;
//                case ":/)":
//                    url = "10.png";
//                    break;
//                case "f:|":
//                    url = "11.png";
//                    break;
//                case ":/B":
//                    url = "12.png";
//                    break;
//                case "p:|":
//                    url = "13.png";
//                    break;
//                case ":p":
//                    url = "14.png";
//                    break;
//                case ":b":
//                    url = "15.png";
//                    break;
//                case ":))":
//                    url = "16.png";
//                    break;
//                case ":|)":
//                    url = "17.png";
//                    break;
//                case ":-B":
//                    url = "18.png";
//                    break;
//                case ":'))":
//                    url = "19.png";
//                    break;
//                case ":*|":
//                    url = "20.png";
//                    break;
//                case ">:)":
//                    url = "21.png";
//                    break;
//                case ":*":
//                    url = "22.png";
//                    break;
//                case ">:|":
//                    url = "23.png";
//                    break;
//                case "<:(":
//                    url = "24.png";
//                    break;
//                case "B:(":
//                    url = "25.png";
//                    break;
//                case "=(":
//                    url = "26.png";
//                    break;
//                case "':(":
//                    url = "27.png";
//                    break;
//                case "b)":
//                    url = "28.png";
//                    break;
//                case ":zz":
//                    url = "29.png";
//                    break;
//                case ":=":
//                    url = "30.png";
//                    break;
//                case "~.:zz":
//                    url = "31.png";
//                    break;
//                case ">:'|":
//                    url = "32.png";
//                    break;
//                case ":oD":
//                    url = "33.png";
//                    break;
//                case ":''":
//                    url = "34.png";
//                    break;
//                case "8D":
//                    url = "35.png";
//                    break;
//                case ":o":
//                    url = "36.png";
//                    break;
//                case ":n.":
//                    url = "37png";
//                    break;
//                case ":9":
//                    url = "38.png";
//                    break;
//                case "8.":
//                    url = "39.png";
//                    break;
//                case ";.":
//                    url = "40.png";
//                    break;
//                case ":?":
//                    url = "41.png";
//                    break;
//                case "K:.":
//                    url = "42.png";
//                    break;
//                case "._|":
//                    url = "43.png";
//                    break;
//                case ".|":
//                    url = "44.png";
//                    break;
//                case "::)":
//                    url = "45.png";
//                    break;
//                case "Spiderman":
//                    url = "46.png";
//                    break;
//                case ";))":
//                    url = "47.png";
//                    break;
//                case ";((":
//                    url = "48.png";
//                    break;
//                case ">B:":
//                    url = "49.png";
//                    break;
//                case ":'C":
//                    url = "50.png";
//                    break;
//                case ":8":
//                    url = "51.png";
//                    break;
//                case "B(":
//                    url = "52.png";
//                    break;
//                case ":=`":
//                    url = "53.png";
//                    break;
//                case ":=D":
//                    url = "54.png";
//                    break;
//            }
//        }

