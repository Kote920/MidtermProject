package com.example.midtermproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.databinding.ChosenSymptomRecycleritemBinding
import com.example.midtermproject.presentation.mapper.SymptomUI

class ChosenSymptomsRecyclerAdapter( private val onDeleteClick: (SymptomUI) -> Unit) :
    ListAdapter<SymptomUI, ChosenSymptomsRecyclerAdapter.SymptomsViewHolder>(SymptomsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SymptomsViewHolder(
        ChosenSymptomRecycleritemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        holder.bind()
    }

    inner class SymptomsViewHolder(private val binding: ChosenSymptomRecycleritemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: SymptomUI

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvChosenSymptom.text  = model.name
            }
            listeners()
        }

        fun listeners(){
            binding.imgBtnDelete.setOnClickListener {
                onDeleteClick.invoke(model)
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
}
