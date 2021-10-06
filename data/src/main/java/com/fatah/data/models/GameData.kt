package com.fatah.data.models

data class GameData(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String?,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val freetogameProfileUrl: String
)
