package com.changxin.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class SecondActivity extends AppCompatActivity {
    private Button mbtn2;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mbtn2 = (Button) findViewById(R.id.btn2);
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
