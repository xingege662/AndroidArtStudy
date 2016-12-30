package com.changxin.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtn;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MainActivity.this, ThirdActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString("hello","常鑫");
            mIntent.putExtra("name", mBundle);
            startActivity(mIntent);
        }
        });
    }
}
