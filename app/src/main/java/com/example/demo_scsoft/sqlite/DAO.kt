package com.example.demo_scsoft.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DAO(context: Context) {
    var sql = SQLiteDB(context)
    lateinit var db: SQLiteDatabase
    fun addString(string: String): Int {
        db = sql.writableDatabase
        var values: ContentValues = ContentValues()
        values.put("STRING", string)
        var result: Long = db.insert("SEARCHHISTORY", null, values)
        db.close()
        if (result > 0) {
            return 1
        }
        return 0
    }

    fun getAll(): List<String> {
        var list: MutableList<String> = ArrayList()
        db = sql.writableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM SEARCHHISTORY", null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            var s: String = cursor.getString(0)
            list.add(s)
            cursor.moveToNext()
        }
        db.close()
        return list
    }

    fun delete(string: String){
        db = sql.writableDatabase
        db.delete("SEARCHHISTORY","STRING=?", arrayOf(string))
    }
}