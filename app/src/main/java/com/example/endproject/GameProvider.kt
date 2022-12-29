package com.example.endproject

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.endproject.classes.DatabaseHandler

class GameProvider : ContentProvider() {
    companion object {
        val AUTHORITY = "com.example.endproject"
        val BASE_CONTENT_URI: Uri = Uri.parse("content://" + AUTHORITY)
        val GAME_PATH = "GamesTable"
        val GAMES = 1
        val GAME_ID = 2

    }

    lateinit var db: SQLiteDatabase
    lateinit var dbHandler: DatabaseHandler

    fun buildUriMatcher(): UriMatcher {
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        uriMatcher.addURI(AUTHORITY, GAME_PATH, GAMES)
        uriMatcher.addURI(AUTHORITY, GAME_PATH + "/#", GAME_ID)

        return uriMatcher

    }

    override fun onCreate(): Boolean {
        val context = context
        dbHandler = DatabaseHandler(context)
        db = dbHandler.readableDatabase

        return true

    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("GamesTable", null, cv)
        context?.contentResolver?.notifyChange(uri, null)

        return uri

    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.update("GamesTable", cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)

        return count

    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete("GamesTable", condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)

        return count
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        val uriMatcher = buildUriMatcher()
        var lOrder: String = order ?: ""
        var lCondition: String = condition ?: ""

        when (uriMatcher.match(uri)) {
            GAMES -> {
                if (lOrder.isEmpty()) lOrder = "_id ASC"

            } GAME_ID -> lCondition += "_id ${uri?.lastPathSegment}"
            else -> lOrder = "_id ASC"

        }

        return db.query("GamesTable", cols, lCondition, condition_val, null, null, lOrder)

    }

    override fun getType(uri: Uri): String? {
        val uriMatcher = buildUriMatcher()
        var type: String = "vnd.android.cursor."

        when (uriMatcher.match(uri)) {
            GAMES -> type += "dir/vnd.com.example.endproject.GamesTable"
            GAME_ID -> type += "item/vnd.com.example.endproject.GamesTable"

        }

        return type

    }

}