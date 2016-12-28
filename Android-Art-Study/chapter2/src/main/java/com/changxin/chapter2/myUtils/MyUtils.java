package com.changxin.chapter2.myUtils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by changxin on 2016/12/28.
 */

public class MyUtils {
    public static String getProcessName(Context context,int pid){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        if (appProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
            if (runningAppProcessInfo.pid == pid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }
}
