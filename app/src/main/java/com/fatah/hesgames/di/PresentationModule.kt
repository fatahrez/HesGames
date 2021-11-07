package com.fatah.hesgames.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.entities.ScreenshotEntity
import com.fatah.presentation.factory.ViewModelFactory
import com.fatah.presentation.mappers.GameDomainPresentationMapper
import com.fatah.presentation.mappers.Mapper
import com.fatah.presentation.mappers.ScreenshotDomainPresentationMapper
import com.fatah.presentation.models.Game
import com.fatah.presentation.models.Screenshot
import com.fatah.presentation.viewmodels.GameViewModel
import com.fatah.presentation.viewmodels.IndividualGameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindsGameViewModel(gameViewModel: GameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IndividualGameViewModel::class)
    abstract fun bindsIndividualGameViewModel(individualGameViewModel: IndividualGameViewModel): ViewModel

    @Binds
    abstract fun bindsGameMapper(
        gameDomainPresentationMapper: GameDomainPresentationMapper
    ): Mapper<GameEntity, Game>

    @Binds
    abstract fun bindsScreenshotMapper(
        screenshotDomainPresentationMapper: ScreenshotDomainPresentationMapper
    ): Mapper<ScreenshotEntity, Screenshot>
}