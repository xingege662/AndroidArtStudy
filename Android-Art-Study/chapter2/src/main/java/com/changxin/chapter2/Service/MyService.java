package com.changxin.chapter2.Service;


import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.changxin.chapter2.Book;
import com.changxin.chapter2.IBookManager;
import com.changxin.chapter2.IOnNewBookArrivedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by changxin on 2016/12/29.
 */

public class MyService extends Service {

    private String TAG = getClass().getSimpleName();
    List<Book> books = new ArrayList<>();
    //    CopyOnWriteArrayList<IOnNewBookArrivedListener> listeners = new CopyOnWriteArrayList<>();
    RemoteCallbackList<IOnNewBookArrivedListener> remoteCallbackList = new RemoteCallbackList<IOnNewBookArrivedListener>();
    private AtomicBoolean isServiceDestroyed = new AtomicBoolean(false);


    @Override
    public void onCreate() {
        super.onCreate();
        Book book1 = new Book(1, "探索艺术");
        Book book2 = new Book(2, "群英传");
        books.add(book1);
        books.add(book2);
        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int checkCallingOrSelfPermission = checkCallingOrSelfPermission("com.cx.aidl.chapter2");
        if(checkCallingOrSelfPermission == PackageManager.PERMISSION_DENIED){
            return null;
        }
        return binder;
    }


    IBookManager.Stub binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBook() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            boolean register = remoteCallbackList.register(listener);
            if(register){
                Log.d(TAG, "registerListener: registerListener is Success");
            }else{
                Log.d(TAG, "registerListener: registerListener is faild");
            }
            int N = remoteCallbackList.beginBroadcast();
            remoteCallbackList.finishBroadcast();
            Log.d(TAG, "registerListener: current size is "+N);
//            if (!listeners.contains(listener)) {
//                listeners.add(listener);
//                Log.d(TAG, "registerListener: "+"add successed");
//            }else{
//                Log.d(TAG, "registerListener: "+"already exist");
//            Log.d(TAG, "registerListener: registetSize"+ listeners.size());
//        }
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

//            if (listeners.contains(listener)) {
//                listeners.remove(listener);
//                Log.d(TAG, "unregisterListener:remove sucessed");
//            }else{
//                Log.d(TAG, "unregisterListener: can not remove");
//            }
//            Log.d(TAG, "unregisterListener: "+listeners.size());
            boolean unregister = remoteCallbackList.unregister(listener);
            if(unregister){
                Log.d(TAG, "unregisterListener: unregisterListener is success");
            }else{
                Log.d(TAG, "unregisterListener: unregisterListener is failed");
            }
            int N = remoteCallbackList.beginBroadcast();
            remoteCallbackList.finishBroadcast();
            Log.d(TAG, "registerListener: current size is "+N);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceDestroyed.set(true);
    }

    /**
     * 新书到达以后添加通知
     * @param book
     * @throws RemoteException
     */
    private void onNewBookArrived(Book book) throws RemoteException {
        books.add(book);
        /**
         * beginBroadcast()和finishBroadcast()必须配对使用
         */
        int beginBroadcast = remoteCallbackList.beginBroadcast();
        for(int i = 0 ; i < beginBroadcast;i++){
            IOnNewBookArrivedListener l = remoteCallbackList.getBroadcastItem(i);
            if(l != null){
                l.onNewBookArrived(book);
            }
        }
        remoteCallbackList.finishBroadcast();
//        Log.d(TAG, "onNewBookArrived: books's size is :" + books.size());
//        for(int i = 0 ; i < listeners.size(); i++){
//            IOnNewBookArrivedListener listener = listeners.get(i);
//            Log.d(TAG, "onNewBookArrived: the new book arrived " + listener);
//            listener.onNewBookArrived(book);
//        }
    }

    private class ServiceWorker implements Runnable{

        @Override
        public void run() {
            while(!isServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = books.size() + 1;
                Book book = new Book(bookId, "new Book" + bookId);

                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
