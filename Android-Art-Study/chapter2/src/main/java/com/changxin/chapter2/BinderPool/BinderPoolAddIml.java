package com.changxin.chapter2.BinderPool;

import android.os.RemoteException;

/**
 * Created by changxin on 2017/1/4.
 */

public class BinderPoolAddIml extends BinderPoolAdd.Stub{
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
