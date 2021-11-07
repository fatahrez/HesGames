package com.fatah.hesgames.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.fatah.presentation.factory.ViewModelFactory
import com.fatah.presentation.models.Status
import com.fatah.presentation.viewmodels.IndividualGameViewModel
import com.fatah.hesgames.R
import dagger.android.AndroidInjection
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GameDetailActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "GameDetailActivity"
    }
    
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    
    private lateinit var individualGameViewModel: IndividualGameViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        val individualGameImageView: ImageView = findViewById(R.id.individualGameImageView)
        val individualTitleTextView: TextView = findViewById(R.id.IndividualTitleTextView)
        val platformTextView: TextView = findViewById(R.id.platformTextView)
        val descriptionTextView: TextView = findViewById(R.id.shortDescriptionTextView)

        val screenshot1ImageView: ImageView = findViewById(R.id.screenshot1ImageView)
        val screenshot2ImageView: ImageView = findViewById(R.id.screenshot2ImageView)
        val screenshot3ImageView: ImageView = findViewById(R.id.screenshot3ImageView)

        individualGameViewModel = ViewModelProvider(this, viewModelFactory)
            .get(IndividualGameViewModel::class.java)
        
        individualGameViewModel.getIndividualGame(intent.getIntExtra("game_id", 1)).observe(
            this, Observer { 
                when(it.status) {
                    Status.LOADING -> {
                        Log.i(TAG, "onCreate: loading ...")
                    }
                    Status.ERROR -> {
                        Log.e(TAG, "onCreate: Error ${it.message}")
                    }
                    Status.SUCCESS -> {
                        val data = it.data!!

                        individualGameImageView.layoutParams.height = windowManager
                            .defaultDisplay.height/3
                        individualGameImageView.load(data.thumbnail)

                        individualTitleTextView.text = data.title
                        platformTextView.text = data.platform

                        screenshot1ImageView.layoutParams.width = windowManager
                            .defaultDisplay.width/3
                        screenshot1ImageView.layoutParams.height = windowManager
                            .defaultDisplay.height/4

                        screenshot2ImageView.layoutParams.width = windowManager
                            .defaultDisplay.width/3
                        screenshot2ImageView.layoutParams.height = windowManager
                            .defaultDisplay.height/4

                        screenshot3ImageView.layoutParams.width = windowManager
                            .defaultDisplay.width/3
                        screenshot3ImageView.layoutParams.height = windowManager
                            .defaultDisplay.height/4

                        screenshot1ImageView.load(data.screenshots?.get(0)?.image)
                        screenshot2ImageView.load(data.screenshots?.get(1)?.image)
                        screenshot3ImageView.load(data.screenshots?.get(2)?.image)

                        descriptionTextView.text = data.description
                    }
                }
            }
        )
        
        
    }
}