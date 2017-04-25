package com.app.utils;

import org.xutils.image.ImageOptions;

/**

 */
public class HeImageOptions {


    /**
     * Picture settings for avatar
     */
    public static ImageOptions options_image_doc = new ImageOptions.Builder()
            .setUseMemCache(false).setSquare(true).setRadius(10)
            .setIgnoreGif(false).build();

    public static ImageOptions options_image_circle = new ImageOptions.Builder()
            .setUseMemCache(false).setRadius(100).setCircular(true)
            .setIgnoreGif(false).build();

    public static ImageOptions options_image_send = new ImageOptions.Builder()
            .setUseMemCache(true).setSize(180,240)
            .setIgnoreGif(false).build();

    public static ImageOptions options_image_preview = new ImageOptions.Builder()
            .setUseMemCache(true).setSize(300, 300)
            .setIgnoreGif(false).build();
}