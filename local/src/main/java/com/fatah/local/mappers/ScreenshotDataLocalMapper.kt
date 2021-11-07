package com.fatah.local.mappers

import com.fatah.data.models.ScreenshotData
import com.fatah.local.models.ScreenshotLocal
import javax.inject.Inject

class ScreenshotDataLocalMapper @Inject constructor(): Mapper<ScreenshotData, ScreenshotLocal> {
    override fun from(e: ScreenshotLocal): ScreenshotData {
        return ScreenshotData(
            id = e.id,
            image = e.image
        )
    }

    override fun to(t: ScreenshotData): ScreenshotLocal {
        return ScreenshotLocal(
            id = t.id,
            gameId = null,
            image = t.image
        )
    }
}