package com.example.endproject.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.endproject.R
import com.example.endproject.classes.Game

class GameAdapter (private val context: Context, private val games: ArrayList<Game>) :
RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.game_row, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]

        holder.tvName.text = game.name
        holder.tvYear.text = game.year.toString()

    }

    override fun getItemCount(): Int {
        return games.size

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvYear: TextView = view.findViewById(R.id.tvYear)

    }

}