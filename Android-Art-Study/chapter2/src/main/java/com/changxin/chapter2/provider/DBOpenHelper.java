package com.changxin.chapter2.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by changxin on 2017/1/3.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private String dbName = "bookProvider.db";
    private String bookTable = "book";
    private String userTable = "user";
    private int DB_VERSION = 1;
    private String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXIST "+userTable+"(_id INTEGER PRIMARY KEY,"+"name TEXT)";
    private String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXIST " + bookTable + "(_id INTEGER PRIMARY KEY,"+"name TEXT)";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
