package com.fatah.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fatah.local.models.GameLocal

@Database(
    entities = [GameLocal::class],
    version = 2,
    exportSchema = false
)
abstract class PurgeGameDB : RoomDatabase(){
    companion object{
        private val LOCK = Any()
        private const val DATABASE_NAME = "purge_game.db"

        @Volatile
        private var INSTANCE: PurgeGameDB? = null

        fun getInstance(@NonNull context: Context): PurgeGameDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            PurgeGameDB::class.java,
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
}