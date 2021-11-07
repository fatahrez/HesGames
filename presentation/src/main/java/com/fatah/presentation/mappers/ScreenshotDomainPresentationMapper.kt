package com.fatah.presentation.mappers

import com.fatah.domain.entities.ScreenshotEntity
import com.fatah.presentation.models.Screenshot
import javax.inject.Inject

class ScreenshotDomainPresentationMapper @Inject constructor(): Mapper<ScreenshotEntity, Screenshot> {
    override fun from(e: Screenshot): ScreenshotEntity {
        return ScreenshotEntity(
            id = e.id,
            image = e.image
        )
    }

    override fun to(t: ScreenshotEntity): Screenshot {
        return Screenshot(
            id = t.id,
            image = t.image
        )
    }
}