package ru.practicum.android.diploma.presentation.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ViewIndustryBinding
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.presentation.settings.viewholders.IndustryViewHolder

class IndustryAdapter(private val onClick: (IndustryItem) -> Unit) : RecyclerView.Adapter<IndustryViewHolder>() {
    private var items: MutableList<IndustryItem> = mutableListOf()
    private var selectedIndustryItemName: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val binding = ViewIndustryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndustryViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, item.industryName == selectedIndustryItemName)
        holder.itemView.setOnClickListener { onClick.invoke(item) }
    }

    fun addIndustryItems(industryItems: List<IndustryItem>, selectedIndustryItemName: String) {
        industryItems.forEach {
            items.add(it)
        }
        this.selectedIndustryItemName = selectedIndustryItemName
        notifyDataSetChanged()
    }

    fun setSelectedIndustry(industry: IndustryItem) {
        val index = items.indexOfFirst { it.industryName == selectedIndustryItemName }
        if (index != -1) {
            selectedIndustryItemName = items[index].industryName
            notifyItemChanged(index)
        }
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }
}
