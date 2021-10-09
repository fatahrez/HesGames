package com.fatah.remote.api

import com.fatah.remote.models.GameNetwork
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PurgeGameService {
    @GET("games")
    fun getGames(): Observable<List<GameNetwork>>

    @GET("game")
    fun getGame(@Query("id") id: Int): Observable<GameNetwork>
}