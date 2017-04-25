package com.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {

    /**
     * Name the specified file name by time
     */
    public static SimpleDateFormat OUTGOING_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

    /**
     * Get the path of the output Video 
     *
     * @return
     */
    public static String getEMRVideoFilePath(String path) {
        String videoFileName = "video_" + FileUtils.OUTGOING_DATE_FORMAT.format(new Date()) + ".mp4";
        return path + File.separator + videoFileName;
    }

    /**
     * Get the saved path of the audio
     *
     * @param path
     * @return
     */
    public static String getEMRSoundFilePath(String path) {
        String soundFileName = "sound_" + FileUtils.OUTGOING_DATE_FORMAT.format(new Date()) + ".mp3";
        return path + File.separator + soundFileName;
    }

    /**
     * Get the saved path of the picture
     *
     * @param path
     * @return
     */
    public static String getEMRPhotoFilePath(String path) {
        String photoFileName = "photo_" + FileUtils.OUTGOING_DATE_FORMAT.format(new Date()) + ".jpg";
        return path + File.separator + photoFileName;
    }

    /**
     * Get the saved path of the xml
     *
     * @param path
     * @return
     */
    public static String getEMRXMLFilePath(String path) {
        String xmlFileName = "xml_" + FileUtils.OUTGOING_DATE_FORMAT.format(new Date()) + ".xml";
        return path + File.separator + xmlFileName;
    }

    /**
     * Get the saved path of the json
     *
     * @param path
     * @return
     */
    public static String getEMRJSONFilePath(String path) {
        String jsonFileName = "json_" + FileUtils.OUTGOING_DATE_FORMAT.format(new Date()) + ".json";
        return path + File.separator + jsonFileName;
    }

    public static String getDOCTORDetailPath(String path) {
        String jsonFileName = "json_" + FileUtils.OUTGOING_DATE_FORMAT.format(new Date()) + ".json";
        return path + File.separator + jsonFileName;
    }

    /**
     * Add a thumbnail address for the image
     *
     * @param msg
     * @return
     */
    public static String ImageToThumbnail(String msg) {
        String thumbnail = "";
        String fileName = "";
        String[] token = msg.split(File.separator);
        fileName = "thumbnail." + token[token.length - 1];
        for (int i = 0; i < token.length - 1; i++) {
            thumbnail = thumbnail + "/" + token[i];
        }
        thumbnail = thumbnail + "/" + fileName;
        thumbnail = thumbnail.substring(1, thumbnail.length());
        return thumbnail.trim();
    }

    /**
     * Get the file name from the network address
     * @param msg
     * @return
     */
    public static String getFileNameFromUrl(String msg){
        String fileName = "";
        String[] token = msg.split(File.separator);
        fileName = token[token.length-1];
        return fileName.trim();
    }

    /**
     * Generate the bitmap collection and copy it to the specified directory
     *
     * @param photoPaths
     * @param path
     * @return
     */
    public static ArrayList<String> copyEMRIamge(ArrayList<String> photoPaths, String path) {

        ArrayList<String> photoList = new ArrayList<String>();
        for (String photo : photoPaths) {
            //photoList.add(getLocalBitmap(photo)) ;
            try {
                String photoPath = getEMRPhotoFilePath(path);
                InputStream from = new FileInputStream(photo.trim());
                OutputStream to = new FileOutputStream(photoPath);
                byte bt[] = new byte[1024];
                int c;
                while ((c = from.read(bt)) > 0) {
                    to.write(bt, 0, c);
                }
                from.close();
                to.close();
                photoList.add(photoPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return photoList;
    }

    //Transform the image stream into a bitmap
    public static Bitmap getLocalBitmap(String url) {
        File file = new File(url);
        try {
            if (file.exists()) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inJustDecodeBounds = false;
                opt.inSampleSize = 10;   //Wide and high as the original one-tenth
                FileInputStream fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis, null, opt);  ///Transform the stream into a Bitmap image
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the file extension name
     *
     * @param filePath
     * @return
     */
    public static String getFileExtName(String filePath) {
        String fileExtname = null;
        String[] token = filePath.split("\\.");
        fileExtname = token[token.length - 1];
        return fileExtname;
    }

    /**
     * Get the file extension name
     *
     * @param filaPath
     * @return
     */
    public static String getFileName(String filaPath) {
        String fileName = "";
        String[] token = filaPath.split(File.separator);
        fileName = token[token.length - 1];
        return fileName;
    }

    /**
     * Get the file name
     *
     * @param audioList
     * @return
     */
    public static List<String> getFileNameList(List<String> audioList) {
        List<String> audioNameList = new ArrayList<String>();
        for (String audio : audioList) {
            String[] token = audio.split(File.separator);
            audioNameList.add(token[token.length - 1]);
        }
        return audioNameList;
    }

    /**
     * Get the penultimate suffix name
     *
     * @param stringName
     * @return
     */
    public static String getFileLastTwoName(String stringName) {

        String lastTwoName = null;
        String[] token = stringName.split("\\.");
        int length = token.length;
        if (length >= 2) {
            lastTwoName = token[token.length - 2];
        }
        return lastTwoName;
    }

    /**
     * Generate json file
     *
     * @param content
     * @param jsonUrl
     */
    public static void occurJson(String content, String jsonUrl) {
        File jsonFile = new File(jsonUrl);
        try {
            jsonFile.createNewFile();
            FileOutputStream jsonfile = new FileOutputStream(jsonFile);
            PrintStream p = new PrintStream(jsonfile);
            p.println(content);
            jsonfile.close();
            p.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the numeric string to date format
     *
     * @param num
     * @return
     */
    public static String NumToDate(String num) {
        String dateString = "";
        String year = num.substring(0, 4);
        String month = num.substring(4, 6);
        String day = num.substring(6, 8);
        String hour = num.substring(8, 10);
        String minute = num.substring(10, 12);
        String second = num.substring(12, 14);
        dateString = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        return dateString;
    }

    /**
     * Write a string to a file
     *
     * @param content
     * @param filePath
     * @return
     */
    public static File writeToFile(String content, String filePath) {
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(content);//Writes a string to a file under the specified path
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    
    /**
     * Get the local file path
     * @param context
     * @param uri
     * @return
     */
    
    public static String getPath(Context context, Uri uri) {
        
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;
 
            try {
                cursor = context.getContentResolver().query(uri, projection,null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
 
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
 
        return null;
    }
}
