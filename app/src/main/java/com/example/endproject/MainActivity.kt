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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvGamesList: RecyclerView
    private lateinit var btnShow: FloatingActionButton

    private lateinit var dbHandler: DatabaseHandler
    private lateinit var m_gamesList: ArrayList<Game>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        dbHandler = DatabaseHandler(this)

        var cursor = contentResolver.query(Game.CONTENT_URI, arrayOf("_id", "name", "description", "genre", "developer", "year"), null, null, null)

        btnShow.setOnClickListener {
            Log.e("Position", "In OnClick")
            var gamesList: ArrayList<Game> = ArrayList()

            if (cursor?.moveToFirst()!!) {
                Log.e("Position", "Entered if statement")
                do {

                    val game = Game(
                        id = cursor.getInt(0),
                        name = cursor.getString(1),
                        descr = cursor.getString(2),
                        genre = cursor.getString(3),
                        developer = cursor.getString(4),
                        year = cursor.getInt(5)
                    )

                    gamesList.add(game)

                } while (cursor.moveToNext())

            }

            attachToRec(gamesList)

        }

    }

    private fun initView() {
        rvGamesList = findViewById(R.id.rvGames)
        btnShow = findViewById(R.id.fabLoad)

    }

    private fun attachToRec(games: ArrayList<Game>) {
        rvGamesList.layoutManager = LinearLayoutManager(this)

        val gameAdapter = GameAdapter(this, games)
        m_gamesList = games

        rvGamesList.adapter = gameAdapter
        gameAdapter.setOnGameClickListener(object: GameAdapter.onGameClickListener{
            override fun onGameClick(position: Int) {
                onGameClicked(position)

            }

        })

    }

    private fun onGameClicked(position: Int) {
        val id: Int = m_gamesList[position].id;
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)


    }

}