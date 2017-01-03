package com.changxin.chapter2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changxin.chapter2.Service.MyService;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private Button mbtn2;
    private Intent mIntent;
    private IBookManager iBookManager;
    private List<Book> books = new ArrayList<Book>();
    private final int  MESSAGE_ARRIVED = 1;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_ARRIVED:
                    try {
                     int a  = iBookManager.getBook().size();
                        Log.d(TAG, "handleMessage: "+msg.obj+"books size is :" + a);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                iBookManager.asBinder().linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            iBookManager = IBookManager.Stub.asInterface(service);
            try {
                Book book = new Book(3, "算法");
                iBookManager.addBook(book);
                books = iBookManager.getBook();
                Log.d(TAG, "onServiceConnected: "+books.toString());
                iBookManager.registerListener(iOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIntent = new Intent(SecondActivity.this, MyService.class);
            bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    };

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binder died. tname:" + Thread.currentThread().getName());
            if (iBookManager == null)
                return;
            iBookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            iBookManager = null;
            //这里重新绑定service
            mIntent = new Intent(SecondActivity.this, MyService.class);
            bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mbtn2 = (Button) findViewById(R.id.btn2);
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 跳转验证进程
                 */
//                mIntent = new Intent(SecondActivity.this, ThirdActivity.class);
//                startActivity(mIntent);

                mIntent = new Intent(SecondActivity.this, MyService.class);

                bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    private IOnNewBookArrivedListener iOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub(){

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_ARRIVED,newBook).sendToTarget();
        }
    };

    @Override
    protected void onDestroy() {

        if(iBookManager!=null && iBookManager.asBinder().isBinderAlive()){
                try {

                iBookManager.unregisterListener(iOnNewBookArrivedListener);
                Log.d(TAG, "onDestroy: unregisterLister and books size is "+books.size());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
