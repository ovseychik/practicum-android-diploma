package ru.practicum.android.diploma.ui.filter.fragment.industry

import androidx.recyclerview.widget.DiffUtil

class IndustryDiffCallback : DiffUtil.ItemCallback<IndustryUiModel>() {
    override fun areItemsTheSame(oldItem: IndustryUiModel, newItem: IndustryUiModel): Boolean {
        return oldItem.industryItem.industryId == newItem.industryItem.industryId
    }

    override fun areContentsTheSame(oldItem: IndustryUiModel, newItem: IndustryUiModel): Boolean {
        return oldItem == newItem
    }
}
