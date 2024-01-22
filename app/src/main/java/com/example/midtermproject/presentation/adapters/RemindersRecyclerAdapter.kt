package com.example.midtermproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.databinding.ReminderItemRecyclerviewBinding
import com.example.midtermproject.domain.model.Reminder
import com.example.midtermproject.presentation.model.ReminderUI

class RemindersRecyclerAdapter :
    ListAdapter<ReminderUI, RemindersRecyclerAdapter.RemindersViewHolder>(RemindersDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RemindersViewHolder(
        ReminderItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RemindersViewHolder, position: Int) {
        holder.bind()
    }

    inner class RemindersViewHolder(private val binding: ReminderItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: ReminderUI

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvMedicineName.text = model.medicineName
                tvTime.text = model.time
            }
        }
    }

    class RemindersDiffUtil : DiffUtil.ItemCallback<ReminderUI>() {
        override fun areItemsTheSame(oldItem: ReminderUI, newItem: ReminderUI): Boolean {
            return oldItem.reminderId == newItem.reminderId
        }

        override fun areContentsTheSame(oldItem: ReminderUI, newItem: ReminderUI): Boolean {
            return oldItem == newItem
        }
    }
}