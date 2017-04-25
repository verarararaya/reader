package com.app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BitmapUtils {
    /**
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BitmapDrawable getBitmapDrawable(String path) {
        //get the thumbnails of the first frame through the path and display them
        Bitmap bitmap = Utils.createVideoThumbnail(path);
        BitmapDrawable drawable = new BitmapDrawable(bitmap);
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        drawable.setDither(true);
        return drawable;
    }

    // if enlarging the picture£¬filter decide whether slide£¬
    // if shrinking the picture£¬filter has no effect
    public static Bitmap createScaleBitmap(Bitmap src, int dstWidth,int dstHeight) {
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // if no zooming£¬then not reclaimed
            src.recycle(); // release native Pixel array of Bitmap
        }
        return dst;
    }
}
