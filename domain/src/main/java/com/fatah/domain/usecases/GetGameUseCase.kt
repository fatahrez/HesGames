package com.fatah.domain.usecases

import com.fatah.domain.entities.GameEntity
import com.fatah.domain.qualifiers.Background
import com.fatah.domain.qualifiers.Foreground
import com.fatah.domain.repository.HesGamesRepository
import com.fatah.domain.usecases.base.ObservableUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val hesGamesRepository: HesGamesRepository,
    @Background val backgroundScheduler: Scheduler,
    @Foreground val foregroundScheduler: Scheduler,
): ObservableUseCase<List<GameEntity>, Nothing>(
    foregroundScheduler, backgroundScheduler
) {
    override fun generateObservable(input: Nothing?): Observable<List<GameEntity>> {
        return hesGamesRepository.getGames()
    }
}