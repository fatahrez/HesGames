package com.fatah.hesgames.di

import com.fatah.data.models.GameData
import com.fatah.data.repository.RemoteDataSource
import com.fatah.remote.api.HttpClient
import com.fatah.remote.api.HttpLogger
import com.fatah.remote.api.HesGamesService
import com.fatah.remote.mappers.GameDataNetworkMapper
import com.fatah.remote.mappers.Mapper
import com.fatah.remote.models.GameNetwork
import com.fatah.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {

    @Module
    interface Binders {
        @Binds
        fun bindsRemoteDataSource(
            remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataSource

        @Binds
        fun bindsGameMapper(
            gameDataNetworkMapper: GameDataNetworkMapper
        ): Mapper<GameData, GameNetwork>
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLogger.create()

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkHttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    fun providesPurgeGameService(retrofit: Retrofit): HesGamesService = retrofit.create(
        HesGamesService::class.java
    )

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://www.freetogame.com/api/")
            .build()
    }
}