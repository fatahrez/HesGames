package com.fatah.data.mappers

import com.fatah.data.models.ScreenshotData
import com.fatah.domain.entities.ScreenshotEntity
import javax.inject.Inject

class ScreenshotDomainDataMapper @Inject constructor(): Mapper<ScreenshotEntity, ScreenshotData> {
    override fun from(e: ScreenshotData): ScreenshotEntity {
        return ScreenshotEntity(
            id = e.id,
            image = e.image
        )
    }

    override fun to(t: ScreenshotEntity): ScreenshotData {
        return ScreenshotData(
            id = t.id,
            image = t.image
        )
    }
}