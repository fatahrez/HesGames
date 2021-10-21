package com.fatah.hesgames.di

import android.app.Application
import com.fatah.hesgames.application.HesGamesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DomainModule::class,
        DataModule::class,
        LocalPersistenceModule::class,
        RemoteModule::class,
        PresentationModule::class,
        AppModule::class
    ]
)
interface HesGamesAppComponent: AndroidInjector<HesGamesApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build():HesGamesAppComponent
    }

    override fun inject(instance: HesGamesApp?)
}