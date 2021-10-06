package com.fatah.purgegame.di

import android.app.Application
import com.fatah.data.models.GameData
import com.fatah.data.repository.LocalDataSource
import com.fatah.local.database.PurgeGameDB
import com.fatah.local.mappers.GameDataLocalMapper
import com.fatah.local.mappers.Mapper
import com.fatah.local.models.GameLocal
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
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = PurgeGameDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesGameDao(
        purgeGameDB: PurgeGameDB
    ) = purgeGameDB.gameDao()
}