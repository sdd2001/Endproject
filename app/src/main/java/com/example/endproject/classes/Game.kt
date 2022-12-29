package com.example.endproject.classes

import android.net.Uri
import android.provider.BaseColumns
import com.example.endproject.GameProvider

class Game (val id: Int, val name: String, val descr: String, val genre: String, val developer: String, val year: Int) : BaseColumns {
    companion object {
        val CONTENT_URI: Uri = GameProvider.BASE_CONTENT_URI.buildUpon().appendPath(GameProvider.GAME_PATH).build()

    }

}