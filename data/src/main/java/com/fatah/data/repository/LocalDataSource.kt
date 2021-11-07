package com.fatah.data.repository

import com.fatah.data.models.GameData
import com.fatah.data.models.ScreenshotData
import io.reactivex.rxjava3.core.Observable

interface LocalDataSource {
    fun getGames(): Observable<List<GameData>>

    fun getGame(id: Int): Observable<GameData>

    fun saveGames(games: List<GameData>)

    fun updateGame(game: GameData)

    fun getScreenshots(): Observable<List<ScreenshotData>>

    fun getIndividualScreenshot(id: Int): Observable<List<ScreenshotData>>

    fun saveScreenshots(screenshots: List<ScreenshotData>)

    fun updateScreenshot(screenshotData: ScreenshotData)
}