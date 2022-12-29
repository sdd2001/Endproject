package com.example.endproject

import android.content.ContentUris
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
            game = Game(0, "Not Found", "Not Found", "Not Found", "Not Found", 2022)
            val id = extras.getInt("id")
            val singleUri: Uri = ContentUris.withAppendedId(Game.CONTENT_URI, id.toLong())
            var cursor = contentResolver.query(singleUri, arrayOf("_id", "name", "description", "genre", "developer", "year"), null, null, null)

            if (cursor?.moveToFirst()!!) {
                game = Game(
                    id = cursor.getInt(0),
                    name = cursor.getString(1),
                    descr = cursor.getString(2),
                    genre = cursor.getString(3),
                    developer = cursor.getString(4),
                    year = cursor.getInt(5)
                )

            }

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