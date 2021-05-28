package com.example.demo_scsoft.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DAO(context: Context) {
    var sql = SQLiteDB(context)
    lateinit var db: SQLiteDatabase
    fun addString(string: String): Long {
        db = sql.writableDatabase
        var values : ContentValues = ContentValues()
        values.put("STRING", string)
        var result : Long = db.insert("SEARCHHISTORY", null, values)
        db.close()
        if (result > 0) {
            return 1
        }
        return 0
    }

    fun getAll(): List<String> {
        var list = mutableListOf<String>()
        db = sql.readableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM SEARCHHISTORY", null)
        cursor.moveToFirst()
        while (cursor.isAfterLast) {
            list.add(cursor.getString(1))
            cursor.moveToNext()
        }
        return list
    }
}