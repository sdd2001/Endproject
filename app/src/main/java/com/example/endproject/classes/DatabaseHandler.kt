package com.example.endproject.classes

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "GamesDatabase.db"

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

    fun getGameById(id: Int): Game {
        var game: Game = Game(0, "", "", "", "", 0)

        val query = "SELECT * FROM $TABLE_CONTACTS WHERE $KEY_ID = $id"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            return game

        }

        var _id: Int
        var name: String
        var descr: String
        var genre: String
        var developer: String
        var year: Int

        if (cursor.moveToFirst()) {
            _id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID))
            name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
            descr = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION))
            genre = cursor.getString(cursor.getColumnIndexOrThrow(KEY_GENRE))
            developer = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DEVELOPER))
            year = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_YEAR))

            game = Game(_id, name, descr, genre, developer, year)

        }

        cursor.close()
        db.close()

        return game

    }

    fun getAllGames(): ArrayList<Game> {
        Log.e("Position", "Entered getAllGames() function")

        val gamesList: ArrayList<Game> = ArrayList()
        val query = "SELECT * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
            Log.e("Position", "In try block")

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            Log.e("Position", "In catch block")
            return ArrayList()

        }

        var id: Int
        var name: String
        var descr: String
        var genre: String
        var developer: String
        var year: Int

        if (cursor.moveToFirst()) {
            Log.e("Position", "Entered if statement")
            do {
                Log.e("Position", "Entered do statement")
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID))
                name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                descr = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION))
                genre = cursor.getString(cursor.getColumnIndexOrThrow(KEY_GENRE))
                developer = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DEVELOPER))
                year = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_YEAR))

                val game = Game(
                    id = id,
                    name = name,
                    descr = descr,
                    genre = genre,
                    developer = developer,
                    year = year
                )

                gamesList.add(game)
                Log.e("Position", "End of do statement")

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return gamesList

    }

}