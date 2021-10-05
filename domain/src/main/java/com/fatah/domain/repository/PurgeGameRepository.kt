package com.fatah.domain.repository

import com.fatah.domain.entities.GameEntity
import io.reactivex.rxjava3.core.Observable

interface PurgeGameRepository {
    fun getGames(): Observable<List<GameEntity>>
}