package com.fatah.hesgames.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fatah.presentation.models.Game
import com.fatah.hesgames.R

class GameListAdapter(
    private val context: Context,
    private val gameList: List<Game>
): BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return gameList.size
    }

    override fun getItem(position: Int): Any {
        return gameList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null ) {
            convertView = layoutInflater!!.inflate(R.layout.item_game, null)
        }
        val titleTextView: TextView = convertView!!.findViewById(R.id.IndividualTitleTextView)
        val thumbnailImageView: ImageView = convertView!!.findViewById(R.id.thumbnailImageView)
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        thumbnailImageView.layoutParams.height = windowManager
            .defaultDisplay.height/5
//        val shortDescriptionTextView: TextView = convertView!!.findViewById(R.id.shortDescriptionTextView)

        titleTextView.text = gameList[position].title
        thumbnailImageView.load(gameList[position].thumbnail)
//        shortDescriptionTextView.text = gameList[position].shortDescription

        return convertView
    }
}