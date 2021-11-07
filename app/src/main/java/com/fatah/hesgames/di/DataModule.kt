package com.fatah.hesgames.di

import com.fatah.data.mappers.GameDomainDataMapper
import com.fatah.data.mappers.Mapper
import com.fatah.data.mappers.ScreenshotDomainDataMapper
import com.fatah.data.models.GameData
import com.fatah.data.models.ScreenshotData
import com.fatah.data.repository.HesGamesRepositoryImpl
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.entities.ScreenshotEntity
import com.fatah.domain.repository.HesGamesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindsRepository(
        purgeGameRepositoryImpl: HesGamesRepositoryImpl
    ): HesGamesRepository

    @Binds
    abstract fun bindsGameMapper(
        gameDomainDataMapper: GameDomainDataMapper
    ): Mapper<GameEntity, GameData>

    @Binds
    abstract fun bindsScreenshotMapper(
        screenshotDomainDataMapper: ScreenshotDomainDataMapper
    ): Mapper<ScreenshotEntity, ScreenshotData>
}