package com.app.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class Utils {
    @SuppressLint("NewApi")
    public static Bitmap createVideoThumbnail(String filePath) {
        // MediaMetadataRetriever is available on API Level 8
        // but is hidden until API Level 10
        Class<?> clazz = null;
        Object instance = null;
        try {
            clazz = Class.forName("android.media.MediaMetadataRetriever");
            instance = clazz.newInstance();

            Method method = clazz.getMethod("setDataSource", String.class);
            method.invoke(instance, filePath);

            // The method name changes between API Level 9 and 10.
            if (Build.VERSION.SDK_INT <= 9) {
                return (Bitmap) clazz.getMethod("captureFrame").invoke(instance);
            } else {
                byte[] data = (byte[]) clazz.getMethod("getEmbeddedPicture").invoke(instance);
                if (data != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    if (bitmap != null) return bitmap;
                }
                return (Bitmap) clazz.getMethod("getFrameAtTime").invoke(instance);
            }
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } catch (InstantiationException e) {
            Log.e("TAG", "createVideoThumbnail", e);
        } catch (InvocationTargetException e) {
            Log.e("TAG", "createVideoThumbnail", e);
        } catch (ClassNotFoundException e) {
            Log.e("TAG", "createVideoThumbnail", e);
        } catch (NoSuchMethodException e) {
            Log.e("TAG", "createVideoThumbnail", e);
        } catch (IllegalAccessException e) {
            Log.e("TAG", "createVideoThumbnail", e);
        } finally {
            try {
                if (instance != null) {
                    clazz.getMethod("release").invoke(instance);
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
    public static String getDateForCircle(String dateTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time = "";
        Long dateRecord = date.getTime();
        Long now = new Date().getTime();
        Long delta = now - dateRecord;
        int delatDay = new Date().getDay() - date.getDay();
        //Not shown in three minutes
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
        time = dfTime.format(date);
        if(delta<=3*3600){
            time ="just now";
        }else if (delatDay == 0) {
            time = "" + time;
        } else if (delatDay == 1) {
            time = "yesterday " + time;
        } else if (delatDay == 2) {
            time = "The day before yesterday " + time;
        } else {
            SimpleDateFormat dfDay = new SimpleDateFormat("MM-dd HH:mm");
            time = dfDay.format(date);
        }

        return time;


    }


    public static String getDate(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "";
        Long dateRecord = date.getTime();
        Long now = new Date().getTime();
        Long delta = now - dateRecord;
        int delatDay = new Date().getDay() - date.getDay();
        //Not shown in three minutes
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
        time = dfTime.format(date);
        if (delatDay == 0) {
            time = " " + time;
        } else if (delatDay == 1) {
            time = " " + time;
        } else if (delatDay == 2) {
            time = " " + time;
        } else {
            SimpleDateFormat dfDay = new SimpleDateFormat("MM-dd HH:mm");
            time = dfDay.format(date);
        }

        return time;
    }

    public  static int getPicsNum(String pics){
        if(pics==null||"".equals(pics)){
            return 0;
        }else if(!pics.contains("-")){
            return 1;
        }else {
            return pics.split("-").length;
        }
    }

    public static String getDomainName(int id){
        String domian = "";
        switch (id){
            case 0:
                domian =  "all";
                break;
           
        }
        return domian;
    }


}
