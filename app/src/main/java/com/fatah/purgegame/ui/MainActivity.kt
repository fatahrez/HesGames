package com.fatah.purgegame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fatah.presentation.factory.ViewModelFactory
import com.fatah.presentation.models.Status
import com.fatah.presentation.viewmodels.GameViewModel
import com.fatah.purgegame.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
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

        gameViewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)

        gameViewModel.gameLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.i(TAG, "onCreate: Loading")
                }
                Status.ERROR -> {
                    Log.e(TAG, "onCreate: ${it.message}")
                }
                Status.SUCCESS -> {
                    Log.i(TAG, "onCreate: ${it.data}")
                }
            }
        })
    }
}