package com.fatah.hesgames.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fatah.presentation.factory.ViewModelFactory
import com.fatah.presentation.models.Game
import com.fatah.presentation.models.Status
import com.fatah.presentation.viewmodels.GameViewModel
import com.fatah.hesgames.R
import com.fatah.hesgames.ui.settings.SettingsActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    companion object{
        private const val TAG = "MainActivity"
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView: GridView = findViewById(R.id.gridView)
//        val posterImageView: ImageView = findViewById(R.id.posterImageView)

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

//                        posterImageView.load(games[0].thumbnail)
//                        posterImageView.layoutParams.height = windowManager
//                            .defaultDisplay.height/3
                        val gameAdapter = GameListAdapter(this@MainActivity, games)
                        gridView.adapter = gameAdapter

                        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                            val intent = Intent(this, GameDetailActivity::class.java)
                            intent.putExtra("game_id", games[position].id)
                            startActivity(intent)
                        }
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val settingIntent = Intent(this, SettingsActivity::class.java)
                startActivity(settingIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}