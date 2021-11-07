package com.fatah.remote.mappers

import com.fatah.data.models.ScreenshotData
import com.fatah.remote.models.ScreenshotNetwork
import javax.inject.Inject

class ScreenshotDataNetworkMapper @Inject constructor(): Mapper<ScreenshotData, ScreenshotNetwork> {
    override fun from(e: ScreenshotNetwork): ScreenshotData {
        return ScreenshotData(
            id = e.id,
            image = e.image
        )
    }

    override fun to(t: ScreenshotData): ScreenshotNetwork {
        return ScreenshotNetwork(
            id = t.id,
            image = t.image
        )
    }
}