package com.fatah.domain.usecases

import com.fatah.domain.entities.GameEntity
import com.fatah.domain.qualifiers.Background
import com.fatah.domain.qualifiers.Foreground
import com.fatah.domain.repository.PurgeGameRepository
import com.fatah.domain.usecases.base.ObservableUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetIndividualGameUseCase @Inject constructor(
    private val repository: PurgeGameRepository,
    @Foreground foregroundScheduler: Scheduler,
    @Background backgroundScheduler: Scheduler
): ObservableUseCase<GameEntity, Int>(foregroundScheduler, backgroundScheduler) {
    override fun generateObservable(input: Int?): Observable<GameEntity> {
        if (input != null) {
            return repository.getGame(input)
        }

        throw IllegalArgumentException("Input cannot be empty")
    }
}