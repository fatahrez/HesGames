package com.fatah.domain.usecases

import com.fatah.domain.entities.GameEntity
import com.fatah.domain.qualifiers.Background
import com.fatah.domain.qualifiers.Foreground
import com.fatah.domain.repository.PurgeGameRepository
import com.fatah.domain.usecases.base.ObservableUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetGameUseCase @Inject() constructor(
    @Background val backgroundScheduler: Scheduler,
    @Foreground val foregroundScheduler: Scheduler,
    private val purgeGameRepository: PurgeGameRepository
): ObservableUseCase<List<GameEntity>, Nothing>(
    foregroundScheduler, backgroundScheduler
) {
    override fun generateObservable(input: Nothing?): Observable<List<GameEntity>> {
        return purgeGameRepository.getGames()
    }
}