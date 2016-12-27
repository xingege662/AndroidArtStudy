package com.changxin.android_art_study.Chapter1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changxin.android_art_study.R;

public class MainActivity extends Activity {
    private String TAG = this.getClass().getSimpleName();
    private Button mButtonSkip;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        initView();
    }

    private void initView() {
        mButtonSkip = (Button) findViewById(R.id.btn);
        mButtonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(MainActivity.this, SecondActivity.class);
                /**
                 * Activity的启动过程：Activity---->startActivity(Intent intent)----->startActivity(Intent intent, @Nullable Bundle options)
                 * ------------->startActivityForResult(@RequiresPermission Intent intent, int requestCode,
                 @Nullable Bundle options)--------------->mInstrumentation.execStartActivity(
                 Context who, IBinder contextThread, IBinder token, Activity target,
                 Intent intent, int requestCode, Bundle options)---------->ActivityManagerNative.getDefault()
                 .startActivitys




                 真正处理在ActivityThread中： handleLaunchActivity(ActivityClientRecord r, Intent customIntent, String reason) ----------》
                 performLaunchActivity(ActivityClientRecord r, Intent customIntent)
                 */
                startActivity(mIntent);
            }
        });
    }

    /**
     * onStart()和onStop()是从Acitivity是否可见的角度来回调的
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    /**
     *onResume()和onPause()是从Activity是否在前台来回调的，除了这两个，其他也没什么区别
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    /**
     *当A跳转到B的时候A会先pause，不能做大数据的缓存操作，因为后面的Activity还要启动
     *
     * 在AcitivtyStack中的resumeTopActivityInnerLocked（）方法中有这么一句注释
     * // We need to start pausing the current activity so the top one can be resumed...
     * 说明会先puase
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    /**
     * 主席讲Actiivity换成透明主题的时候这个方法并不会调用，但是自己试了一下，可以用
     *
     * 不能做大数据的存储
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    /**
     * 在onPause()之前或者之后调用，两者并没有绝对的关系
     *  当Acitivity的配置变化的时候就会被重建，这里可以通过configChanges来配置消除一些配置变化时Acitivty的重建
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    /**
     * 在onStart()之前调用
     *
     *在onCreate()和onRestoreInstanceState()中都是可以恢复的，但是两者不同的是：
     * onRestoreInstanceState():当它调用的时候savedInstanceState是一定有值的
     * onCreate()：当用onCreate()恢复的时候savedInstanceState不一定有值，使用之前一定要判断
     *
     * 官方文档推荐用onRestoreInstanceState（）
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    /**
     * 如果要启动的Acitivity在栈顶，并且设置了singleTop，那么再次启动这个Acitivty的时候，就会回调这个方法，
     * onCreate()和onStart()不会回调。
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}
