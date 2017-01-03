package com.changxin.chapter2.provider;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.changxin.chapter2.R;

public class ContentPorviderActivity extends AppCompatActivity {
    private Button btnProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_porvider);
        initView();

    }

    private void initView() {
        btnProvider = (Button) findViewById(R.id.btnProvider);
        btnProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.cx.bookContentProvider");
                getContentResolver().query(uri, null, null, null, null);
                getContentResolver().query(uri, null, null, null, null);
                getContentResolver().query(uri, null, null, null, null);
                getContentResolver().query(uri, null, null, null, null);
                getContentResolver().query(uri, null, null, null, null);
                getContentResolver().query(uri, null, null, null, null);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","changxin");
                getContentResolver().insert(uri,contentValues );
            }
        });
    }
}
