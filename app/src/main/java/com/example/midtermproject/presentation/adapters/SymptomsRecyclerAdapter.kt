package com.example.midtermproject.presentation.adapters

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.databinding.ReminderItemRecyclerviewBinding
import com.example.midtermproject.databinding.SymptomItemRecyclerviewBinding
import com.example.midtermproject.domain.model.Symptom
import com.example.midtermproject.presentation.mapper.SymptomUI
import com.example.midtermproject.presentation.model.ReminderUI

class SymptomsRecyclerAdapter( private val onItemClick: (SymptomUI) -> Unit) :
    ListAdapter<SymptomUI, SymptomsRecyclerAdapter.SymptomsViewHolder>(SymptomsDiffUtil()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SymptomsViewHolder(
        SymptomItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        holder.bind()
    }

    inner class SymptomsViewHolder(private val binding: SymptomItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: SymptomUI

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                binding.tvSymptomName.text = model.name
            }
            listeners()


        }

        fun listeners(){
            binding.root.setOnClickListener {
                onItemClick.invoke(model)
            }
        }

    }

    class SymptomsDiffUtil : DiffUtil.ItemCallback<SymptomUI>() {
        override fun areItemsTheSame(oldItem: SymptomUI, newItem: SymptomUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SymptomUI, newItem: SymptomUI): Boolean {
            return oldItem == newItem
        }
    }

    fun filter(query: String) {
        val filteredList = currentList.filter { symptom -> symptom.name.contains(query, true) }
        submitList(filteredList)
    }
}