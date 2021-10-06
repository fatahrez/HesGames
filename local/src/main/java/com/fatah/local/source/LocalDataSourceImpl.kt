package com.fatah.local.source

import com.fatah.data.models.GameData
import com.fatah.data.repository.LocalDataSource
import com.fatah.local.database.GameDao
import com.fatah.local.mappers.Mapper
import com.fatah.local.models.GameLocal
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val gameDataLocalMapper: Mapper<GameData, GameLocal>,
    private val gameDao: GameDao
): LocalDataSource {
    override fun getGames(): Observable<List<GameData>> {
        return gameDao.getGames()
            .map { games ->
                games.map {
                    gameDataLocalMapper.from(it)
                }
            }
    }

    override fun saveGames(games: List<GameData>) {
       gameDao.addGames(
            games.map {
                gameDataLocalMapper.to(it)
            }
        )
    }

    override fun updateGame(game: GameData) {
        gameDao.updateGame(
            gameDataLocalMapper.to(game)
        )
    }
}