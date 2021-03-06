package com.fatah.hesgames.di

import android.app.Application
import android.content.Context
import com.fatah.hesgames.ui.GameDetailActivity
import com.fatah.hesgames.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal  abstract fun contributesGameDetailActivity(): GameDetailActivity
}