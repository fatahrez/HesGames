package com.fatah.hesgames.di

import android.app.Application
import com.fatah.data.models.GameData
import com.fatah.data.models.ScreenshotData
import com.fatah.data.repository.LocalDataSource
import com.fatah.local.database.HesGamesDB
import com.fatah.local.mappers.GameDataLocalMapper
import com.fatah.local.mappers.Mapper
import com.fatah.local.mappers.ScreenshotDataLocalMapper
import com.fatah.local.models.GameLocal
import com.fatah.local.models.ScreenshotLocal
import com.fatah.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders{
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource

        @Binds
        fun bindsGameMapper(
            gameDataLocalMapper: GameDataLocalMapper
        ): Mapper<GameData, GameLocal>

        @Binds
        fun bindsScreenshotMapper(
            screenshotDataLocalMapper: ScreenshotDataLocalMapper
        ): Mapper<ScreenshotData, ScreenshotLocal>
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = HesGamesDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesGameDao(
        hesGamesDB: HesGamesDB
    ) = hesGamesDB.gameDao()

    @Provides
    @Singleton
    fun providesScreenshotDao(
        hesGamesDB: HesGamesDB
    ) = hesGamesDB.screenshotDao()
}