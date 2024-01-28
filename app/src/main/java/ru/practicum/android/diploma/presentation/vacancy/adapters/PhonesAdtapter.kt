package ru.practicum.android.diploma.presentation.vacancy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.practicum.android.diploma.databinding.PhoneItemBinding
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem
import ru.practicum.android.diploma.presentation.vacancy.viewholders.PhoneViewHolder

class PhonesAdtapter(private val phoneClick: (String) -> Unit) : ListAdapter<String, PhoneViewHolder>(DetailsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val viewBinding = PhoneItemBinding.inflate(layoutInspector, parent, false)
        return PhoneViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            phoneClick(currentList[holder.adapterPosition])
        }
    }
}
