package com.fatah.purgegame.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatah.domain.entities.GameEntity
import com.fatah.presentation.factory.ViewModelFactory
import com.fatah.presentation.mappers.GameDomainPresentationMapper
import com.fatah.presentation.mappers.Mapper
import com.fatah.presentation.models.Game
import com.fatah.presentation.viewmodels.GameViewModel
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
    abstract fun bindsGameMapper(
        gameDomainPresentationMapper: GameDomainPresentationMapper
    ): Mapper<GameEntity, Game>
}