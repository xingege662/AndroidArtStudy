package com.changxin.chapter2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManager.Stub.asInterface(service);
            try {
                books = iBookManager.getBook();
                Log.d(TAG, "onServiceConnected: "+books.toString());
                Book book = new Book(3, "算法");
                iBookManager.addBook(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

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
}
