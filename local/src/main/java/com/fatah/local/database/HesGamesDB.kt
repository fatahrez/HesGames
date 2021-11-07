package com.fatah.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fatah.local.models.GameLocal
import com.fatah.local.models.ScreenshotLocal

@Database(
    entities = [GameLocal::class, ScreenshotLocal::class],
    version = 5,
    exportSchema = false
)
abstract class HesGamesDB : RoomDatabase(){
    companion object{
        private val LOCK = Any()
        private const val DATABASE_NAME = "hes_games.db"

        @Volatile
        private var INSTANCE: HesGamesDB? = null

        fun getInstance(@NonNull context: Context): HesGamesDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            HesGamesDB::class.java,
                            DATABASE_NAME
                        )
                        .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }


    abstract fun gameDao(): GameDao

    abstract fun screenshotDao(): ScreenshotDao
}