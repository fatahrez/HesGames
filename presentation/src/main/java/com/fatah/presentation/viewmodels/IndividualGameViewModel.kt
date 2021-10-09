package com.fatah.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.usecases.GetIndividualGameUseCase
import com.fatah.presentation.mappers.Mapper
import com.fatah.presentation.models.Game
import com.fatah.presentation.models.Resource
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class IndividualGameViewModel @Inject internal constructor(
    private val gameDomainPresentationMapper: Mapper<GameEntity, Game>,
    private val getIndividualGameUseCase: GetIndividualGameUseCase
): ViewModel(){

//    private val individualGameMediatorLiveData = MediatorLiveData<Resource<Game>>()
//    val individualGameLiveData: LiveData<Resource<Game>>
//        get() = individualGameMediatorLiveData

    fun getIndividualGame(id: Int): LiveData<Resource<Game>> = getIndividualGameUseCase
        .buildUseCase(id)
        .map {
                gameDomainPresentationMapper.to(it)
        }.map {
            Resource.success(it)
        }
        .onErrorResumeNext {
            Observable.just(Resource.error(it.localizedMessage))
        }
        .toFlowable(BackpressureStrategy.LATEST)
        .toLiveData()
}