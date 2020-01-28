package com.example.locdvd.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME = "locDVD.db";
    static int DB_VERSION = 1;

    public LocalSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFileTable = "CREATE TABLE DVD(id INTEGER PRIMARY KEY, titre TEXT , annee NUMERIC , acteurs TEXT , resume TEXT)";
        db.execSQL(sqlFileTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
