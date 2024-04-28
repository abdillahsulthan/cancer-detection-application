package com.dicoding.asclepius.view.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.view.utils.ViewModelFactory
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.data.local.entity.History
import com.dicoding.asclepius.view.adapter.HistoryAdapter

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    private val historyViewModel by viewModels<HistoryViewModel>() {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "History"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)

        historyViewModel.getAllHistory().observe(this) { history ->
            val items = arrayListOf<History>()
            history.map {
                val item = History(idHistory = it.idHistory, image = it.image, result = it.result, date = it.date)
                items.add(item)
            }
            setHistory(items)
        }
    }

    private fun setHistory(historyList: List<History>) {
        val adapter = HistoryAdapter(historyViewModel)
        adapter.submitList(historyList)
        binding.rvHistory.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}