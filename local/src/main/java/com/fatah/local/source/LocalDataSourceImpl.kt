package com.fatah.local.source

import com.fatah.data.models.GameData
import com.fatah.data.models.ScreenshotData
import com.fatah.data.repository.LocalDataSource
import com.fatah.local.database.GameDao
import com.fatah.local.database.ScreenshotDao
import com.fatah.local.mappers.Mapper
import com.fatah.local.models.GameLocal
import com.fatah.local.models.ScreenshotLocal
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val gameDataLocalMapper: Mapper<GameData, GameLocal>,
    private val screenshotDataLocalMapper: Mapper<ScreenshotData, ScreenshotLocal>,
    private val gameDao: GameDao,
    private val screenshotDao: ScreenshotDao
): LocalDataSource {
    override fun getGames(): Observable<List<GameData>> {
        return gameDao.getGames()
            .map { games ->
                games.map {
                    gameDataLocalMapper.from(it)
                }
            }
    }

    override fun getGame(id: Int): Observable<GameData> {
        return gameDao.getIndividualGame(id)
            .map {
                gameDataLocalMapper.from(it)
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

    override fun getScreenshots(): Observable<List<ScreenshotData>> {
        return screenshotDao.getScreenshots()
            .map { screenshots ->
                screenshots.map {
                    screenshotDataLocalMapper.from(it)
                }
            }

    }

    override fun getIndividualScreenshot(id: Int): Observable<List<ScreenshotData>> {
        return screenshotDao.getIndividualScreenshot(id)
            .map {screenshots ->
                screenshots.map {
                    screenshotDataLocalMapper.from(it)
                }
            }

    }

    override fun saveScreenshots(screenshots: List<ScreenshotData>) {
        screenshotDao.addScreenshots(
            screenshots.map {
                screenshotDataLocalMapper.to(it)
            }
        )
    }

    override fun updateScreenshot(screenshotData: ScreenshotData) {
        screenshotDao.updateScreenshot(
            screenshotDataLocalMapper.to(screenshotData)
        )
    }
}