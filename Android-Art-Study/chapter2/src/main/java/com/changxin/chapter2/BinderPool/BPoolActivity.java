package com.changxin.chapter2.BinderPool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.changxin.chapter2.R;

public class BPoolActivity extends AppCompatActivity {

//    private BinderPoolAdd binderPoolAddIml;
//    private BinderPoolEncre binderPoolEncre;
//    private String TAG = getClass().getSimpleName();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bpool);
//        new Thread(){
//            @Override
//            public void run() {
//                doWork();
//            }
//        }.start();
//    }
//    private void doWork() {
//        BinderPool binderPool = BinderPool.getmInstance(BPoolActivity.this);
//        IBinder iBinder = binderPool.qureyBinder(binderPool.binderPoolAddCode);
//
//        binderPoolAddIml = BinderPoolAddIml.asInterface(iBinder);
//        int a  = 0;
//        try {
//            a = binderPoolAddIml.add(3,5);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//        Log.d(TAG, "doWork: 3+5 = " + a);
//
//
//        IBinder iBinder1 = binderPool.qureyBinder(BinderPool.binderPoolEncreIml);
//        binderPoolEncre = BinderPoolEncreIml.asInterface(iBinder1);
////        try {
////            Log.d(TAG, "doWork: after encryed is " + binderPoolEncre.encrypt("hello encryed"));
////            Log.d(TAG, "doWork: after decryped is " + binderPoolEncre.decrypt("hello world"));
////        } catch (RemoteException e) {
////            e.printStackTrace();
////        }
//
//    }
private static final String TAG = "BinderPoolActivity";

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
        BinderPool binderPool = BinderPool.getmInstance(BPoolActivity.this);
        IBinder securityBinder = binderPool.qureyBinder(BinderPool.binderPoolEncreIml);


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
        IBinder computeBinder = binderPool.qureyBinder(BinderPool.binderPoolAddCode);

        mCompute = BinderPoolAddIml.asInterface(computeBinder);
//        try {
//            System.out.println("3+5=" + mCompute.add(3, 5));
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }
}
