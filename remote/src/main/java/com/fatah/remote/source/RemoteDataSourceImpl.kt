package com.fatah.remote.source

import com.fatah.data.models.GameData
import com.fatah.data.repository.RemoteDataSource
import com.fatah.remote.api.PurgeGameService
import com.fatah.remote.mappers.Mapper
import com.fatah.remote.models.GameNetwork
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val gameDataNetworkMapper: Mapper<GameData, GameNetwork>,
    private val purgeGameService: PurgeGameService
): RemoteDataSource{
    override fun getGames(): Observable<List<GameData>> {
        return purgeGameService.getGames()
            .map {games ->
                games.map {
                    gameDataNetworkMapper.from(it)
                }
            }
    }
}