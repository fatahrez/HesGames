package com.fatah.remote.mappers

import com.fatah.data.models.GameData
import com.fatah.remote.models.GameNetwork
import javax.inject.Inject

class GameDataNetworkMapper @Inject constructor(): Mapper<GameData, GameNetwork> {
    override fun from(e: GameNetwork): GameData {
        return GameData(
            id = e.id,
            title = e.title,
            thumbnail = e.thumbnail,
            shortDescription = e.shortDescription,
            description = e.description,
            gameUrl = e.gameUrl,
            genre = e.genre,
            platform = e.platform,
            publisher = e.publisher,
            developer = e.releaseDate,
            releaseDate = e.releaseDate,
            freetogameProfileUrl = e.freetogameProfileUrl
        )
    }

    override fun to(t: GameData): GameNetwork {
        return GameNetwork(
            id = t.id,
            title = t.title,
            thumbnail = t.thumbnail,
            shortDescription = t.shortDescription,
            description = t.description,
            gameUrl = t.gameUrl,
            genre = t.genre,
            platform = t.platform,
            publisher = t.publisher,
            developer = t.developer,
            releaseDate = t.releaseDate,
            freetogameProfileUrl = t.freetogameProfileUrl
        )
    }
}