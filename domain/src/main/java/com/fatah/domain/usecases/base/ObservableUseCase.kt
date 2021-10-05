package com.fatah.domain.usecases.base

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import java.security.PrivateKey
import javax.inject.Inject

abstract class ObservableUseCase<T, in Input> constructor(
    private val foregroundScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) {
    protected abstract fun generateObservable(input: Input?): Observable<T>

    fun execute(input: Input?): Observable<T> {
        return generateObservable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}