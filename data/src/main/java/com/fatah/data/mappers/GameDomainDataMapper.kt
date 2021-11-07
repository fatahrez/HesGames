package com.fatah.data.mappers

import com.fatah.data.models.GameData
import com.fatah.data.models.ScreenshotData
import com.fatah.domain.entities.GameEntity
import com.fatah.domain.entities.ScreenshotEntity
import javax.inject.Inject

class GameDomainDataMapper @Inject constructor(): Mapper<GameEntity, GameData> {

    override fun from(e: GameData): GameEntity {
        return GameEntity(
            id = e.id,
            title = e.title,
            thumbnail = e.thumbnail,
            shortDescription = e.shortDescription,
            description = e.description,
            gameUrl = e.gameUrl,
            genre = e.genre,
            platform = e.platform,
            publisher = e.publisher,
            developer = e.developer,
            releaseDate = e.releaseDate,
            freetogameProfileUrl = e.freetogameProfileUrl,
            screenshots = e.screenshots?.map {
                ScreenshotEntity(it.id, it.image)
            }
        )
    }

    override fun to(t: GameEntity): GameData {

        return GameData(
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
            freetogameProfileUrl = t.freetogameProfileUrl,
            screenshots = t.screenshots?.map {
                ScreenshotData(it.id, it.image)
            }
        )
    }
}