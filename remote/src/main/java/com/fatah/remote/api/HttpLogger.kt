package com.fatah.remote.api

import okhttp3.logging.HttpLoggingInterceptor

object HttpLogger {
    fun create(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}