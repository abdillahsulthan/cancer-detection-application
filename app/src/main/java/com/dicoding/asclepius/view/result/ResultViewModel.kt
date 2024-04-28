package com.dicoding.asclepius.view.result

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.repository.HistoryRepository
import com.dicoding.asclepius.data.local.entity.History

class ResultViewModel(mApplication : Application) : ViewModel() {

    private val mHistoryRepository : HistoryRepository = HistoryRepository(mApplication)

    fun insert(history: History) {
        mHistoryRepository.insert(history)
    }
}