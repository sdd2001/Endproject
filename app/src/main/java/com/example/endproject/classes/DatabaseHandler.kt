package com.example.endproject.classes

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
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

    @SuppressLint("Range")
    fun viewGames(): ArrayList<Game> {
        val gamesList: ArrayList<Game> = ArrayList<Game>()

        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try  {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()

        }

        var id: Int
        var name: String
        var descr: String
        var genre: String
        var developer: String
        var year: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                descr = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                genre = cursor.getString(cursor.getColumnIndex(KEY_GENRE))
                developer = cursor.getString(cursor.getColumnIndex(KEY_DEVELOPER))
                year = cursor.getInt(cursor.getColumnIndex(KEY_YEAR))

            } while (cursor.moveToNext())

        }

        return gamesList

    }

}