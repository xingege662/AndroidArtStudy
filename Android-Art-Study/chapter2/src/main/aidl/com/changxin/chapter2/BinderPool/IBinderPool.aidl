// IBinderPool.aidl
package com.changxin.chapter2.BinderPool;

// Declare any non-default types here with import statements

interface IBinderPool {
   IBinder queryBinder(int binderCode);
}
