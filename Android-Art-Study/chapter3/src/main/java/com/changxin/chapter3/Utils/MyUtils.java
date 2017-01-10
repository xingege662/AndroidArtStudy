package com.changxin.chapter3.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by changxin on 2017/1/10.
 */

public class MyUtils {
    /**
     * 获取线程名字
     * @param context
     * @param pid
     * @return
     */
    public static String getProcessName(Context context,int pid){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if(runningAppProcesses == null){
            return null;
        }
        for(ActivityManager.RunningAppProcessInfo runningProcess:runningAppProcesses){
            if(runningProcess.pid == pid){
                return runningProcess.processName;
            }
        }
        return null;
    }

    /**
     * 关闭资源
     * @param closeable
     */
    public static void close(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DisplayMetrics getScreenMetrics(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics;
    }


}
