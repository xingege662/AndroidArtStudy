package com.voole.chapter12.loader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by changxin on 2017/2/8.
 */

public class ImageResizer {
    public static String TAG ="ImageResizer";

    /**
     * 从resource下加载Bitmap
     * @param resources
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
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

    /**
     *  从file文件中加载Bitmap
     * @param filePath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeBitmapFromFile(String filePath,int reqWidth ,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);

        options.inSampleSize = calculateInsamplesize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath,options);
    }

    /**
     *  从流中加载Bitmap
     * @param in
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public  static Bitmap decodeBitmapFromStream(InputStream in, int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        options.inSampleSize = calculateInsamplesize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(in,null,options);
    }

    /**
     * 从字节中加载Bitmap
     * @param data
     * @param offset
     * @param length
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeBitmapFromByteArray(byte[] data,int offset,int length,int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data,offset,length,options);
        options.inSampleSize = calculateInsamplesize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data,offset,length,options);
    }
    /**
     * 计算inSampleSize
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
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
