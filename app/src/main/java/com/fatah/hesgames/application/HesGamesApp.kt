package com.fatah.hesgames.application

import com.fatah.hesgames.di.DaggerHesGamesAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class HesGamesApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerHesGamesAppComponent.builder().application(this).build()
    }
}