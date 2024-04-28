package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.repository.HistoryRepository
import com.dicoding.asclepius.data.local.entity.History

class HistoryViewModel(mApplication: Application) : ViewModel() {

    private val mHistoryRepository : HistoryRepository = HistoryRepository(mApplication)

    fun getAllHistory() : LiveData<List<History>> {
        return mHistoryRepository.getAllHistory()
    }

    fun delete(history: History) {
        return mHistoryRepository.delete(history)
    }
}