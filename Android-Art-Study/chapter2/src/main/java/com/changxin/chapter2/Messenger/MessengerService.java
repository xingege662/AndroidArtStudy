package com.changxin.chapter2.Messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.changxin.chapter2.Const.Const;



/**
 * Created by changxin on 2016/12/30.
 */

public class MessengerService extends Service {

    private String TAG = getClass().getSimpleName();

    private class MessengerHander extends  Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Const.MSG_FROM_CLIENT:
                    Log.d(TAG, "handleMessage: 获取到了客户端的数据了:"+msg.getData().getString("msg"));
                    Messenger messengerClient = msg.replyTo;
                    Message messge = Message.obtain(null, Const.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("msgservice","we received your request ,hold on ,please");
                    messge.setData(bundle);
                    try {
                        messengerClient.send(messge);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    Messenger messenger = new Messenger(new MessengerHander());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


}
