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
    private lateinit var m_listener: onGameClickListener

    interface onGameClickListener {
        fun onGameClick(position: Int)

    }

    fun setOnGameClickListener(listener: onGameClickListener) {
        m_listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_row, parent, false)

        return ViewHolder(view, m_listener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]

        holder.tvName.text = game.name
        holder.tvYear.text = game.year.toString()

    }

    override fun getItemCount(): Int {
        return games.size

    }

    class ViewHolder(view: View, listener: onGameClickListener) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvYear: TextView = view.findViewById(R.id.tvYear)

        init {
            view.setOnClickListener {
                listener.onGameClick(adapterPosition)

            }

        }

    }

}