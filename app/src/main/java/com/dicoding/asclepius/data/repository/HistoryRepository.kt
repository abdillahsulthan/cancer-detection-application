package com.dicoding.asclepius.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.entity.History
import com.dicoding.asclepius.data.local.room.HistoryDao
import com.dicoding.asclepius.data.local.room.HistoryRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(application: Application) {

    private val mHistoryDao : HistoryDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HistoryRoomDatabase.getDatabase(application)
        mHistoryDao = db.historyDao()
    }

    fun getAllHistory(): LiveData<List<History>> = mHistoryDao.getAllHistory()

    fun insert (history: History) {
        executorService.execute { mHistoryDao.insert(history) }
    }

    fun delete (history: History) {
        executorService.execute { mHistoryDao.delete(history) }
    }
}