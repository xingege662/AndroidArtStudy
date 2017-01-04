package com.changxin.chapter2.BinderPool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.changxin.chapter2.R;

public class BPoolActivity extends AppCompatActivity {


    private static final String TAG = "BPoolActivity";
    private BinderPoolEncre mSecurityCenter;
    private BinderPoolAdd mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpool);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    private void doWork() {

       // BinderPool binderPool = BinderPool.getmInstance(BPoolActivity.this);
        BinderPool2 binderPool2 = BinderPool2.getInsance(BPoolActivity.this);

        IBinder securityBinder = binderPool2.queryBinder(BinderPool.binderPoolEncreIml) ;
        mSecurityCenter =  BinderPoolEncreIml.asInterface(securityBinder);
        Log.d(TAG, "visit ISecurityCenter");
        String msg = "helloworld-安卓";
        System.out.println("content:" + msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            System.out.println("encrypt:" + password);
            System.out.println("decrypt:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }



        Log.d(TAG, "visit ICompute");
        IBinder computeBinder = binderPool2.queryBinder(BinderPool.binderPoolAddCode);
        mCompute = BinderPoolAddIml.asInterface(computeBinder);
        try {
            System.out.println("3+5=" + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
