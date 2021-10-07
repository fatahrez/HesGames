package com.fatah.data.repository

import com.fatah.data.mappers.Mapper
import com.fatah.data.models.GameData
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.repository.PurgeGameRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PurgeGameRepositoryImpl @Inject    constructor(
    private val gameDomainDataMapper: Mapper<GameEntity, GameData>,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PurgeGameRepository{
    override fun getGames(): Observable<List<GameEntity>> {
        val localCachedGames = localDataSource.getGames()
            .map { games ->
                games.map {
                    gameDomainDataMapper.from(it)
                }
            }

        return remoteDataSource.getGames().map { games ->
            localDataSource.saveGames(games)
            games.map {
                gameDomainDataMapper.from(it)
            }
        }.concatWith(localCachedGames)
    }
}