package com.example.demo_scsoft.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, "db", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE SEARCHHISTORY(STRING TEXT PRIMARY KEY)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS SEARCHHISTORY")
        onCreate(db)
    }
}