package com.changxin.chapter2.BinderPool;

import android.os.RemoteException;

/**
 * Created by changxin on 2017/1/4.
 */

public class BinderPoolEncreIml extends BinderPoolEncre.Stub {

    @Override
    public String encrypt(String content) throws RemoteException {
        return content+"123";
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return password+"321";
    }
}
