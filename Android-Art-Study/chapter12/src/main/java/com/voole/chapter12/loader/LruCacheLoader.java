package com.voole.chapter12.loader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by changxin on 2017/2/8.
 */

public class LruCacheLoader {
    //单位为KB
    long maxMemory = Runtime.getRuntime().maxMemory()/1024;
    int cacheSize = (int) (maxMemory/8);
    {
        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;//这里的单位要和上面的cacheSize相同

            }

        };
    }


}
