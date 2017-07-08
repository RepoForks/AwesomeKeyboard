package com.hoanganhtuan95ptit.awesomekeyboard.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;


/*
 * lưu trữ liệu tại local
 * Created by HoangAnhTuan on 6/21/2017.
 */

public class CacheUtils {

    /**
     * lưu dữ liệu dang string
     *
     * @param context context
     * @param appName tên ứng dụng
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu lưu
     */
    public static void saveData(Context context, @StringRes int appName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * lưu dữ liệu dang float
     *
     * @param context context
     * @param appName tên ứng dụng
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu lưu
     */
    public static void saveData(Context context, @StringRes int appName, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * lưu dữ liệu dang int
     *
     * @param context context
     * @param appName tên ứng dụng
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu lưu
     */
    public static void saveData(Context context, @StringRes int appName, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * lưu dữ liệu dang long
     *
     * @param context context
     * @param appName tên ứng dụng
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu lưu
     */
    public static void saveData(Context context, @StringRes int appName, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * lưu dữ liệu dang boolean
     *
     * @param context context
     * @param appName tên ứng dụng
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu lưu
     */
    public static void saveData(Context context, @StringRes int appName, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * lấy dữ liệu dạng string ra
     *
     * @param context context
     * @param appName tên app
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu sẽ được thay thế, nếu không có dữ liệu
     * @return dữ liệu trả về
     */
    public static String getData(Context context, @StringRes int appName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }

    /**
     * lấy dữ liệu dạng string ra
     *
     * @param context context
     * @param appName tên app
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu sẽ được thay thế, nếu không có dữ liệu
     * @return dữ liệu trả về
     */
    public static float getData(Context context, @StringRes int appName, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, value);
    }

    /**
     * lấy dữ liệu dạng string ra
     *
     * @param context context
     * @param appName tên app
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu sẽ được thay thế, nếu không có dữ liệu
     * @return dữ liệu trả về
     */
    public static int getData(Context context, @StringRes int appName, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, value);
    }

    /**
     * lấy dữ liệu dạng string ra
     *
     * @param context context
     * @param appName tên app
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu sẽ được thay thế, nếu không có dữ liệu
     * @return dữ liệu trả về
     */
    public static long getData(Context context, @StringRes int appName, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, value);
    }

    /**
     * lấy dữ liệu dạng string ra
     *
     * @param context context
     * @param appName tên app
     * @param key     key dữ liệu cần lưu
     * @param value   dữ liệu sẽ được thay thế, nếu không có dữ liệu
     * @return dữ liệu trả về
     */
    public static boolean getData(Context context, @StringRes int appName, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(appName), Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, value);
    }
}
