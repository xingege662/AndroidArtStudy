package com.voole.chapter12.loader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by changxin on 2017/2/8.
 */

public class ImageResizer {
    public static String TAG ="ImageResizer";
    public static Bitmap decodeBitmapFromResource(Resources resources, int resId, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,resId,options);
        //计算
        options.inSampleSize = calculateInsamplesize(options,reqWidth,reqHeight);
        //重新加载
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resId);

    }

    private static int calculateInsamplesize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0) {
            return 1;
        }
        int inSampleSize = 1;
        int height = options.outHeight;
        int width = options.outWidth;
        Log.d(TAG, "calculateInsamplesize: height = "+height);
        Log.d(TAG, "calculateInsamplesize: width = "+width);
        if(height > reqHeight || width > reqWidth){
            int halfHeight = height / 2;
            int halfWidth = width / 2 ;
            while((halfHeight/inSampleSize)>= reqHeight && (halfWidth/inSampleSize)>=reqWidth){
                inSampleSize = inSampleSize * 2;
            }
        }
        Log.d(TAG, "calculateInsamplesize: inSampleSize = "+inSampleSize);
        return inSampleSize;
    }
}
