package ru.practicum.android.diploma.presentation.vacancy.adapters

import androidx.recyclerview.widget.DiffUtil

class DetailsDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}
