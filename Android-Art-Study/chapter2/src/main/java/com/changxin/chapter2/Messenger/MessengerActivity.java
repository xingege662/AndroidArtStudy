package com.changxin.chapter2.Messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.changxin.chapter2.Const.Const;
import com.changxin.chapter2.R;

public class MessengerActivity extends AppCompatActivity {
    private Button mButtonMessenger;
    private Intent mIntent;
    Messenger mService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService  = new Messenger(service);
            Message mMessage = Message.obtain(null, Const.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "this is Client Infomation");
            mMessage.setData(bundle);
            try {
                mService.send(mMessage);
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
        setContentView(R.layout.activity_messenger);
        initView();
    }

    private void initView() {

        mButtonMessenger = (Button) findViewById(R.id.btnMessenger);
        mButtonMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MessengerActivity.this, MessengerService.class);
                bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);

    }
}
