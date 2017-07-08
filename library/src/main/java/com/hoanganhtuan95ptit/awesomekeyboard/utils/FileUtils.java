package com.hoanganhtuan95ptit.awesomekeyboard.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.hoanganhtuan95ptit.awesomekeyboard.utils.callback.OnPictureRead;

import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;


/*
 * lưu dữ liệu
 * Created by HOANG ANH TUAN on 6/28/2017.
 */

public class FileUtils {

    /**
     * lấy danh sách ảnh trong thiết bị
     *
     * @param activity    activity
     * @param pictureRead dữ liệu được trả về tại đây
     */
    public static void readAllPicture(final Activity activity, final OnPictureRead pictureRead) {
        new AsyncTask<Activity, File, Void>() {

            @Override
            protected Void doInBackground(Activity... params) {
                Activity activity = params[0];
                Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String[] projection = {MediaStore.Images.ImageColumns.DATA};
                Cursor c = null;
                SortedSet<String> dirList = new TreeSet<>();
                String[] directories = null;
                if (u != null) {
                    c = activity.getContentResolver().query(u, projection, null, null, null);
                }
                if ((c != null) && (c.moveToFirst())) {
                    do {
                        String tempDir = c.getString(0);
                        tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
                        dirList.add(tempDir);
                    }
                    while (c.moveToNext());
                    directories = new String[dirList.size()];
                    dirList.toArray(directories);
                }
                for (int i = 0; i < dirList.size(); i++) {
                    File imageDir = new File(directories[i]);
                    File[] imageList = imageDir.listFiles();
                    if (imageList == null) continue;
                    for (File imagePath : imageList) {
                        imagePath.isDirectory();
                        if (imagePath.getName().contains(".jpg") || imagePath.getName().contains(".JPG")
                                || imagePath.getName().contains(".jpeg") || imagePath.getName().contains(".JPEG")) {
                            publishProgress(imagePath);
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(File... values) {
                super.onProgressUpdate(values);
                File file = values[0];
                pictureRead.pictureRead(file.getAbsolutePath(), file.lastModified());
            }
        }.execute(activity);

    }
}
