package com.fatah.remote.models

import com.google.gson.annotations.SerializedName

data class ScreenshotNetwork (
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String
)