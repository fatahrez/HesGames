package com.fatah.local.mappers

import com.fatah.data.models.GameData
import com.fatah.local.models.GameLocal
import javax.inject.Inject

class GameDataLocalMapper @Inject constructor(): Mapper<GameData, GameLocal>{
    override fun from(e: GameLocal): GameData {
        return GameData(
            id = e.id,
            title = e.title,
            thumbnail = e.thumbnail,
            shortDescription = e.shortDescription,
            gameUrl = e.gameUrl,
            genre = e.genre,
            platform = e.platform,
            publisher = e.publisher,
            developer = e.developer,
            releaseDate = e.releaseDate,
            freetogameProfileUrl = e.freetogameProfileUrl
        )
    }

    override fun to(t: GameData): GameLocal {
        return GameLocal(
            id = t.id,
            title = t.title,
            thumbnail = t.thumbnail,
            shortDescription = t.shortDescription,
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