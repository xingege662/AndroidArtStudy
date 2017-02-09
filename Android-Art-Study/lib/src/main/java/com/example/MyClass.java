package com.example;

public class MyClass {
    static {
        System.loadLibrary("jni-test");
    }

    public static void main(String args[]) {
        MyClass jniTest = new MyClass();
//        System.out.println(jniTest.get());
//        jniTest.set("hello world");
    }

//    public native String get();
//
//    public native void set(String str);
}
