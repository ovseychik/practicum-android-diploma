package ru.practicum.android.diploma.presentation.settings.viewholders

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ViewCountryItemBinding
import ru.practicum.android.diploma.presentation.settings.adapters.LocationAdapterItem

class LocationViewHolder(private val binding: ViewCountryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(locationAdapterItem: LocationAdapterItem) {
        when (locationAdapterItem) {
            is LocationAdapterItem.CountryItem -> binding.tvCountry.text = locationAdapterItem.country.countryName
            is LocationAdapterItem.City -> binding.tvCountry.text = locationAdapterItem.place.areaName
        }
    }
}
