package com.changxin.chapter2.Service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.changxin.chapter2.Book;
import com.changxin.chapter2.IBookManager;
import com.changxin.chapter2.IOnNewBookArrivedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changxin on 2016/12/29.
 */

public class MyService extends Service {

    List<Book> books = new ArrayList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    IBookManager.Stub binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBook() throws RemoteException {

            Book book1 = new Book(1, "探索艺术");
            Book book2 = new Book(2, "群英传");
            books.add(book1);
            books.add(book2);
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {

        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

        }
    };
}
