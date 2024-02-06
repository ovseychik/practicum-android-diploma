package ru.practicum.android.diploma.presentation.settings.viewholders

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ViewCountryItemBinding
import ru.practicum.android.diploma.presentation.settings.adapters.LocalityAdapterItem

class LocalityViewHolder(private val binding: ViewCountryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(localityAdapterItem: LocalityAdapterItem) {
        when (localityAdapterItem) {
            is LocalityAdapterItem.CountryItem -> binding.tvCountry.text = localityAdapterItem.country.countryName
            is LocalityAdapterItem.City -> binding.tvCountry.text = localityAdapterItem.place.areaName
        }
    }
}
