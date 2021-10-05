package com.fatah.data.mappers

interface Mapper<T, E> {
    fun from(e: E): T

    fun to(t: T): E
}