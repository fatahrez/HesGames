package com.fatah.hesgames.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
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
        
        individualGameViewModel = ViewModelProvider(this, viewModelFactory)
            .get(IndividualGameViewModel::class.java)
        
        individualGameViewModel.getIndividualGame(intent.getIntExtra("game_id", 1)).observe(
            this, Observer { 
                when(it.status) {
                    Status.LOADING -> {
                        Log.i(TAG, "onCreate: loading ...")
                    }
                    Status.ERROR -> {
                        Log.e(TAG, "onCreate: Error ${it.message}", 
                            throw IllegalArgumentException(it.message)
                        )
                    }
                    Status.SUCCESS -> {

                        individualGameImageView.layoutParams.height = windowManager
                            .defaultDisplay.height/3
                        individualGameImageView.load(it.data?.thumbnail)

                    }
                }
            }
        )
        
        
    }
}