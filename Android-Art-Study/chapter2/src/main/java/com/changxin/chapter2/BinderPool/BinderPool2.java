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
 */

public class BinderPool2 {

    private String TAG = getClass().getSimpleName();
    public static final int binderPoolAddCode = 0;
    public static final int binderPoolEncreIml = 1;
    private Context mContext;
    private IBinderPool mIBinderPool;
    private static volatile BinderPool2 mInstance;
    private CountDownLatch mCountDownLatch;

    private BinderPool2(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPool2 getInsance(Context context) {
        if (mInstance == null) {
            synchronized (BinderPool.class) {
                if (mInstance == null) {
                    mInstance = new BinderPool2(context);
                }
            }
        }
        return mInstance;
    }

    private synchronized void connectBinderPoolService() {
        mCountDownLatch = new CountDownLatch(1);

        Intent service = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(service, mBinderPoolConnection,
                Context.BIND_AUTO_CREATE);
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * query binder by binderCode from binder pool
     *
     * @param binderCode
     *            the unique token of binder
     * @return binder who's token is binderCode<br>
     *         return null when not found or BinderPoolService died.
     */
    public IBinder queryBinder(int binderCode) {
        IBinder binder = null;
        try {
            if (mIBinderPool != null) {
                binder = mIBinderPool.queryBinder(binderCode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

    private ServiceConnection mBinderPoolConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // ignored.
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                mIBinderPool.asBinder().linkToDeath(mBinderPoolDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
        }
    };

    private IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.w(TAG, "binder died.");
            mIBinderPool.asBinder().unlinkToDeath(mBinderPoolDeathRecipient, 0);
            mIBinderPool = null;
            connectBinderPoolService();
        }
    };

    public static class BinderPoolImpl extends IBinderPool.Stub {

        public BinderPoolImpl() {
            super();
        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case binderPoolEncreIml: {
                    binder = new BinderPoolEncreIml();
                    break;
                }
                case binderPoolAddCode: {
                    binder = new BinderPoolAddIml();
                    break;
                }
                default:
                    break;
            }

            return binder;
        }
    }

}

