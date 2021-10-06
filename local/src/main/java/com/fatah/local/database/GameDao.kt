package com.fatah.local.database

import androidx.room.*
import com.fatah.local.models.GameLocal
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface GameDao {
    @Query("SELECT * FROM games ORDER BY release_date")
    fun getGames(): Observable<List<GameLocal>>

    @Update
    fun updateGame(gameLocal: GameLocal): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addGames(games: List<GameLocal>)

    @Query("SELECT * FROM games WHERE id=:id")
    fun getIndividualGame(id: Int): Observable<GameLocal>

    @Query("DELETE FROM games")
    fun clearCachedGames(): Completable
}