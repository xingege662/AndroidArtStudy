// BinderPoolEncre.aidl
package com.changxin.chapter2.BinderPool;

// Declare any non-default types here with import statements

interface BinderPoolEncre {
    String encrypt(String content);
    String decrypt(String password);
}
