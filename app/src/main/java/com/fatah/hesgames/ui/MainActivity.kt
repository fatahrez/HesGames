package com.fatah.hesgames.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatah.presentation.factory.ViewModelFactory
import com.fatah.presentation.models.Game
import com.fatah.presentation.models.Status
import com.fatah.presentation.viewmodels.GameViewModel
import com.fatah.hesgames.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    GameListAdapter.GameClickListener{
    companion object{
        private const val TAG = "MainActivity"
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var gameViewModel: GameViewModel

    private val gameListAdapter = GameListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameRecyclerView: RecyclerView = findViewById(R.id.gameRecyclerView)

        gameRecyclerView.layoutManager = LinearLayoutManager(this)
        gameRecyclerView.adapter = gameListAdapter

        gameViewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)

        gameViewModel.gameLiveData.observe(this, Observer{
            when (it.status) {
                Status.LOADING -> {
                    Log.i(TAG, "onCreate: Loading")
                }
                Status.ERROR -> {
                    Log.e(TAG, "onCreate: ${it.message}")
                }
                Status.SUCCESS -> {
                    Log.i(TAG, "onCreate: success ${it.data}")
                    Log.i(TAG, "onCreate: $it")
                    it.data?.let {games ->
//                        Log.i(TAG, "onCreate: games ${games.toString()}")
                        gameListAdapter.populate(games)
                    }
                }
            }
        })
    }

    override fun onGameTapped(game: Game) {
        val intent = Intent(this, GameDetailActivity::class.java)
        intent.putExtra("game_id", game.id)
        startActivity(intent)
    }
}