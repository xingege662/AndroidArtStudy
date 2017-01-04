package com.changxin.chapter2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by changxin on 2017/1/3.
 */

public class BookProvider extends ContentProvider {
    private String TAG = getClass().getSimpleName();
    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: "+"Thread Name" + Thread.currentThread().getName());
        return false;
    }

    /**
     * 所处的binder线程池中只有两个线程
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "onCreate: "+"Thread Name" + Thread.currentThread().getName());
        getContext().getContentResolver().notifyChange(uri,null);
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "onCreate: "+"Thread Name" + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "onCreate: "+"Thread Name" + Thread.currentThread().getName());
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "onCreate: "+"Thread Name" + Thread.currentThread().getName());
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "onCreate: "+"Thread Name" + Thread.currentThread().getName());
        return 0;
    }
}
