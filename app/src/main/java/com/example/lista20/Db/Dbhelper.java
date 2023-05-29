package com.example.lista20.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="agenda.db";
    static final String TABLE_AGENDA="t_agenda";

    public Dbhelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_AGENDA + "(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tarea TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(("DROP TABLE " + TABLE_AGENDA));
        onCreate((db));

    }
}
