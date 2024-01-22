package com.example.midtermproject.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.R
import com.example.midtermproject.databinding.CalendarItemRecyclerviewBinding
import com.example.midtermproject.databinding.ReminderItemRecyclerviewBinding
import com.example.midtermproject.presentation.model.CalendarDay
import com.example.midtermproject.presentation.model.ReminderUI

class CalendarRecyclerAdapter(private val days: List<CalendarDay>) :
    RecyclerView.Adapter<CalendarRecyclerAdapter.DayViewHolder>() {

    inner class DayViewHolder(private val binding: CalendarItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(){
                val model = days[adapterPosition]
                binding.tvDate.text = model.dayOfMonth.toString()
                binding.tvWeekDay.text = model.dayOfWeek.toString()
                if (model.isCurrentDay){
                    binding.tvDate.setBackgroundResource(R.drawable.calendar_current_arc)
                    binding.tvMonth.visibility = View.VISIBLE
                    binding.tvMonth.text = model.currentMonth
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DayViewHolder(
        CalendarItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind()

    }

    override fun getItemCount(): Int = days.size
}