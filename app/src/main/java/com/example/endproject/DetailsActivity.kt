package com.example.endproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.endproject.classes.DatabaseHandler
import com.example.endproject.classes.Game

class DetailsActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvDev: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvYear: TextView

    private lateinit var dbHandler: DatabaseHandler
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        val extras = intent.extras

        initView()
        dbHandler = DatabaseHandler(this)

        if (extras != null) {
            val id = extras.getInt("id")
            game = dbHandler.getGameById(id)

            tvName.text = game.name
            tvDesc.text = game.descr
            tvDev.text = game.developer
            tvGenre.text = game.genre
            tvYear.text = game.year.toString()

        }

    }

    private fun initView() {
        tvName = findViewById(R.id.tvDetailsName)
        tvDesc = findViewById(R.id.tvDetailsDescr)
        tvDev = findViewById(R.id.tvDetailsDeveloper)
        tvGenre = findViewById(R.id.tvDetailsGenre)
        tvYear = findViewById(R.id.tvDetailsYear)

    }

}