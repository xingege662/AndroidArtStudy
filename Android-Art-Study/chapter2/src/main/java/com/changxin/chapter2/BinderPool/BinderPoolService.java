package com.changxin.chapter2.BinderPool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by changxin on 2017/1/4.
 */

public class BinderPoolService extends Service {

    private String TAG = getClass().getSimpleName();
    
    private Binder mBinderPool = new BinderPool.BinderPoolIml();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinderPool;
    }
}
