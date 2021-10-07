package com.fatah.purgegame.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fatah.presentation.models.Game
import com.fatah.purgegame.R

class GameListAdapter(
    private val listener: GameClickListener
): RecyclerView.Adapter<GameListAdapter.GameVH>() {

    private val gameList: MutableList<Game> = ArrayList()

    fun populate(games: List<Game>) {
        gameList.clear()
        gameList.addAll(games)
        notifyDataSetChanged()
    }

    inner class GameVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

            titleTextView.text = game.title

            itemView.setOnClickListener {
                listener.onGameTapped(game)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListAdapter.GameVH {
        val inflater = LayoutInflater.from(parent.context)
        return GameVH(
            inflater.inflate(R.layout.item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameListAdapter.GameVH, position: Int) =
        holder.bind(gameList[position])

    override fun getItemCount()= gameList.size

    interface GameClickListener {
        fun onGameTapped(game: Game)
    }
}