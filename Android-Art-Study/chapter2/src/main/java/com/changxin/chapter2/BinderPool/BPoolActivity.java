package com.changxin.chapter2.BinderPool;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.changxin.chapter2.R;

public class BPoolActivity extends AppCompatActivity {

    private BinderPoolAddIml binderPoolAddIml;
    private BinderPoolEncreIml binderPoolEncre;
    private String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpool);
        new Thread(){
            @Override
            public void run() {
                doWork();
            }
        }.start();
    }
    private void doWork() {
        BinderPool binderPool = BinderPool.getmInstance(BPoolActivity.this);
        IBinder iBinder = (Binder) binderPool.qureyBinder(binderPool.binderPoolAddCode);

        binderPoolAddIml = (BinderPoolAddIml) BinderPoolAddIml.asInterface(iBinder);
        try {
            Log.d(TAG, "doWork: 3+5 = " + binderPoolAddIml.add(3,5) );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        binderPoolEncre = (BinderPoolEncreIml) BinderPoolEncreIml.asInterface(iBinder);
        try {
            Log.d(TAG, "doWork: after encryed is " + binderPoolEncre.encrypt("hello encryed"));
            Log.d(TAG, "doWork: after decryped is " + binderPoolEncre.decrypt("hello world"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
