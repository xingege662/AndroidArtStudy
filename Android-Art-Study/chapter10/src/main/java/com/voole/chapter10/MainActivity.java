package com.voole.chapter10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private ThreadLocal<Boolean> mThreadLocal = new ThreadLocal<>();
    private String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThreadLocal.set(true);
        Log.d(TAG, "MainThread:"+mThreadLocal.get());
        new Thread("thread1"){
            @Override
            public void run() {
                mThreadLocal.set(false);
                Log.d(TAG, "thread1:"+mThreadLocal.get());
            }
        }.start();
        new Thread("thread2"){
            @Override
            public void run() {
                Log.d(TAG, "thread2:"+mThreadLocal.get());
            }
        }.start();
    }
}
