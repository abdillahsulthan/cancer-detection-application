package com.dicoding.asclepius.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.data.local.entity.History
import com.dicoding.asclepius.databinding.ItemHistoryBinding
import com.dicoding.asclepius.view.history.HistoryViewModel

class HistoryAdapter(private val historyViewModel: HistoryViewModel) : ListAdapter<History, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, historyViewModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(history)
    }

    class MyViewHolder(private val binding: ItemHistoryBinding, private val historyViewModel: HistoryViewModel) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: History){
            binding.textViewResult.text = history.result
            binding.textViewDate.text = history.date

            binding.imageViewClose.setOnClickListener {
                historyViewModel.delete(history)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }
}