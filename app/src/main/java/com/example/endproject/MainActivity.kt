package com.example.endproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endproject.adapters.GameAdapter
import com.example.endproject.classes.DatabaseHandler
import com.example.endproject.classes.Game

class MainActivity : AppCompatActivity() {
    private lateinit var rvGamesList: RecyclerView
    private lateinit var btnShow: Button

    private lateinit var dbHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        dbHandler = DatabaseHandler(this)

        btnShow.setOnClickListener { attachToRec() }


    }

    private fun initView() {
        rvGamesList = findViewById(R.id.rvGames)
        btnShow = findViewById(R.id.btnShow)

    }

    private fun getGamesList(): ArrayList<Game> {
        val games = dbHandler.getAllGames()
        Log.e("Amount of games", "${ games.size }")

        return games

    }

    private fun attachToRec() {
        rvGamesList.layoutManager = LinearLayoutManager(this)
        val gameAdapter = GameAdapter(this, getGamesList())
        rvGamesList.adapter = gameAdapter

    }

}