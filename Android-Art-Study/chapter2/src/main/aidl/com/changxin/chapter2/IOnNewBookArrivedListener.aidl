// IOnNewBookArrivedListener.aidl
package com.changxin.chapter2;

// Declare any non-default types here with import statements
import com.changxin.chapter2.Book;

interface IOnNewBookArrivedListener {
     void onNewBookArrived(in Book newBook);
}
