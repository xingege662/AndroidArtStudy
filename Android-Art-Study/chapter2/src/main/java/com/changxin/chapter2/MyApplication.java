package com.changxin.chapter2;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.changxin.chapter2.myUtils.MyUtils;

/**
 * Created by changxin on 2016/12/28.
 */

public class MyApplication extends Application {

    private final  String TAG = getClass().getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());
        if(getApplicationInfo().packageName.equals(processName)) {

            Log.d(TAG, "onCreate:processName " + processName+"      "+getApplicationInfo().packageName);
        }
    }
}
