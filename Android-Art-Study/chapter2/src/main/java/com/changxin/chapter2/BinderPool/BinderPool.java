package com.changxin.chapter2.BinderPool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by changxin on 2017/1/4.
 * Binder连接池
 */

public class BinderPool {

    private String TAG = getClass().getSimpleName();
    public static final int binderPoolAddCode = 0;
    public static final int binderPoolEncreIml = 1;
    private Context mContext;


    private IBinderPool mIBinderPool;
    private static volatile BinderPool mInstance;
    private CountDownLatch mCountDownLatch;

    private BinderPool(Context context){
        mContext = context.getApplicationContext();
        ConnectBinderPoolService();
    }

    public static BinderPool getmInstance(Context context){
        if(mInstance == null){
            synchronized (BinderPool.class) {
                if (mInstance == null) {
                    mInstance = new BinderPool(context);
                }
            }
        }
        return mInstance;
    }

    private synchronized void ConnectBinderPoolService(){
        mCountDownLatch = new CountDownLatch(1);
        Intent service = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
        try {
            mCountDownLatch.await();//本线程停住
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                mIBinderPool.asBinder().linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();//释放
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ConnectBinderPoolService();
        }
    };

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied: Binder Dead");
            mIBinderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            mIBinderPool = null;
            //重连
            ConnectBinderPoolService();
        }
    };

    public IBinder qureyBinder(int queryCode){
        IBinder binder = null;
        if(binder == null){
            try {
                binder = mIBinderPool.queryBinder(queryCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;
    }

    public static class  BinderPoolIml extends IBinderPool.Stub{
        public BinderPoolIml(){
            super();
        }
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case binderPoolAddCode:
                    binder = new BinderPoolIml();
                    break;
                case binderPoolEncreIml:
                    binder = new BinderPoolEncreIml();
                    break;

            }
            return binder;
        }
    }
}
