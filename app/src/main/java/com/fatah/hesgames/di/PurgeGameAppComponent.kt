package com.fatah.hesgames.di

import android.app.Application
import com.fatah.hesgames.application.PurgeGameApp
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
interface PurgeGameAppComponent: AndroidInjector<PurgeGameApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build():PurgeGameAppComponent
    }

    override fun inject(instance: PurgeGameApp?)
}