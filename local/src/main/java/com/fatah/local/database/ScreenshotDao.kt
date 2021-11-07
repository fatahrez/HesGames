package com.fatah.local.database

import androidx.room.*
import com.fatah.local.models.ScreenshotLocal
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface ScreenshotDao {
    @Query("SELECT * FROM screenshots ORDER BY id")
    fun getScreenshots():Observable<List<ScreenshotLocal>>

    @Update
    fun updateScreenshot(screenshotLocal: ScreenshotLocal): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addScreenshots(screenshots: List<ScreenshotLocal>)

    @Query("SELECT * FROM screenshots WHERE game_id=:gameId")
    fun getIndividualScreenshot(gameId: Int): Observable<List<ScreenshotLocal>>

    @Query("DELETE FROM screenshots")
    fun clearCachedScreenshots(): Completable
}