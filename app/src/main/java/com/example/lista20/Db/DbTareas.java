package com.example.lista20.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.lista20.entidades.tareas;

import java.util.ArrayList;

public class DbTareas extends Dbhelper {

    Context context;

    public DbTareas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarTarea(String tarea) {
        long id = 0;
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tarea", tarea);

            id = db.insert(TABLE_AGENDA, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<tareas> mostrasTareas() {
        SQLiteDatabase db = getWritableDatabase();

        ArrayList<tareas> listaTareas = new ArrayList<>();
        tareas Tareas = null;
        Cursor cursorTareas = null;

        cursorTareas = db.rawQuery("SELECT * FROM " + TABLE_AGENDA, null);
        if (cursorTareas.moveToFirst()) {
            do {
                Tareas = new tareas();
                Tareas.setId(cursorTareas.getInt(0));
                Tareas.setTarea(cursorTareas.getString(1));
                listaTareas.add(Tareas);
            } while (cursorTareas.moveToNext());
        }
        cursorTareas.close();

        return listaTareas;
    }

    public void eliminarTarea(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_AGENDA, whereClause, whereArgs);
    }
}
