package ru.practicum.android.diploma.presentation.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.practicum.android.diploma.databinding.ViewIndustryBinding
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.presentation.settings.viewholders.IndustryViewHolder
import ru.practicum.android.diploma.ui.filter.fragment.industry.IndustryDiffCallback
import ru.practicum.android.diploma.ui.filter.fragment.industry.IndustryUiModel

class IndustryAdapter(private val onClick: (IndustryItem) -> Unit) : ListAdapter<IndustryUiModel, IndustryViewHolder>(
    IndustryDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val binding = ViewIndustryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndustryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item.industryItem, item.isSelected)
        holder.itemView.setOnClickListener { onClick.invoke(item.industryItem) }
    }
}
