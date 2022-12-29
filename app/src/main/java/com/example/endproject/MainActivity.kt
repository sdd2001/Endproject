package com.example.endproject

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endproject.adapters.GameAdapter
import com.example.endproject.classes.DatabaseHandler
import com.example.endproject.classes.Game

class MainActivity : AppCompatActivity() {
    private lateinit var rvGamesList: RecyclerView
    private lateinit var btnShow: Button

    private lateinit var dbHandler: DatabaseHandler
    private lateinit var m_gamesList: ArrayList<Game>

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
        m_gamesList = dbHandler.getAllGames()
        Log.e("Amount of games", "${ m_gamesList.size }")

        return m_gamesList

    }

    private fun attachToRec() {
        rvGamesList.layoutManager = LinearLayoutManager(this)

        val gameAdapter = GameAdapter(this, getGamesList())

        rvGamesList.adapter = gameAdapter
        gameAdapter.setOnGameClickListener(object: GameAdapter.onGameClickListener{
            override fun onGameClick(position: Int) {
                onGameClicked(position)

            }

        })

    }

    private fun onGameClicked(position: Int) {
        val id: Int = m_gamesList.get(position).id;
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)


    }

}