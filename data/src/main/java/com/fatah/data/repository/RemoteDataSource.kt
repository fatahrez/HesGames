package com.fatah.data.repository

import com.fatah.data.models.GameData
import io.reactivex.rxjava3.core.Observable

interface RemoteDataSource {
    fun getGames(): Observable<List<GameData>>

    fun getGame(id: Int): Observable<GameData>
}