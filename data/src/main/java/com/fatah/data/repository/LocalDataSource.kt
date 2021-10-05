package com.fatah.data.repository

import com.fatah.data.models.GameData
import io.reactivex.rxjava3.core.Observable

interface LocalDataSource {
    fun getGames(): Observable<List<GameData>>

    fun saveGames(games: List<GameData>)

    fun updateGame(game: GameData)
}