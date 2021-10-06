package com.fatah.purgegame.di

import com.fatah.data.mappers.GameDomainDataMapper
import com.fatah.data.mappers.Mapper
import com.fatah.data.models.GameData
import com.fatah.data.repository.PurgeGameRepositoryImpl
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.repository.PurgeGameRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindsRepository(
        purgeGameRepositoryImpl: PurgeGameRepositoryImpl
    ): PurgeGameRepository

    @Binds
    abstract fun bindsGameMapper(
        gameDomainDataMapper: GameDomainDataMapper
    ): Mapper<GameEntity, GameData>
}