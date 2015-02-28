package com.zy.frame.net.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/2/28.
 */
public class CacheSqliteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cache.db";
    private static final int DATABASE_VERSION = 1;

    public CacheSqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
