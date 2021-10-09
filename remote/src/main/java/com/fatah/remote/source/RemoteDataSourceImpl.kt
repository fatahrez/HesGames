package com.fatah.remote.source

import com.fatah.data.models.GameData
import com.fatah.data.repository.RemoteDataSource
import com.fatah.remote.api.HesGamesService
import com.fatah.remote.mappers.Mapper
import com.fatah.remote.models.GameNetwork
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val gameDataNetworkMapper: Mapper<GameData, GameNetwork>,
    private val hesGamesService: HesGamesService
): RemoteDataSource{
    override fun getGames(): Observable<List<GameData>> {
        return hesGamesService.getGames()
            .map {games ->
                games.map {
                    gameDataNetworkMapper.from(it)
                }
            }
    }

    override fun getGame(id: Int): Observable<GameData> {
        return hesGamesService.getGame(id)
            .map {
                gameDataNetworkMapper.from(it)
            }
    }
}