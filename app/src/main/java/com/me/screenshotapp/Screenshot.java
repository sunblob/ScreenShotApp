package com.me.screenshotapp;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class Screenshot {

    public static Bitmap takescreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfRootView(View v) {
        return takescreenshot(v.getRootView());
    }

    public static void saveScreenshot(Bitmap bitmap) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/" + now + ".jpg";

        File mDir = new File(mPath + "/Screen");
        mDir.mkdirs();

        File imageFile = new File(mPath);

        if (imageFile.exists()) {
            imageFile.delete();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}