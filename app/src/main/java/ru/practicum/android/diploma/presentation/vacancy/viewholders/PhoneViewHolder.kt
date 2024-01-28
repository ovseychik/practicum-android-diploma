package ru.practicum.android.diploma.presentation.vacancy.viewholders

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.PhoneItemBinding

class PhoneViewHolder(private val parentBinding: PhoneItemBinding) : RecyclerView.ViewHolder(parentBinding.root) {
    fun bind(phone: String) {
        parentBinding.tvPhone.text = phone
    }
}
