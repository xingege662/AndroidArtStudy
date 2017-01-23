package com.voole.chapter7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btn;
    private Intent intent;
    private String[] data = {"abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd",
            "abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd",
            "abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd","abcd"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                //设置动画效果
                overridePendingTransition(R.anim.anim_view_anim,R.anim.anim_view_exit);
            }
        });
    }
}
