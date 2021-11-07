package com.fatah.data.repository

import com.fatah.data.mappers.Mapper
import com.fatah.data.mappers.ScreenshotDomainDataMapper
import com.fatah.data.models.GameData
import com.fatah.data.models.ScreenshotData
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.entities.ScreenshotEntity
import com.fatah.domain.repository.HesGamesRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HesGamesRepositoryImpl @Inject    constructor(
    private val gameDomainDataMapper: Mapper<GameEntity, GameData>,
    private val screenshotDomainDataMapper: Mapper<ScreenshotEntity, ScreenshotData>,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): HesGamesRepository{
    override fun getGames(): Observable<List<GameEntity>> {
        val localCachedGames = localDataSource.getGames()
            .map { games ->
                games.map { game ->
                    gameDomainDataMapper.from(game)
                }
            }

        return remoteDataSource.getGames().map { games ->
            localDataSource.saveGames(games)
            games.map {
                gameDomainDataMapper.from(it)
            }
        }
            .concatWith(localCachedGames)
    }

    override fun getGame(id: Int): Observable<GameEntity> {
        return remoteDataSource.getGame(id).map { game ->
            localDataSource.updateGame(game)

            localDataSource.saveScreenshots(game.screenshots!!)

            localDataSource.getIndividualScreenshot(game.id).map {
                game.screenshots = it
            }
//            it.screenshots?.let { it1 -> localDataSource.saveScreenshots(it1) }

            gameDomainDataMapper.from(game)
        }
    }
}