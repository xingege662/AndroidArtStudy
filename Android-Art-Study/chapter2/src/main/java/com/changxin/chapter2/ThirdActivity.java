package com.changxin.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ThirdActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mIntent =getIntent();
        Bundle bundle = mIntent.getBundleExtra("name");
        Log.d(TAG, "onCreate: "+bundle.getString("hello"));
    }
}
