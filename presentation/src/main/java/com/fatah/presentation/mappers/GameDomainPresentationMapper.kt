package com.fatah.presentation.mappers

import com.fatah.domain.entities.GameEntity
import com.fatah.presentation.models.Game
import javax.inject.Inject

class GameDomainPresentationMapper @Inject constructor(): Mapper<GameEntity, Game> {
    override fun from(e: Game): GameEntity {
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
            freetogameProfileUrl = e.freetogameProfileUrl
        )
    }

    override fun to(t: GameEntity): Game {
        return Game(
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