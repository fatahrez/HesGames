package com.fatah.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.usecases.GetGameUseCase
import com.fatah.presentation.mappers.Mapper
import com.fatah.presentation.models.Game
import com.fatah.presentation.models.Resource
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameViewModel @Inject internal constructor(
    private val gameDomainPresentationMapper: Mapper<GameEntity, Game>,
    private val getGameUseCase: GetGameUseCase
): ViewModel(){

    val gameLiveData: LiveData<Resource<List<Game>>>
        get() = getGameUseCase
        .buildUseCase()
        .map { games ->
            games.map {
                gameDomainPresentationMapper.to(it)
            }
        }.map {
            Resource.success(it)
        }
//        .startWith(Resource.loading<Nothing>())
        .onErrorResumeNext {
            Observable.just(Resource.error(it.localizedMessage))
        }
        .toFlowable(BackpressureStrategy.BUFFER)
        .toLiveData()
}