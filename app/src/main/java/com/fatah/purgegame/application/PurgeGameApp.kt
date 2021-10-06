package com.fatah.purgegame.application

import com.fatah.purgegame.di.DaggerPurgeGameAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PurgeGameApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerPurgeGameAppComponent.builder().application(this).build()
    }
}