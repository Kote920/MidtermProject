package com.example.midtermproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.databinding.ChosenSymptomRecycleritemBinding
import com.example.midtermproject.databinding.IssuesItemRecyclerviewBinding
import com.example.midtermproject.presentation.mapper.SymptomUI
import com.example.midtermproject.presentation.model.IssueUI


class IssuesRecyclerAdapter :
    ListAdapter<IssueUI, IssuesRecyclerAdapter.IssuesViewHolder>(IssuesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IssuesViewHolder(
        IssuesItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        holder.bind()
    }

    inner class IssuesViewHolder(private val binding: IssuesItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: IssueUI

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvName.text = model.name
                tvProfName.text = model.profName
                tvAccuracy.text = model.accuracy.toString()
            }
        }
    }

    class IssuesDiffUtil : DiffUtil.ItemCallback<IssueUI>() {
        override fun areItemsTheSame(oldItem: IssueUI, newItem: IssueUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IssueUI, newItem: IssueUI): Boolean {
            return oldItem == newItem
        }
    }
}
