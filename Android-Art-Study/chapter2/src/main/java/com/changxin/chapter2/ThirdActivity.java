package com.changxin.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changxin.chapter2.Messenger.MessengerActivity;

public class ThirdActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private Intent mIntent;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mIntent =getIntent();
        Bundle bundle = mIntent.getBundleExtra("name");
        Log.d(TAG, "onCreate: "+bundle.getString("hello"));
        initView();
    }

    private void initView() {

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MessengerActivity.class);
                startActivity(intent);
            }
        });
    }
}
