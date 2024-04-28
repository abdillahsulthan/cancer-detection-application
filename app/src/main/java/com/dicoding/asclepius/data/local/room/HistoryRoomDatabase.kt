package com.dicoding.asclepius.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dicoding.asclepius.data.local.entity.History

@Database(entities = [History::class], version = 2)
abstract class HistoryRoomDatabase : RoomDatabase() {

    abstract fun historyDao() : HistoryDao

    companion object {
        @Volatile
        private var INSTANCE : HistoryRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): HistoryRoomDatabase {
            if (INSTANCE == null) {
                synchronized(HistoryRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryRoomDatabase::class.java, "history_database")
                        .addMigrations(MIGRATION)
                        .build()
                }
            }
            return INSTANCE as HistoryRoomDatabase
        }

        private val MIGRATION: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                 database.execSQL("ALTER TABLE History ADD COLUMN date TEXT")
            }
        }
    }
}