package com.example.ch17_storage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "mydb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create Table my_table (_id integer primary key autoincrement, todo not null)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}