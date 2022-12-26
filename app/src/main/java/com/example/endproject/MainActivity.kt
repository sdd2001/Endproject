package com.example.endproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.endproject.classes.DatabaseHandler
import com.example.endproject.classes.Game

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun getGamesList(): ArrayList<Game> {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val gamesList: ArrayList<Game> = databaseHandler.viewGames()

        return gamesList

    }

}