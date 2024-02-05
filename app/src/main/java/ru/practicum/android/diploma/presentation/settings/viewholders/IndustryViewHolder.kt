package ru.practicum.android.diploma.presentation.settings.viewholders

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ViewIndustryBinding
import ru.practicum.android.diploma.domain.models.guides.IndustryItem

class IndustryViewHolder(private val binding: ViewIndustryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(industryItem: IndustryItem, isChecked: Boolean) {
        binding.tvIndustryName.text = industryItem.industryName
        binding.btnRadio.isChecked = isChecked
    }
}
