package com.example.endproject.classes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "GamesDatabase"

        private val TABLE_CONTACTS = "GamesTable"

        private val KEY_ID = "_id"
        private val KEY_NAME = "name"
        private val KEY_DESCRIPTION = "description"
        private val KEY_GENRE = "genre"
        private val KEY_DEVELOPER = "developer"
        private val KEY_YEAR = "year"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_GENRE + " TEXT,"
                + KEY_DEVELOPER + " TEXT,"
                + KEY_YEAR + " INTEGER" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db);

    }

}